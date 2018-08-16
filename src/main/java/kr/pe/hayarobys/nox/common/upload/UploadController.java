package kr.pe.hayarobys.nox.common.upload;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UploadController{
	//private static Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	@Autowired
	private UploadService uploadService;
	
	/**
	 * 네이버 스마트 에디터2의 포토 업로드 화면을 보여줍니다.
	 * @param fileGroupNo
	 * @param mav
	 * @return
	 */
	@RequestMapping(value="/upload/{fileGroupNo}/photo/edit", method=RequestMethod.GET)
	public ModelAndView uploadEdit(
			@PathVariable(value="fileGroupNo", required=true) String fileGroupNo,
			ModelAndView mav
	){
		// 서비스에 넘겨서 본인 여부 확인 할 것
		mav.addObject("fileGroupNo", fileGroupNo);
		mav.setViewName("common/upload/photo_uploader");
		return mav;
	}
	
	/**
	 * Form 형식의 SmartEditor 이미지 업로드
	 * @param fileVO
	 */
	@RequestMapping(value="/upload/{fileGroupNo}/photo/form", method=RequestMethod.POST, headers=("content-type=multipart/*"))
	public String imageUploadSmartEditorByForm(
			@PathVariable(value="fileGroupNo", required=true) String fileGroupNo,
			SmartEditorImageFileVO fileVO, HttpServletRequest request
	){
			fileVO.setFileGroupNo(Integer.parseInt(fileGroupNo));
			return uploadService.imageUploadSmartEditorByForm(fileVO);
		
	}

	/**
	 * HTML5 지원 브라우저에서 스마트 에디터를 이용해 application/x-www-form-urlencoded; charset=utf-8 타입으로 전송되는 이미지를 받습니다.
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/upload/{fileGroupNo}/photo/ajax", method=RequestMethod.POST)
	@ResponseBody public void imageUploadSmartEditorByStream(
			@PathVariable(value="fileGroupNo", required=true) String fileGroupNo
	){
		uploadService.imageUploadSmartEditorByStream(Integer.parseInt(fileGroupNo));
	}
}
