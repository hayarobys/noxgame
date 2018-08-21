package com.suph.security.core.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.suph.security.core.dto.JsonResultVO;

import kr.pe.hayarobys.nox.common.tempsave.TempSaveVO;

@Controller
public class TestController{
	private Logger logger = LoggerFactory.getLogger(TestController.class);
	
	/**
	 * 레이어 팝업 예제 화면으로 이동합니다.
	 * @return
	 */
	@RequestMapping(value="/test/layer-popup", method=RequestMethod.GET)
	public String admin(){
		return "test/layer-popup";
	}
	
	@RequestMapping(value="/test/echo/{message}", method=RequestMethod.GET)
	private @ResponseBody Map<String, Object> echoMessage(@PathVariable(required=true) String message){
		logger.debug("message: {}", message);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("result", message);
		return returnMap;
	}
	
	/** 자유 게시판 신규 글 작성 페이지 */
	@RequestMapping(value="/test/community/freeboard/write", method=RequestMethod.GET)
	public String testGetWrite(ModelAndView mav){
		return "test/write";
	}
	
	/** 자유 게시판 신규 글 등록 */
	@RequestMapping(value="/test/community/freeboard/write", method=RequestMethod.POST)
	@ResponseBody public JsonResultVO<String> testPostWrite(@RequestBody TempSaveVO tempSaveVO){
		logger.debug("tempSaveVO: {}", tempSaveVO.toString());
		return new JsonResultVO<String>("success");
	}
}
