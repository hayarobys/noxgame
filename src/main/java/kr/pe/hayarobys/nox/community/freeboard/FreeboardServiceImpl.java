package kr.pe.hayarobys.nox.community.freeboard;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class FreeboardServiceImpl implements FreeboardService{
	
	
	@Override
	public ModelAndView getTempSaveSqPk(ModelAndView mav){
		
		
		mav.addObject("tempSaveSqPk", "123");
		mav.setViewName("community/freeboard/edit");
		return mav;
	}
}
