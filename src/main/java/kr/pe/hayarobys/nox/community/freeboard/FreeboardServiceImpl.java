package kr.pe.hayarobys.nox.community.freeboard;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.suph.security.core.dto.JsonResultVO;
import com.suph.security.core.enums.Flag;
import com.suph.security.core.enums.TempSaveCategory;
import com.suph.security.core.userdetails.MemberInfo;
import com.suph.security.core.util.ContextUtil;

import kr.pe.hayarobys.nox.common.comment.CommentDAO;
import kr.pe.hayarobys.nox.common.comment.CommentGroupVO;
import kr.pe.hayarobys.nox.common.tempsave.TempSaveVO;
import kr.pe.hayarobys.nox.common.upload.FileGrpVO;
import kr.pe.hayarobys.nox.common.upload.FileVO;
import kr.pe.hayarobys.nox.common.upload.UploadDAO;

@Service
public class FreeboardServiceImpl implements FreeboardService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FreeboardDAO freeboardDAO;
	
	@Autowired
	private UploadDAO uploadDAO;
	
	@Autowired
	private CommentDAO commentDAO;
	
	@Override
	public ModelAndView getTempSaveNo(ModelAndView mav){
		MemberInfo memberInfo = ContextUtil.getMemberInfo();
		// 미로그인 유저여서 authentication이 null일 가능성은 security 단에서 1차적으로 차단한 상태이므로 부가 처리를 하지 않습니다.
		Integer memNo = memberInfo.getNo();
		
		// 기존에 FREEBOARD로 등록된 임시 저장글이 있는가?
		// 있다면 가장 최근에 작성한 임시 저장글의 일련 번호를 반환
		TempSaveVO lastTempSaveVO = freeboardDAO.selectLastTempSaveNoFromFreeboard(memNo);
		List<FileVO> fileVOList = null;
		if(lastTempSaveVO != null){
			logger.debug("마지막 임시저장글 발견: {}", lastTempSaveVO);
			fileVOList = uploadDAO.selectFileByFileGroupNo(lastTempSaveVO.getFileGrpNo());
		}else{
			// 파일 묶음 레코드 생성
			FileGrpVO fileGrpVO = new FileGrpVO();
			fileGrpVO.setMemNo(memNo);
			
			// TODO: 최소 조회 권한은 어찌할까? > 임시로 유저, 관리자, 매니저를 묶은 1번 권한 그룹으로 지정해 둡니다. 추후 필히 수정
			fileGrpVO.setAuthGrpNo(1);
			freeboardDAO.insertFileGrp(fileGrpVO);
			Integer fileGrpNo = fileGrpVO.getFileGrpNo();
			
			// 생성에 실패했다면 ?
			logger.debug("파일그룹넘버: {}", fileGrpNo);
			lastTempSaveVO = new TempSaveVO();
			lastTempSaveVO.setMemNo(memNo);
			lastTempSaveVO.setFileGrpNo(fileGrpNo);
			lastTempSaveVO.setTempSaveCategory(TempSaveCategory.FREEBOARD);
			lastTempSaveVO.setTempSaveTitle("");
			lastTempSaveVO.setTempSaveBody("");
			
			freeboardDAO.insertTempSave(lastTempSaveVO);
		}
		
		mav.addObject("lastTempSaveVO", lastTempSaveVO);
		mav.addObject("fileVOList", fileVOList);
		
		mav.setViewName("community/freeboard/write");
		return mav;
	}

	@Override
	public JsonResultVO<Integer> postWrite(TempSaveVO requestTempSaveVO){
		logger.debug("글 등록 요청: {}", requestTempSaveVO);
		
		// 요청 들어온 임시 저장 번호와 요청자의 계정 일련 번호 조회
		Integer tempSaveNo = requestTempSaveVO.getTempSaveNo();
		Integer memNo = ContextUtil.getMemberInfo().getNo();
		
		// 원본 임시 저장글로부터 파일 그룹 번호 조회
		requestTempSaveVO.setMemNo(memNo);
		Integer fileGroupNo = freeboardDAO.selectTempSaveByTempSaveNo(tempSaveNo);
		requestTempSaveVO.setFileGrpNo(fileGroupNo);
		
		// 댓글 그룹 생성
		CommentGroupVO commentGroupVO = new CommentGroupVO();
		commentGroupVO.setCommentGroupNewWriteFlag(Flag.Y);
		commentDAO.insertCommentGroup(commentGroupVO);
		
		// 자유 게시판 그룹 생성
		FreeboardGroupVO freeboardGroupVO = new FreeboardGroupVO();
		freeboardGroupVO.setMemNo(memNo);
		freeboardGroupVO.setCommentGroupNo(commentGroupVO.getCommentGroupNo());
		freeboardGroupVO.setFileGroupNo(fileGroupNo);
		freeboardGroupVO.setAuthGroupNo(1); // TODO: 권한그룹 수정할 것
		freeboardGroupVO.setFreeboardGroupClassOrder(0);
		freeboardGroupVO.setFreeboardGroupClassDepth(1);
		freeboardDAO.insertFreeboardGroup(freeboardGroupVO);
		
		// 자유 게시판 상세 생성
		FreeboardVO freeboardVO = new FreeboardVO();
		freeboardVO.setFreeboardGroupNo(freeboardGroupVO.getFileGroupNo());
		freeboardVO.setFreeboardTitle(requestTempSaveVO.getTempSaveTitle());
		freeboardVO.setFreeboardBody(requestTempSaveVO.getTempSaveBody());
		freeboardDAO.insertFreeboard(freeboardVO);
		
		// 임시 저장 데이터 제거
		freeboardDAO.deleteTempSaveByTempSaveNo(tempSaveNo);
		
		// 생성된 게시글 그룹 번호 반환
		JsonResultVO<Integer> jsonResultVO = new JsonResultVO<Integer>(freeboardGroupVO.getFreeboardGroupNo());
		return jsonResultVO;
	}
	
	
}
