package kr.pe.hayarobys.nox.community.freeboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.suph.security.core.enums.TempSaveCategory;
import com.suph.security.core.userdetails.MemberInfo;
import com.suph.security.core.util.ContextUtil;

import kr.pe.hayarobys.nox.common.tempsave.TempSaveVO;
import kr.pe.hayarobys.nox.common.upload.FileGrpVO;

@Service
public class FreeboardServiceImpl implements FreeboardService{
	protected Logger logger = LoggerFactory.getLogger(FreeboardServiceImpl.class);
	
	@Autowired
	private FreeboardDAO freeboardDAO;
	
	@Override
	public ModelAndView getTempSaveNo(ModelAndView mav){
		MemberInfo memberInfo = ContextUtil.getMemberInfo();
		// 미로그인 유저여서 authentication이 null일 가능성은 security 단에서 1차적으로 차단한 상태이므로 부가 처리를 하지 않습니다.
		Integer memNo = memberInfo.getNo();
		
		// 기존에 FREEBOARD로 등록된 임시 저장글이 있는가?
		// 있다면 가장 최근에 작성한 임시 저장글의 일련 번호를 반환
		TempSaveVO lastTempSaveVO = freeboardDAO.selectLastTempSaveNoFromFreeboard(memNo);
		if(lastTempSaveVO != null){
			logger.debug("마지막 임시저장글 발견: {}", lastTempSaveVO);
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
		mav.setViewName("community/freeboard/write");
		return mav;
	}
}
