package com.hayarobys.noxgame.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/main")
public class MainController{
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping(value="/message", method = RequestMethod.GET)
	public @ResponseBody MainVO getMessage(){
		MainVO vo = new MainVO();
		vo.setMessage("안녕!");
		vo.setResult("success");
		
		logger.debug("message {}", vo);
		return vo;
	}
}
