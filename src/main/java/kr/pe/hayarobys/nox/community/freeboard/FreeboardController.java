package kr.pe.hayarobys.nox.community.freeboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.suph.security.core.dto.JsonResultVO;

import kr.pe.hayarobys.nox.common.tempsave.TempSaveVO;

@Controller
@RequestMapping(value="/community/freeboard")
public class FreeboardController{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FreeboardService freeboardService;
	
	/** 자유 게시판 목록 페이지 */
	@RequestMapping(method=RequestMethod.GET)
	public String freeboard() {
		return "community/freeboard/freeboard";
	}
	
	/** 자유 게시판 글 목록 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public void getList(){
		
	}
	
	/** 자유 게시판 신규 글 작성 페이지 */
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public ModelAndView getWrite(ModelAndView mav){
		return freeboardService.getTempSaveNo(mav);
	}
	
	/** 자유 게시판 신규 글 등록 */
	@RequestMapping(value="/write", method=RequestMethod.POST)
	@ResponseBody public JsonResultVO<Integer> postWrite(@RequestBody TempSaveVO tempSaveVO){
		return freeboardService.postWrite(tempSaveVO);
	}
	
	/** 자유 게시판 특정 글 수정 페이지 */
	@RequestMapping(value="/{frbrdGrpNo}/write", method=RequestMethod.GET)
	public void getWrite(){
		
	}
	
	/** 자유 게시판 상세 조회 */
	@RequestMapping(value="/{frbrdGrpNo}", method=RequestMethod.GET)
	public void getDetail(){
		
	}
	
	/** 자유 게시판 특정 글 수정 */
	@RequestMapping(value="/{frbrdGrpNo}/write", method=RequestMethod.PATCH)
	public void patchWrite(){
		
	}
	
	/** 자유 게시판 특정 글 삭제 */
	@RequestMapping(value="/{frbrdGrpNo}", method=RequestMethod.DELETE)
	public void deleteDetail(){
		
	}
	
	
}
