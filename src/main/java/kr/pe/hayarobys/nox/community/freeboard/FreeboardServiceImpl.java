package kr.pe.hayarobys.nox.community.freeboard;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.suph.security.core.dto.JsonResultVO;
import com.suph.security.core.enums.Authgroup;
import com.suph.security.core.enums.Flag;
import com.suph.security.core.enums.TempSaveCategory;
import com.suph.security.core.enums.TempSaveUse;
import com.suph.security.core.userdetails.MemberInfo;
import com.suph.security.core.util.ContextUtil;

import kr.pe.hayarobys.nox.common.comment.CommentDAO;
import kr.pe.hayarobys.nox.common.comment.CommentGroupVO;
import kr.pe.hayarobys.nox.common.exception.ForbiddenException;
import kr.pe.hayarobys.nox.common.tempsave.TempSaveDAO;
import kr.pe.hayarobys.nox.common.tempsave.TempSaveVO;
import kr.pe.hayarobys.nox.common.upload.FileGroupVO;
import kr.pe.hayarobys.nox.common.upload.FileVO;
import kr.pe.hayarobys.nox.common.upload.UploadDAO;

@Service
public class FreeboardServiceImpl implements FreeboardService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FreeboardDAO freeboardDAO;
	
	@Autowired
	private TempSaveDAO tempSaveDAO;
	
	@Autowired
	private UploadDAO uploadDAO;
	
	@Autowired
	private CommentDAO commentDAO;
	
	@Override
	public ModelAndView getFreeboardTempSaveNo(ModelAndView mav){
		MemberInfo memberInfo = ContextUtil.getMemberInfo();
		// 미로그인 유저여서 authentication이 null일 가능성은 security 단에서 1차적으로 차단한 상태이므로 부가 처리를 하지 않습니다.
		Integer memNo = memberInfo.getNo();
		
		// 기존에 FREEBOARD 카테로리에 신규 작성 용도로 등록된 임시 저장글이 있는가?
		// 있다면 가장 최근에 작성한 임시 저장글의 일련 번호를 반환
		TempSaveVO searchTempSaveVO = new TempSaveVO();
		searchTempSaveVO.setMemNo(memNo);
		searchTempSaveVO.setTempSaveCategory(TempSaveCategory.FREEBOARD);
		searchTempSaveVO.setTempSaveUse(TempSaveUse.WRITE);
		TempSaveVO tempSaveVO = tempSaveDAO.selectLastTempSaveFromCategory(searchTempSaveVO);
		List<FileVO> fileVOList = null;
		if(tempSaveVO != null){
			fileVOList = uploadDAO.selectFileByFileGroupNo(tempSaveVO.getFileGroupNo());
		}else{
			// 파일 묶음 레코드 생성
			FileGroupVO fileGroupVO = new FileGroupVO();
			fileGroupVO.setMemNo(memNo);
			
			// 파일 그룹의 기본 공개범위는 '비공개'. 추후 임시저장글 > 글 작성 완료시 설정한 값에 따라 공개범위 수정
			fileGroupVO.setAuthgroup(Authgroup.SECRET);
			freeboardDAO.insertFileGrp(fileGroupVO);
			Integer fileGroupNo = fileGroupVO.getFileGroupNo();
			
			tempSaveVO = new TempSaveVO();
			tempSaveVO.setMemNo(memNo);
			tempSaveVO.setFileGroupNo(fileGroupNo);
			tempSaveVO.setTempSaveCategory(TempSaveCategory.FREEBOARD);
			tempSaveVO.setTempSaveUse(TempSaveUse.WRITE);
			tempSaveVO.setTempSaveTitle("");
			tempSaveVO.setTempSaveBody("");
			
			tempSaveDAO.insertTempSave(tempSaveVO);
		}
		
		mav.addObject("tempSaveVO", tempSaveVO);
		mav.addObject("fileVOList", fileVOList);
		
		mav.setViewName("community/freeboard/write");
		return mav;
	}

	@Override
	public JsonResultVO<Integer> postWrite(TempSaveVO requestTempSaveVO){
		logger.debug("글 등록 요청: {}", requestTempSaveVO);
		
		// 요청 들어온 임시 저장 번호로 원본 임시 저장 글 조회
		Integer tempSaveNo = requestTempSaveVO.getTempSaveNo();
		TempSaveVO originalTempSaveVO = tempSaveDAO.selectTempSaveByTempSaveNo(tempSaveNo);
		
		// 원본 임시 저장글과 요청자의 계정 일치 여부 확인
		Integer memNo = ContextUtil.getMemberInfo().getNo();
		if( memNo != originalTempSaveVO.getMemNo() ){
			logger.debug("요청자: {}, 원본 임시 저장 글 등록자: {}", memNo, originalTempSaveVO.getMemNo());
			throw new ForbiddenException("임시 저장 글의 등록자와 현재 요청자의 계정 정보가 불일치 합니다.");
		}
		requestTempSaveVO.setMemNo(memNo);
		
		// 원본 임시 저장글로부터 파일 그룹 번호 조회
		Integer fileGroupNo = originalTempSaveVO.getFileGroupNo();
		requestTempSaveVO.setFileGroupNo(fileGroupNo);
		
		// 공개범위 미 설정 시 기본값은 전체공개
		Authgroup authgroup = requestTempSaveVO.getOpenType();
		if(authgroup == null){
			authgroup = Authgroup.PUBLIC;
		}
		
		// 파일 그룹의 공개 범위를 작성자가 요청한 공개 범위로 수정.
		FileGroupVO fileGroupVO = new FileGroupVO();
		fileGroupVO.setFileGroupNo(fileGroupNo);
		fileGroupVO.setAuthgroup(authgroup);
		uploadDAO.updateAuthgroupOfFileGroupByFileGroupNo(fileGroupVO);
		
		// 댓글 그룹 생성
		CommentGroupVO commentGroupVO = new CommentGroupVO();
		commentGroupVO.setCommentGroupNewWriteFlag(Flag.Y);
		commentDAO.insertCommentGroup(commentGroupVO);
		
		// 자유 게시판 그룹 생성
		FreeboardGroupVO freeboardGroupVO = new FreeboardGroupVO();
		freeboardGroupVO.setMemNo(memNo);
		freeboardGroupVO.setCommentGroupNo(commentGroupVO.getCommentGroupNo());
		freeboardGroupVO.setAuthgroup(authgroup);
		freeboardGroupVO.setFreeboardGroupClassOrder(0);
		freeboardGroupVO.setFreeboardGroupClassDepth(1);
		this.insertFreeboardGroup(freeboardGroupVO);
		
		// 자유 게시판 상세 생성
		FreeboardVO freeboardVO = new FreeboardVO();
		freeboardVO.setFreeboardGroupNo(freeboardGroupVO.getFreeboardGroupNo());
		freeboardVO.setFreeboardTitle(requestTempSaveVO.getTempSaveTitle());
		freeboardVO.setFreeboardBody(requestTempSaveVO.getTempSaveBody());
		freeboardVO.setFileGroupNo(fileGroupNo);
		freeboardDAO.insertFreeboard(freeboardVO);
		
		// 임시 저장 데이터 제거
		tempSaveDAO.deleteTempSaveByTempSaveNo(tempSaveNo);
		
		// 생성된 게시글 그룹 번호 반환
		JsonResultVO<Integer> jsonResultVO = new JsonResultVO<Integer>(freeboardGroupVO.getFreeboardGroupNo());
		return jsonResultVO;
	}

	@Override
	public ModelAndView getDetail(Integer freeboardGroupNo, ModelAndView mav){
		FreeboardDetailVO freeboardDetailVO = freeboardDAO.selectFreeboardDetail(freeboardGroupNo);
		
		// TODO: 요청자가 이 게시글의 최소 조회 권한을 통과하는지 확인 할 것
		
		mav.addObject("freeboardDetailVO", freeboardDetailVO);
		mav.setViewName("/community/freeboard/detail");
		return mav;
	}
	
	/**
	 * 자유게시판 그룹을 생성하고 계층 최상위 게시물로 자기 자신을 참조합니다.
	 * @param freeboardGroupVO
	 */
	private void insertFreeboardGroup(FreeboardGroupVO freeboardGroupVO){
		freeboardDAO.insertFreeboardGroup(freeboardGroupVO);
		freeboardGroupVO.setFreeboardGroupClassNo(freeboardGroupVO.getFreeboardGroupNo());
		freeboardDAO.updateFreeboardGroupClassNo(freeboardGroupVO);
	}

	@Override
	public ModelAndView getModifyForm(Integer freeboardGroupNo, ModelAndView mav){
		// 수정 대상글의 원본 정보 조회
		FreeboardDetailVO freeboardDetailVO = freeboardDAO.selectFreeboardDetail(freeboardGroupNo);
		
		// 요청자와 수정 대상글의 계정 정보가 일치하는지 확인
		Integer memNo = ContextUtil.getMemberInfo().getNo();
		if(memNo != freeboardDetailVO.getMemNo()){
			logger.debug("수정 요청자: {}, 수정 대상 글 작성자: {}", memNo, freeboardDetailVO.getMemNo());
			throw new ForbiddenException("수정 대상 글의 작성자와 수정 요청자의 계정 정보가 불일치 합니다.");
		}
		
		// FREEBOARD 카테고리에 MODIFY 용도로 생성된 요청자의 임시 저장글이 있는지 확인
		TempSaveVO searchTempSaveVO = new TempSaveVO();
		searchTempSaveVO.setMemNo(memNo);
		searchTempSaveVO.setTempSaveCategory(TempSaveCategory.FREEBOARD);
		searchTempSaveVO.setTempSaveUse(TempSaveUse.MODIFY);
		TempSaveVO tempSaveVO = tempSaveDAO.selectLastTempSaveFromCategory(searchTempSaveVO);
		List<FileVO> fileVOList = null;
		
		// 기존 임시 저장 글이 있는 경우 해당 글의 첨부파일 목록 조회
		if(tempSaveVO != null){
			fileVOList = uploadDAO.selectFileByFileGroupNo(tempSaveVO.getFileGroupNo());
		}else{
		// 기존 임시 저장 글이 없는 경우
			// 신규 파일 그룹 생성. 파일 그룹의 기본 공개범위는 '비공개'. 추후 임시 저장글 > 글 수정 완료 시, 설정한 값에 따라 공개범위 수정
			FileGroupVO fileGroupVO = new FileGroupVO();
			fileGroupVO.setMemNo(memNo);
			fileGroupVO.setAuthgroup(Authgroup.SECRET);
			freeboardDAO.insertFileGrp(fileGroupVO);
			Integer fileGroupNo = fileGroupVO.getFileGroupNo();
			
			// 수정 대상글의 파일 상세 레코드 복제. 물리적 파일은 복제 없이 그대로 유지
			List<FileVO> originalFileVOList = uploadDAO.selectFileByFileGroupNo(freeboardDetailVO.getFileGroupNo());
			for(FileVO vo : originalFileVOList){
				vo.setFileGroupNo(fileGroupNo);
			}
			uploadDAO.insertFileList(originalFileVOList);
			
			// 새롭게 복제한 파일 목록 조회
			fileVOList = uploadDAO.selectFileByFileGroupNo(fileGroupNo);
			
			// FREEBOARD-MODIFY로 원본 글을 복제한 임시 저장 레코드 생성
			tempSaveVO = new TempSaveVO();
			tempSaveVO.setMemNo(memNo);
			tempSaveVO.setFileGroupNo(fileGroupNo);
			tempSaveVO.setTempSaveCategory(TempSaveCategory.FREEBOARD);
			tempSaveVO.setTempSaveUse(TempSaveUse.MODIFY);
			tempSaveVO.setTempSaveTitle(freeboardDetailVO.getFreeboardTitle());
			tempSaveVO.setTempSaveBody(freeboardDetailVO.getFreeboardBody());
			tempSaveDAO.insertTempSave(tempSaveVO);
		}
		
		// 생성한 레코드 정보 MODEL에 담아 VIEW로 반환
		mav.addObject("freeboardGroupNo", freeboardGroupNo);
		mav.addObject("tempSaveVO", tempSaveVO);
		mav.addObject("fileVOList", fileVOList);
		mav.setViewName("/community/freeboard/modify");
		return mav;
	}
}
