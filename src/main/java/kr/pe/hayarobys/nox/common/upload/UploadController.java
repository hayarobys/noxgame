package kr.pe.hayarobys.nox.common.upload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UploadController{
	//private static Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	@Autowired
	private UploadService uploadService;
	
	@RequestMapping(value="/upload/photo/edit", method=RequestMethod.GET)
	public String uploadEdit(){
		return "common/upload/photo_uploader";
	}
	
	/**
	 * Form 형식의 SmartEditor 이미지 업로드
	 * @param fileVO
	 */
	@RequestMapping(value="/upload/photo", method=RequestMethod.POST, headers=("content-type=multipart/*"))
	public String imageUploadSmartEditorByForm(ImageFileVO fileVO, HttpServletRequest request){
			return uploadService.imageUploadSmartEditorByForm(fileVO);
		
	}

	/**
	 * HTML5 지원 브라우저에서 스마트 에디터를 이용해 application/x-www-form-urlencoded; charset=utf-8 타입으로 전송되는 이미지를 받습니다.
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/upload/photos", method=RequestMethod.POST)
	public void imageUploadSmartEditorByStream(
			HttpServletResponse response
	){
		uploadService.imageUploadSmartEditorByStream();
	}
}
