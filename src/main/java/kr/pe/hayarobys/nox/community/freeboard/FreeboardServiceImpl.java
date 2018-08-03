package kr.pe.hayarobys.nox.community.freeboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.suph.security.core.userdetails.MemberInfo;
import com.suph.security.core.util.ContextUtil;

import kr.pe.hayarobys.nox.common.upload.FileGrpVO;
import kr.pe.hayarobys.nox.common.upload.TempSaveVO;

@Service
public class FreeboardServiceImpl implements FreeboardService{
	private static Logger logger = LoggerFactory.getLogger(FreeboardServiceImpl.class);
	
	@Autowired
	private FreeboardDAO freeboardDAO;
	
	@Override
	public ModelAndView getTempSaveSqPk(ModelAndView mav){
		MemberInfo memberInfo = ContextUtil.getMemberInfo();
		// vo가 null이면 어쩌지?
		Integer memNo = memberInfo.getNo();
		
		
		FileGrpVO fileGrpVO = new FileGrpVO();
		fileGrpVO.setMemNo(memNo);
		
		// 최소 조회 권한은 어찌할까?
		
		freeboardDAO.insertFileGrp(fileGrpVO);
		Integer fileGrpNo = fileGrpVO.getFileGrpNo();
		if(fileGrpNo == null){
			
			// 생성에 실패했다면 ?
			logger.debug("파일그룹넘버: {}", fileGrpNo);
		}
		
		TempSaveVO tempSaveVO = new TempSaveVO();
		tempSaveVO.setMemNo(memNo);
		tempSaveVO.setFileGrpNo(fileGrpNo);
		
		mav.addObject("tempSaveNo", fileGrpNo);
		mav.setViewName("community/freeboard/edit");
		return mav;
	}
}
