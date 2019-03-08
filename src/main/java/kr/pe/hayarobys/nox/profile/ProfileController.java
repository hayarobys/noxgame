package kr.pe.hayarobys.nox.profile;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/profile")
public class ProfileController{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ProfileService profileService;
	
	/** 프로필 상세 조회 페이지 */
	@RequestMapping(value="/{memNo}", method=RequestMethod.GET)
	public ModelAndView getProfile(
			@RequestParam(name="memNo", required=false) Integer memNo,
			ModelAndView mav
	){
		Map<String, Object> objectMap = profileService.getProfile(memNo);
		if(objectMap != null){
			mav.addAllObjects(objectMap);
		}
		mav.setViewName("profile/profile");
		
		return mav;
	}
	
}
