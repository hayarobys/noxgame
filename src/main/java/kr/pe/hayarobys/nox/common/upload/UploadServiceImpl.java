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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.suph.security.core.util.ContextUtil;

@Service
public class UploadServiceImpl implements UploadService{
	private static Logger logger = LoggerFactory.getLogger(UploadServiceImpl.class);
	
	@Override
	public void insertFile(File file){
		// TODO Auto-generated method stub

	}

	@Override
	public String imageUploadSmartEditorByForm(ImageFileVO imageFileVO){
		StringBuilder result = new StringBuilder();
		result.append("redirect:");
		result.append(imageFileVO.getCallback());
		result.append("?callback_func=");
		result.append(imageFileVO.getCallback_func());
		
		MultipartFile file = imageFileVO.getFiledata();
		if(		file != null
			&&	file.getOriginalFilename() != null
			&&	!file.getOriginalFilename().equals("")
		){
			result.append("&errstr=error");
			return result.toString();
		}
		
		HttpServletRequest request	= ContextUtil.getCurrentRequest();
		
		long fileSize			= file.getSize();
		String originalFileName	= file.getOriginalFilename();
		String fileExtension	= originalFileName.substring(originalFileName.lastIndexOf(".")+1);
		String projectPath		= request.getSession().getServletContext().getRealPath(File.separator);
		String uploadPath		= projectPath + File.separator + "resources" + File.separator + "upload" + File.separator;
		File folder				= new File(uploadPath);
		
		// 폴더 생성
		if( !folder.exists() ){ folder.mkdirs(); }
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String today		= formatter.format(new java.util.Date());
		String saveFileName	= today + UUID.randomUUID().toString() + "." + fileExtension;
		
		try{
			// 파일 묶음 일련 번호와 요청자의 일
			// DB에 메타 정보 입력
			FileVO fileVO = new FileVO();
			//fileVO.setFileGrpNo(fileGrpNo);
			fileVO.setFileSize(fileSize);
			fileVO.setExtensionName(fileExtension);
			fileVO.setOriginalFileName(originalFileName);
			fileVO.setSaveFileName(saveFileName);
			fileVO.setFileSaveDirectory(uploadPath);
			
			// 서버에 파일쓰기
			file.transferTo(
					new File(uploadPath + saveFileName)
			);
			
			logger.debug("ImageUpload - SaveName: {}, Path: {}", saveFileName, uploadPath);
			
			// 반환값 정리
			result.append("&bNewLine=true&sFileName=");										// 이미지 사이에 간격주기
												result.append(originalFileName);			// img 태그의 title 속성에 쓰일 원본 파일명
			result.append("&sFileURL=");		result.append(request.getContextPath());	// img 태그의 src 속성에 쓰일 저장 경로
												result.append("/resources/upload/");
												result.append(saveFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result.toString();
	}

	@Override
	public void imageUploadSmartEditorByStream(){
		HttpServletRequest request		= ContextUtil.getCurrentRequest();
		HttpServletResponse response	= ContextUtil.getCurrentResponse();
		
		String originalFileName	= request.getHeader("file-name");											// 원본 파일 명
		String fileExtension	= originalFileName.substring( originalFileName.lastIndexOf(".") + 1 ).toLowerCase();	// 확장자를 소문자로 변경
		String projectPath		= request.getSession().getServletContext().getRealPath(File.separator);		// 파일 기본경로
		String uploadPath		= projectPath + File.separator + "resources" + File.separator + "upload" + File.separator;	// 파일 상세경로
		File folder				= new File(uploadPath);
		
		// 폴더 생성
		if( !folder.exists() ){ folder.mkdirs(); }
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String today		= formatter.format(new java.util.Date());
		String saveFileName	= today + UUID.randomUUID().toString() + "." + fileExtension;
		
		try{
			// DB에 메타 정보 입력
			
			
			// 서버에 파일쓰기
			InputStream is	= request.getInputStream();
			OutputStream os	= new FileOutputStream(uploadPath + saveFileName);
			
			int numRead;
			byte b[] = new byte[ Integer.parseInt(request.getHeader("file-size")) ];
			while( (numRead = is.read(b, 0, b.length)) != -1 ){
				os.write(b, 0, numRead);
			}
			
			if( is != null ){ is.close(); }
			os.flush();
			os.close();
			
			logger.debug("ImageUpload - SaveName: {}, Path: {}", saveFileName, uploadPath);
			
			// 반환값 정리
			StringBuilder result = new StringBuilder();
			result.append("&bNewLine=true&sFileName=");										// 이미지 사이에 간격주기
												result.append(originalFileName);			// img 태그의 title 속성에 쓰일 원본 파일명
			result.append("&sFileURL=");		result.append(request.getContextPath());	// img 태그의 src 속성에 쓰일 저장 경로
												result.append("/resources/upload/");
												result.append(saveFileName);
						
			PrintWriter print = response.getWriter();
			print.print(result.toString());
			print.flush();
			print.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
