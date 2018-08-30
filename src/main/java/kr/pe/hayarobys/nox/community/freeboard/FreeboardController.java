package kr.pe.hayarobys.nox.community.freeboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.suph.security.core.dto.JsonResultVO;
import com.suph.security.core.dto.PaginationRequest;
import com.suph.security.core.dto.PaginationResponse;
import com.suph.security.core.util.ContextUtil;

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
	public @ResponseBody PaginationResponse<FreeboardGroupDetailVO> getFreeboardGroupList(
			@RequestParam(name="pagenum", required=false, defaultValue="1") int pagenum,
			@RequestParam(name="pagesize", required=false, defaultValue="20") int pagesize
	){
		PaginationRequest paginationRequest = new PaginationRequest();
		paginationRequest.setPagenum(pagenum-1);
		paginationRequest.setPagesize(pagesize);
		
		return freeboardService.getFreeboardGroupList(paginationRequest);
	}
	
	/** 자유 게시판 신규 글 작성 페이지 */
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public ModelAndView getWrite(ModelAndView mav){
		return freeboardService.getFreeboardTempSaveNo(mav);
	}
	
	/** 자유 게시판 신규 글 등록 */
	@RequestMapping(value="/write", method=RequestMethod.POST)
	@ResponseBody public JsonResultVO<Integer> postWrite(@RequestBody TempSaveVO tempSaveVO){
		return freeboardService.postWrite(tempSaveVO);
	}
	
	/** 자유 게시판 모든 작성 취소 */
	@RequestMapping(value="/write-cancel", method=RequestMethod.PUT)
	public @ResponseBody void writeCancel(){
		Integer memberNo = ContextUtil.getMemberInfo().getNo();
		freeboardService.freeboardWriteCancel(memberNo);
	}
	
	/** 자유 게시판 상세 조회 */
	@RequestMapping(value="/{freeboardGroupNo}", method=RequestMethod.GET)
	public ModelAndView getDetail(
			@PathVariable(value="freeboardGroupNo", required=true) Integer freeboardGroupNo,
			ModelAndView mav
	){
		return freeboardService.getDetail(freeboardGroupNo, mav);
	}
	
	/** 자유 게시판 특정 글 수정 페이지 */
	@RequestMapping(value="/{freeboardGroupNo}/edit", method=RequestMethod.GET)
	public ModelAndView getModify(
			@PathVariable(value="freeboardGroupNo", required=true) Integer freeboardGroupNo,
			ModelAndView mav
	){
		return freeboardService.getModifyForm(freeboardGroupNo, mav);
	}
	
	/** 자유 게시판 특정 글 수정 */
	@RequestMapping(value="/{freeboardGroupNo}", method=RequestMethod.PATCH)
	public @ResponseBody void patchModify(
			@PathVariable(value="freeboardGroupNo", required=true) Integer freeboardGroupNo,
			@RequestBody TempSaveVO tempSaveVO
	){
		freeboardService.patchFreeboard(freeboardGroupNo, tempSaveVO);
	}
	
	/** 자유 게시판 모든 수정 취소 */
	@RequestMapping(value="/edit-cancel", method=RequestMethod.PUT)
	public @ResponseBody void editCancel(){
		Integer memberNo = ContextUtil.getMemberInfo().getNo();
		freeboardService.freeboardModifyCancel(memberNo);
	}
	
	/** 자유 게시판 특정 글 삭제 */
	@RequestMapping(value="/{freeboardGroupNo}", method=RequestMethod.DELETE)
	public void deleteFreeboardGroup(
			@PathVariable(value="freeboardGroupNo", required=true) Integer freeboardGroupNo
	){
		freeboardService.deleteFreeboardGroup(freeboardGroupNo);
	}
}
