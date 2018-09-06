package kr.pe.hayarobys.nox.community.freeboard;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.suph.security.core.dto.JsonResultVO;
import com.suph.security.core.dto.PaginationRequest;
import com.suph.security.core.dto.PaginationResponse;
import com.suph.security.core.enums.OpenType;
import com.suph.security.core.enums.TempSaveCategory;
import com.suph.security.core.enums.TempSaveUse;
import com.suph.security.core.userdetails.MemberInfo;
import com.suph.security.core.util.ContextUtil;

import kr.pe.hayarobys.nox.common.comment.CommentService;
import kr.pe.hayarobys.nox.common.exception.ForbiddenException;
import kr.pe.hayarobys.nox.common.tempsave.TempSaveService;
import kr.pe.hayarobys.nox.common.tempsave.TempSaveVO;
import kr.pe.hayarobys.nox.common.upload.FileVO;
import kr.pe.hayarobys.nox.common.upload.UploadService;

@Service
public class FreeboardServiceImpl implements FreeboardService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FreeboardDAO freeboardDAO;
	
	@Autowired
	private TempSaveService tempSaveService;
	
	@Autowired
	private UploadService uploadService;
	
	@Autowired
	private CommentService commentService;
	
	@Override
	public PaginationResponse<FreeboardGroupDetailVO> getFreeboardGroupList(PaginationRequest paginationRequest){
		List<FreeboardGroupDetailVO> list = freeboardDAO.getFreeboardGroupList(paginationRequest);
		int totalRows = freeboardDAO.getFreeboardGroupListTotalRows();
		
		return new PaginationResponse<FreeboardGroupDetailVO>(list, totalRows);
	}
	
	@Override
	public ModelAndView getFreeboardTempSaveNo(ModelAndView mav){
		MemberInfo memberInfo = ContextUtil.getMemberInfo();
		// 미로그인 유저여서 authentication이 null일 가능성은 security 단에서 1차적으로 차단한 상태이므로 부가 처리를 하지 않습니다.
		Integer memNo = memberInfo.getNo();
		
		// 기존에 FREEBOARD 카테로리에 신규 작성 용도로 등록된 임시 저장글이 있는가?
		// 있다면 가장 최근에 작성한 임시 저장글의 일련 번호를 반환
		TempSaveVO tempSaveVO = tempSaveService.selectLastTempSaveFromMemNoAndCategoryAndUse(memNo, TempSaveCategory.FREEBOARD, TempSaveUse.WRITE);
		
		List<FileVO> fileVOList = null;
		if(tempSaveVO != null){
			mav.addObject("isNew", false);
			fileVOList = uploadService.selectFileByFileGroupNo(tempSaveVO.getFileGroupNo());
		}else{
			mav.addObject("isNew", true);	// 과거 저장 글을 불러온것인지 여부. true면 새로 작성한 것.
			
			// 파일 그룹 생성. 기본 공개범위는 '비공개'. 추후 임시저장글 > 글 작성 완료시 설정한 값에 따라 공개범위 수정
			Integer fileGroupNo = uploadService.insertFileGroup(memNo, OpenType.SECRET);
			
			// 임시 저장 생성
			tempSaveVO = new TempSaveVO();
			tempSaveVO.setMemNo(memNo);
			tempSaveVO.setFileGroupNo(fileGroupNo);
			tempSaveVO.setTempSaveCategory(TempSaveCategory.FREEBOARD);
			tempSaveVO.setTempSaveUse(TempSaveUse.WRITE);
			tempSaveVO.setTempSaveTitle("");
			tempSaveVO.setTempSaveBody("");
			
			tempSaveService.insertTempSave(tempSaveVO);
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
		TempSaveVO originalTempSaveVO = tempSaveService.selectTempSaveByTempSaveNo(tempSaveNo);
		
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
		OpenType openType = requestTempSaveVO.getOpenType();
		if(openType == null){
			openType = OpenType.PUBLIC;
		}
		
		// 파일 그룹의 공개 범위를 작성자가 요청한 공개 범위로 수정.
		uploadService.updateOpenTypeOfFileGroupByFileGroupNo(fileGroupNo, openType);
		
		// 댓글 그룹 생성
		Integer commentGroupNo = commentService.insertCommentGroup(memNo, requestTempSaveVO.getAllowComment());
		
		// 자유 게시판 그룹 생성
		FreeboardGroupVO freeboardGroupVO = new FreeboardGroupVO();
		freeboardGroupVO.setMemNo(memNo);
		freeboardGroupVO.setCommentGroupNo(commentGroupNo);
		freeboardGroupVO.setOpenType(openType);
		this.insertFreeboardGroup(freeboardGroupVO);
		
		// 자유 게시판 상세 생성
		FreeboardVO freeboardVO = new FreeboardVO();
		freeboardVO.setFreeboardGroupNo(freeboardGroupVO.getFreeboardGroupNo());
		freeboardVO.setFreeboardTitle(requestTempSaveVO.getTempSaveTitle());
		freeboardVO.setFreeboardBody(requestTempSaveVO.getTempSaveBody());
		freeboardVO.setFileGroupNo(fileGroupNo);
		freeboardDAO.insertFreeboard(freeboardVO);
		
		// 임시 저장 데이터 제거
		tempSaveService.deleteTempSaveByTempSaveNo(tempSaveNo);
		
		// 생성된 게시글 그룹 번호 반환
		JsonResultVO<Integer> jsonResultVO = new JsonResultVO<Integer>(freeboardGroupVO.getFreeboardGroupNo());
		return jsonResultVO;
	}
	
	@Override
	public void freeboardWriteCancel(Integer memNo){
		tempSaveService.deleteTempSaveByMemNoAndCategoryAndUse(memNo, TempSaveCategory.FREEBOARD, TempSaveUse.WRITE);
	}
	
	@Override
	public ModelAndView getDetail(Integer freeboardGroupNo, ModelAndView mav){
		FreeboardDetailVO freeboardDetailVO = freeboardDAO.selectLastFreeboardDetail(freeboardGroupNo);
		
		// TODO: 요청자가 이 게시글의 최소 조회 권한을 통과하는지 확인 할 것
		
		// TODO: 작성자 본인이 아니라면 조회수 + 1
		
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
	}

	@Override
	public ModelAndView getModifyForm(Integer freeboardGroupNo, ModelAndView mav){
		// 수정 대상글의 원본 정보 조회
		FreeboardDetailVO freeboardDetailVO = freeboardDAO.selectLastFreeboardDetail(freeboardGroupNo);
		
		// 요청자와 수정 대상글의 계정 정보가 일치하는지 확인
		Integer memNo = ContextUtil.getMemberInfo().getNo();
		if(memNo != freeboardDetailVO.getMemNo()){
			logger.debug("수정 요청자: {}, 수정 대상 글 작성자: {}", memNo, freeboardDetailVO.getMemNo());
			throw new ForbiddenException("수정 대상 글의 작성자와 수정 요청자의 계정 정보가 불일치 합니다.");
		}
		
		// FREEBOARD 카테고리에 MODIFY 용도로 생성된 요청자의 임시 저장글이 있는지 확인
		TempSaveVO tempSaveVO = tempSaveService.selectLastTempSaveFromMemNoAndCategoryAndUse(memNo, TempSaveCategory.FREEBOARD, TempSaveUse.MODIFY);
		
		List<FileVO> fileVOList = null;
		// 기존 임시 저장 글이 있는 경우 해당 글의 첨부파일 목록 조회
		if(tempSaveVO != null){
			fileVOList = uploadService.selectFileByFileGroupNo(tempSaveVO.getFileGroupNo());
		}else{
		// 기존 임시 저장 글이 없는 경우
			// 신규 파일 그룹 생성. 파일 그룹의 기본 공개범위는 '비공개'. 추후 임시 저장글 > 글 수정 완료 시, 설정한 값에 따라 공개범위 수정
			Integer fileGroupNo = uploadService.insertFileGroup(memNo, OpenType.SECRET);
			
			// 수정 대상글의 파일 상세 레코드 복제. 물리적 파일은 복제 없이 그대로 유지
			List<FileVO> originalFileVOList = uploadService.selectFileByFileGroupNo(freeboardDetailVO.getFileGroupNo());
			if(originalFileVOList.size() > 0){
				for(FileVO vo : originalFileVOList){
					vo.setFileGroupNo(fileGroupNo);
				}
				uploadService.insertFileList(originalFileVOList);
			}
			
			// 새롭게 복제한 파일 목록 조회
			fileVOList = uploadService.selectFileByFileGroupNo(fileGroupNo);
			
			// FREEBOARD-MODIFY로 원본 글을 복제한 임시 저장 레코드 생성
			tempSaveVO = new TempSaveVO();
			tempSaveVO.setMemNo(memNo);
			tempSaveVO.setFileGroupNo(fileGroupNo);
			tempSaveVO.setTempSaveCategory(TempSaveCategory.FREEBOARD);
			tempSaveVO.setTempSaveUse(TempSaveUse.MODIFY);
			tempSaveVO.setTempSaveTitle(freeboardDetailVO.getFreeboardTitle());
			tempSaveVO.setTempSaveBody(freeboardDetailVO.getFreeboardBody());
			tempSaveService.insertTempSave(tempSaveVO);
		}
		
		// 최소 조회 권한과 댓글 허용 여부 입력
		tempSaveVO.setOpenType(freeboardDetailVO.getOpenType());
		tempSaveVO.setAllowComment(freeboardDetailVO.getAllowComment());
		
		// 생성한 레코드 정보 MODEL에 담아 VIEW로 반환
		mav.addObject("freeboardGroupNo", freeboardGroupNo);
		mav.addObject("tempSaveVO", tempSaveVO);
		mav.addObject("fileVOList", fileVOList);
		mav.setViewName("/community/freeboard/modify");
		return mav;
	}
	
	@Override
	public void patchFreeboard(Integer freeboardGroupNo, TempSaveVO tempSaveVO){
		// 요청자 확인
		MemberInfo memberInfo = ContextUtil.getMemberInfo();
		
		// 수정 대상이 되는 게시글 그룹의 작성자 조회
		Integer memNo = freeboardDAO.selectMemNoFromFreeboardGroupByFreeboardGroupNo(freeboardGroupNo);
		
		// 계정 일치 여부 확인
		if(memberInfo.getNo() != memNo){
			throw new ForbiddenException("요청자와 수정 대상글의 등록자가 불일치 합니다."); // 남의 글은 수정 할 수 없습니다.
		}
		
		// 원본 임시 저장글의 파일 그룹 번호 조회
		Integer fileGroupNo = tempSaveService.selectFileGroupNoFromTempSaveByTempSaveNo(tempSaveVO.getTempSaveNo());
		
		// 자유 게시판 그룹의 최소 조회 권한 수정
		updateOpenTypeOfFreeboardGroupByFreeboardGroupNo(freeboardGroupNo, tempSaveVO.getOpenType());
		
		// 원본 임시 저장 제거. 단, 연결된 파일은 제외
		tempSaveService.deleteTempSaveByTempSaveNo(tempSaveVO.getTempSaveNo());
		
		// 자유 게시판 상세 추가
		FreeboardVO freeboardVO = new FreeboardVO();
		freeboardVO.setFreeboardGroupNo(freeboardGroupNo);
		freeboardVO.setFileGroupNo(fileGroupNo);
		freeboardVO.setFreeboardTitle(tempSaveVO.getTempSaveTitle());
		freeboardVO.setFreeboardBody(tempSaveVO.getTempSaveBody());
		freeboardDAO.insertFreeboard(freeboardVO);
		
		// 이 자유 게시판 그룹에 속한 모든 파일 그룹 조회
		List<Integer> fileGroupNoList = freeboardDAO.selectFileGroupFromFreeboardGroupByFreeobardGroupNo(freeboardGroupNo);
		
		// 이 파일 그룹 목록의 최소 조회 권한 일괄 수정
		uploadService.updateOpenTypeOfFileGroupByFileGroupNoList(fileGroupNoList, tempSaveVO.getOpenType());
		
		// 연결된 파일들 임시 플래그를 false로 일괄 변경
		uploadService.updateTempFlagOfFileByFileGroupNo(fileGroupNo, false);
		
		// 수정 대상이 되는 게시글 그룹의 댓글 그룹 번호 조회
		Integer commentGroupNo = freeboardDAO.selectCommentGroupNoFromFreeboardGroupByFreeboardGroupNo(freeboardGroupNo);
		
		// 댓글 그룹의 신규 작성 가능 여부 갱신
		commentService.updateAllowCommentOfCommentGroupByCommentGroupNo(commentGroupNo, tempSaveVO.getAllowComment());
	}
	
	@Override
	public void updateOpenTypeOfFreeboardGroupByFreeboardGroupNo(Integer freeboardGroupNo, OpenType openType){
		FreeboardGroupVO freeboardGroupVO = new FreeboardGroupVO();
		freeboardGroupVO.setFreeboardGroupNo(freeboardGroupNo);
		freeboardGroupVO.setOpenType(openType);
		freeboardDAO.updateAuthgroupOfFreeboardGroupByFreeboardGroupNo(freeboardGroupVO);
	}
	
	@Override
	public void freeboardModifyCancel(Integer memNo){
		tempSaveService.deleteTempSaveByMemNoAndCategoryAndUse(memNo, TempSaveCategory.FREEBOARD, TempSaveUse.MODIFY);
	}
	
	@Override
	public void deleteFreeboardGroup(Integer freeboardGroupNo){
		// 자유게시판 그룹에 연결된 댓글 그룹 번호 조회
		Integer commentGroupNo = freeboardDAO.selectCommentGroupNoFromFreeboardGroupByFreeboardGroupNo(freeboardGroupNo);
		
		// 자유게시판 상세 목록에 연결된 각 파일 그룹 목록 조회
		List<Integer> fileGroupNoList = freeboardDAO.selectFileGroupFromFreeboardGroupByFreeobardGroupNo(freeboardGroupNo);
		
		// 자유게시판 상세 목록 제거
		freeboardDAO.deleteFreeboardByFreeboardGroupNo(freeboardGroupNo);
		
		// 이 파일 그룹들에 연결된 모든 파일과 물리 파일 제거 서비스 요청
		uploadService.deleteFileGroupByFileGroupNoList(fileGroupNoList);
		
		// 자유게시판 그룹 제거
		freeboardDAO.deleteFreeboardGroupByFreeboardGroupNo(freeboardGroupNo);
		
		// 댓글 제거 서비스 요청
		commentService.deleteCommentGroup(commentGroupNo);
	}	
}
