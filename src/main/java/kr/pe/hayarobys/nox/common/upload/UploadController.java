package kr.pe.hayarobys.nox.common.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UploadController{
	private static Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	@RequestMapping(value="/upload/photo/edit", method=RequestMethod.GET)
	public String uploadEdit(){
		return "common/upload/photo_uploader";
	}
	
	/**
	 * 단일 파일 업로드
	 * @param fileVO
	 */
	@RequestMapping(value="/upload/photo", method=RequestMethod.POST, headers=("content-type=multipart/*"))
	public String singleUpload(ImageFileVO fileVO, HttpServletRequest request){
		
		String callback = fileVO.getCallback();
        String callback_func = fileVO.getCallback_func();
        String file_result = "";
        try {
            if(
            		fileVO.getFiledata() != null &&
            		fileVO.getFiledata().getOriginalFilename() != null &&
            		!fileVO.getFiledata().getOriginalFilename().equals("")
            ){
                //파일이 존재하면
                String original_name = fileVO.getFiledata().getOriginalFilename();
                String ext = original_name.substring(original_name.lastIndexOf(".")+1);
                //파일 기본경로
                String defaultPath = request.getSession().getServletContext().getRealPath("/");
                //파일 기본경로 _ 상세경로
                String path = defaultPath + "resources" + File.separator + "upload" + File.separator;              
                File file = new File(path);
                
                logger.debug("저장경로: {}", path);
                
                //디렉토리 존재하지 않을경우 디렉토리 생성
                if(!file.exists()) {
                    file.mkdirs();
                }
                //서버에 업로드 할 파일명(한글문제로 인해 원본파일은 올리지 않는것이 좋음)
                String realname = UUID.randomUUID().toString() + "." + ext;
            ///////////////// 서버에 파일쓰기 ///////////////// 
                fileVO.getFiledata().transferTo(new File(path+realname));
                file_result += "&bNewLine=true&sFileName=" + original_name + "&sFileURL=/resources/upload/" + realname;
            } else {
                file_result += "&errstr=error";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:" + callback + "?callback_func=" + callback_func + file_result;
	}
	
	/**
	 * HTML5 지원 브라우저에서 application/x-www-form-urlencoded; charset=utf-8 타입으로 전송되는 이미지를 받습니다.
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/upload/photos", method=RequestMethod.POST)
	public void multiUpload(
			HttpServletRequest request,
			HttpServletResponse response
	){
		
		String sFileInfo	= ""; // 파일 정보
		String filename		= request.getHeader("file-name"); // 원본 파일 명
		String filename_ext	= filename.substring(filename.lastIndexOf(".")+1).toLowerCase(); // 확장자를 소문자로 변경
		String dftFilePath	= request.getSession().getServletContext().getRealPath("/"); // 파일 기본경로
		String filePath		= dftFilePath + "resources" + File.separator + "upload" + File.separator; // 파일 상세경로
		File file			= new File(filePath);
		
		// 폴더 생성
		if( !file.exists() ){ file.mkdirs(); }
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String today		= formatter.format(new java.util.Date());
		String saveFileNm	= today + UUID.randomUUID().toString();
		
		try{	
			// 서버에 파일쓰기
			InputStream is	= request.getInputStream();
			OutputStream os	= new FileOutputStream(filePath + saveFileNm + "." + filename_ext);
			
			int numRead;
			byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
			while((numRead = is.read(b, 0, b.length)) != -1){
				os.write(b, 0, numRead);
			}
			
			if( is != null ){ is.close(); }
			os.flush();
			os.close();
			
			logger.debug("ImageUpload - Name: {}, Path: {}", saveFileNm + filename_ext, filePath);
			
			// 반환값 정리
			sFileInfo += "&bNewLine=true"; // 이미지 사이에 간격주기
			sFileInfo += "&sFileName=" + filename; // img 태그의 title 속성에 쓰일 원본 파일명
			sFileInfo += "&sFileURL=" + request.getContextPath() + "/resources/upload/"  + saveFileNm + "." + filename_ext;
			
			PrintWriter print = response.getWriter();
			print.print(sFileInfo);
			print.flush();
			print.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
