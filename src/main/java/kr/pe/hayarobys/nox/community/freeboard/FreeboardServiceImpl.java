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
		
		// 기존에 FREEBOARD로 등록된 임시 저장글이 있는가?
		// 있다면 가장 최근에 작성한 임시 저장글의 일련 번호를 반환
		TempSaveVO searchTempSaveVO = new TempSaveVO();
		searchTempSaveVO.setMemNo(memNo);
		searchTempSaveVO.setTempSaveCategory(TempSaveCategory.FREEBOARD);
		TempSaveVO lastTempSaveVO = tempSaveDAO.selectLastTempSaveNoFromCategory(searchTempSaveVO);
		List<FileVO> fileVOList = null;
		if(lastTempSaveVO != null){
			logger.debug("마지막 임시저장글 발견: {}", lastTempSaveVO);
			fileVOList = uploadDAO.selectFileByFileGroupNo(lastTempSaveVO.getFileGrpNo());
		}else{
			// 파일 묶음 레코드 생성
			FileGroupVO fileGrpVO = new FileGroupVO();
			fileGrpVO.setMemNo(memNo);
			
			// 파일 그룹의 기본 공개범위는 '비공개'. 추후 임시저장글 > 글 작성 완료시 설정한 값에 따라 공개범위 수정
			fileGrpVO.setAuthgroup(Authgroup.SECRET);
			freeboardDAO.insertFileGrp(fileGrpVO);
			Integer fileGrpNo = fileGrpVO.getFileGrpNo();
			
			logger.debug("파일그룹넘버: {}", fileGrpNo);
			lastTempSaveVO = new TempSaveVO();
			lastTempSaveVO.setMemNo(memNo);
			lastTempSaveVO.setFileGrpNo(fileGrpNo);
			lastTempSaveVO.setTempSaveCategory(TempSaveCategory.FREEBOARD);
			lastTempSaveVO.setTempSaveTitle("");
			lastTempSaveVO.setTempSaveBody("");
			
			tempSaveDAO.insertTempSave(lastTempSaveVO);
		}
		
		mav.addObject("lastTempSaveVO", lastTempSaveVO);
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
		Integer fileGroupNo = originalTempSaveVO.getFileGrpNo();
		requestTempSaveVO.setFileGrpNo(fileGroupNo);
		
		// 공개범위 미 설정 시 기본값은 전체공개
		Authgroup authgroup = requestTempSaveVO.getOpenType();
		if(authgroup == null){
			authgroup = Authgroup.PUBLIC;
		}
		
		// 파일 그룹의 공개 범위를 작성자가 요청한 공개 범위로 수정.
		FileGroupVO fileGroupVO = new FileGroupVO();
		fileGroupVO.setFileGrpNo(fileGroupNo);
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
		freeboardGroupVO.setFileGroupNo(fileGroupNo);
		freeboardGroupVO.setAuthgroup(authgroup);
		freeboardGroupVO.setFreeboardGroupClassOrder(0);
		freeboardGroupVO.setFreeboardGroupClassDepth(1);
		this.insertFreeboardGroup(freeboardGroupVO);
		
		// 자유 게시판 상세 생성
		FreeboardVO freeboardVO = new FreeboardVO();
		freeboardVO.setFreeboardGroupNo(freeboardGroupVO.getFreeboardGroupNo());
		freeboardVO.setFreeboardTitle(requestTempSaveVO.getTempSaveTitle());
		freeboardVO.setFreeboardBody(requestTempSaveVO.getTempSaveBody());
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
}
