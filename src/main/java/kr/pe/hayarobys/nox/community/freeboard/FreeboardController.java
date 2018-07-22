package kr.pe.hayarobys.nox.community.freeboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/community/freeboard")
public class FreeboardController{
	private static Logger logger = LoggerFactory.getLogger(FreeboardController.class);
	
	@Autowired
	private FreeboardService freeboardService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String freeboard() {
		return "community/freeboard/freeboard";
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public String edit() {
		
		
		return "community/freeboard/edit";
	}
	
	
}
