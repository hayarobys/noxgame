package kr.pe.hayarobys.nox.common.proj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/proj")
public class ProjTestController{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ProjTestService projTestService;
	
	/** 자유 게시판 글 목록 */
	@RequestMapping(value="/test", method=RequestMethod.GET)
	@ResponseBody public String getFreeboardGroupList(
			@RequestParam(name="longitude") double longitude,
			@RequestParam(name="latitude") double latitude
	){
		return projTestService.test(longitude, latitude);
	}
}
