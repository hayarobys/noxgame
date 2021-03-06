package kr.pe.hayarobys.nox.common.upload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.suph.security.core.enums.OpenType;
import com.suph.security.core.util.ContextUtil;

import kr.pe.hayarobys.nox.common.exception.ForbiddenException;
import kr.pe.hayarobys.nox.common.exception.InternalServerErrorException;

@Service
public class UploadServiceImpl implements UploadService{
	private static Logger logger = LoggerFactory.getLogger(UploadServiceImpl.class);
	
	@Autowired
	private UploadDAO uploadDAO;
	
	@Override
	public List<FileVO> selectFileByFileGroupNo(Integer fileGroupNo){
		return uploadDAO.selectFileByFileGroupNo(fileGroupNo);
	}
	
	@Override
	public void updateOpenTypeOfFileGroupByFileGroupNo(Integer fileGroupNo, OpenType openType){
		FileGroupVO fileGroupVO = new FileGroupVO();
		fileGroupVO.setFileGroupNo(fileGroupNo);
		fileGroupVO.setOpenType(openType);
		uploadDAO.updateAuthgroupOfFileGroupByFileGroupNo(fileGroupVO);
	}
	
	@Override
	public void updateOpenTypeOfFileGroupByFileGroupNoList(List<Integer> fileGroupNoList, OpenType openType){
		UpdateOpenTypeByListVO updateAuthgroupByListVO = new UpdateOpenTypeByListVO();
		updateAuthgroupByListVO.setList(fileGroupNoList);
		updateAuthgroupByListVO.setOpenType(openType);
		uploadDAO.updateAuthgroupOfFileGroupByFileGroupNoList(updateAuthgroupByListVO);
	}
	
	/**
	 * 물리적 경로의 파일을 제거합니다.
	 * @param fileDir
	 */
	private void deleteLocalFileByFileDir(String fileDir){
		File file = new File(fileDir);
		if(file.exists() == true){
			file.delete();
		}
	}
	
	@Override
	public void updateTempFlagOfFileByFileGroupNo(Integer fileGroupNo, Boolean tempFlag){
		FileVO fileVO = new FileVO();
		fileVO.setFileGroupNo(fileGroupNo);
		fileVO.setTempFlag(tempFlag);
		uploadDAO.updateTempFlagOfFileByFileGroupNo(fileVO);
	}
	
	@Override
	public void deleteFileByFileGroupNo(Integer fileGroupNo, Boolean deleteTempOnly){
		// 파일 그룹 번호와 연결된 파일 목록 조회
		List<FileVO> fileList = selectFileByFileGroupNo(fileGroupNo);
		
		// 파일 목록을 순회하며 물리 파일 제거
		if(deleteTempOnly == true){
			for(FileVO fileVO : fileList){
				if(fileVO.getTempFlag() == true){
					deleteLocalFileByFileDir(fileVO.getFileSaveDirectory() + fileVO.getSaveFileName());
				}
			}
		}else{
			for(FileVO fileVO : fileList){
				deleteLocalFileByFileDir(fileVO.getFileSaveDirectory() + fileVO.getSaveFileName());
			}
		}
		
		// DB상에서 파일 몽땅 제거
		uploadDAO.deleteFileByFileGroupNo(fileGroupNo);
		
		// DB상에서 파일 그룹 제거
		uploadDAO.deleteFileGroupByFileGroupNo(fileGroupNo);
	}
	
	@Override
	public void deleteFileGroupByFileGroupNoList(List<Integer> fileGroupNoList){		
		// 파일 목록 조회
		List<FileVO> fileList = uploadDAO.selectFileByFileGroupNoList(fileGroupNoList);
		
		// 파일 목록들의 모든 물리 파일 제거
		List<Integer> fileNoList = new ArrayList<Integer>();
		for(FileVO fileVO : fileList){
			fileNoList.add(fileVO.getFileNo());
			if(fileVO.getTempFlag() == true){
				deleteLocalFileByFileDir(fileVO.getFileSaveDirectory() + fileVO.getSaveFileName());
			}
		}
		
		// DB에서 파일 목록 제거
		if(fileList.size() > 0){
			uploadDAO.deleteFileByFileNoList(fileNoList);
		}
		
		// DB에서 파일 그룹 목록 제거
		uploadDAO.deleteFileGroupByFileGroupNoList(fileGroupNoList);
	}
	
	@Override
	public void insertFileList(List<FileVO> fileList){
		uploadDAO.insertFileList(fileList);
	}
	
	@Override
	public Integer insertFileGroup(Integer memNo, OpenType openType){
		// TODO: 정합성 검사
		FileGroupVO fileGroupVO = new FileGroupVO();
		fileGroupVO.setMemNo(memNo);
		fileGroupVO.setOpenType(openType);
		uploadDAO.insertFileGroup(fileGroupVO);
		return fileGroupVO.getFileGroupNo();
	}
	
	/**
	 * 파일 정보를 DB에 넣습니다.
	 * - 파일 레코드 생성
	 * - 파일 레코드의 일련 번호 반환
	 * @param fileVO
	 */
	private void insertFile(FileVO fileVO){
		// TODO: 정합성 검사
		uploadDAO.insertFile(fileVO);
	}

	@Override
	public String imageUploadSmartEditorByForm(SmartEditorImageFileVO imageFileVO){
		// 파일 묶음 일련 번호와 요청자의 일치 여부 확인
		Integer memNoOfFileGroup = uploadDAO.selectMemNoOfFileGroupByFileGroupNo(imageFileVO.getFileGroupNo());
		Integer memNoOfRequester = ContextUtil.getMemberInfo().getNo();
		
		if(!memNoOfRequester.equals(memNoOfFileGroup)){
			logger.debug("요청자: {}, 대상 파일 그룹의 등록자: {}", memNoOfRequester, memNoOfFileGroup);
			throw new ForbiddenException("대상 파일 그룹의 등록자와 업로드 요청자의 계정 정보가 불일치 합니다.");
		}
		
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
		
		// 업로드 요청된 파일의 정보 확인
		HttpServletRequest request	= ContextUtil.getCurrentRequest();
		
		long fileSize			= file.getSize();
		String originalFileName	= file.getOriginalFilename();
		String fileExtension	= originalFileName.substring(originalFileName.lastIndexOf(".")+1);
		String projectPath		= request.getSession().getServletContext().getRealPath(File.separator);

		SimpleDateFormat formatterFull	= new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat formatterYear	= new SimpleDateFormat("yyyy");
		SimpleDateFormat formatterMonth	= new SimpleDateFormat("MM");
		SimpleDateFormat formatterDay	= new SimpleDateFormat("dd");
		
		Date nowTime		= new java.util.Date();
		String today		= formatterFull.format(nowTime);
		String saveFileName	= today + "-" + UUID.randomUUID().toString() + "." + fileExtension; // 저장하기위해 변경한 파일 명
		
		StringBuffer uploadPathBuffer = new StringBuffer();
		uploadPathBuffer.append(File.separator);
		uploadPathBuffer.append("resources");
		uploadPathBuffer.append(File.separator);
		uploadPathBuffer.append("upload");
		uploadPathBuffer.append(File.separator);
		uploadPathBuffer.append(formatterYear.format(nowTime));
		uploadPathBuffer.append(File.separator);
		uploadPathBuffer.append(formatterMonth.format(nowTime));
		uploadPathBuffer.append(File.separator);
		uploadPathBuffer.append(formatterDay.format(nowTime));
		uploadPathBuffer.append(File.separator);
		
		String uploadPath = projectPath + uploadPathBuffer.toString(); // 파일 상세경로
		
		// 폴더 생성
		File folder = new File(uploadPath);
		if( !folder.exists() ){ folder.mkdirs(); }
		
		// DB에 파일 정보 입력
		FileVO fileVO = new FileVO();
		fileVO.setFileGroupNo(imageFileVO.getFileGroupNo());
		fileVO.setMemNo(ContextUtil.getMemberInfo().getNo());
		fileVO.setTempFlag(imageFileVO.getTempFlag());
		fileVO.setFileSize(fileSize);
		fileVO.setExtensionName(fileExtension);
		fileVO.setOriginalFileName(originalFileName);
		fileVO.setSaveFileName(saveFileName);
		fileVO.setFileSaveDirectory(uploadPath);
		
		logger.debug("파일 정보: {}", fileVO);
		
		try{	
			insertFile(fileVO);
		}catch(Exception e){
			throw new InternalServerErrorException("업로드 실패 - 데이터베이스 기록 실패");
		}
		
		// 서버에 파일쓰기
		try{
			file.transferTo(
					new File(uploadPath + saveFileName)
			);
		}catch(Exception e){
			throw new InternalServerErrorException("업로드 실패 - 물리 저장소 쓰기 실패");
		}
		
		logger.debug("ImageUpload - SaveName: {}, Path: {}", saveFileName, uploadPath);
		
		// 반환값 정리
		result.append("&bNewLine=true&sFileName=");	// 이미지 사이에 간격주기
		result.append(originalFileName);			// img 태그의 title 속성에 쓰일 원본 파일명
		result.append("&sFileURL=");
		result.append(request.getContextPath());	// img 태그의 src 속성에 쓰일 저장 경로
		result.append(uploadPathBuffer.toString().replaceAll(Matcher.quoteReplacement(File.separator), "/"));
		result.append(saveFileName);
		result.append("&sFileNo=");
		result.append(fileVO.getFileNo());
		result.append("&sSaveFileName=");
		result.append(saveFileName);
	
		return result.toString();
	}

	@Override
	public void imageUploadSmartEditorByStream(Integer fileGroupNo){
		// 파일 묶음 일련 번호와 요청자의 일치 여부 확인
		Integer memNoOfFileGroup = uploadDAO.selectMemNoOfFileGroupByFileGroupNo(fileGroupNo);
		Integer memNoOfRequester = ContextUtil.getMemberInfo().getNo();
		
		if(!memNoOfRequester.equals(memNoOfFileGroup)){
			logger.debug("요청자: {}, 대상 파일 그룹의 등록자: {}", memNoOfRequester, memNoOfFileGroup);
			throw new ForbiddenException("대상 파일 그룹의 등록자와 업로드 요청자의 계정 정보가 불일치 합니다.");
		}
		
		// 업로드 요청된 파일의 정보 확인
		HttpServletRequest request		= ContextUtil.getCurrentRequest();
		HttpServletResponse response	= ContextUtil.getCurrentResponse();
		
		Boolean tempFlag		= Boolean.parseBoolean(request.getHeader("file-temp"));
		Long fileSize			= Long.parseLong(request.getHeader("file-size"));
		//String fileType			= request.getHeader("file-Type");
		String originalFileName	= request.getHeader("file-name"); // 원본 파일 명
		String fileExtension	= originalFileName.substring( originalFileName.lastIndexOf(".") + 1 ).toLowerCase(); // 확장자를 소문자로 변경
		String projectPath		= request.getSession().getServletContext().getRealPath(File.separator); // 파일 기본경로
		
		SimpleDateFormat formatterFull	= new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat formatterYear	= new SimpleDateFormat("yyyy");
		SimpleDateFormat formatterMonth	= new SimpleDateFormat("MM");
		SimpleDateFormat formatterDay	= new SimpleDateFormat("dd");
		
		Date nowTime		= new java.util.Date();
		String today		= formatterFull.format(nowTime);
		String saveFileName	= today + "-" + UUID.randomUUID().toString() + "." + fileExtension; // 저장하기위해 변경한 파일 명
		
		StringBuffer uploadPathBuffer = new StringBuffer();
		uploadPathBuffer.append(File.separator);
		uploadPathBuffer.append("resources");
		uploadPathBuffer.append(File.separator);
		uploadPathBuffer.append("upload");
		uploadPathBuffer.append(File.separator);
		uploadPathBuffer.append(formatterYear.format(nowTime));
		uploadPathBuffer.append(File.separator);
		uploadPathBuffer.append(formatterMonth.format(nowTime));
		uploadPathBuffer.append(File.separator);
		uploadPathBuffer.append(formatterDay.format(nowTime));
		uploadPathBuffer.append(File.separator);
		
		String uploadPath = projectPath + uploadPathBuffer.toString(); // 파일 상세경로
		
		// 폴더 생성
		File folder = new File(uploadPath);
		if( !folder.exists() ){ folder.mkdirs(); }
		
		// DB에 파일 정보 입력
		FileVO fileVO = new FileVO();
		fileVO.setFileGroupNo(fileGroupNo);
		fileVO.setMemNo(memNoOfRequester);
		fileVO.setTempFlag(tempFlag);
		fileVO.setFileSize(fileSize);
		fileVO.setExtensionName(fileExtension);
		fileVO.setOriginalFileName(originalFileName);
		fileVO.setSaveFileName(saveFileName);
		fileVO.setFileSaveDirectory(uploadPath);
		
		logger.debug("파일 정보: {}", fileVO);
		
		try{
			insertFile(fileVO);	
		}catch(Exception e){
			throw new InternalServerErrorException("업로드 실패 - 데이터베이스 기록 실패");
		}
		
		// 서버에 파일쓰기
		InputStream is = null;
		OutputStream os = null;
		try{
			is	= new BufferedInputStream( request.getInputStream() );
			os	= new BufferedOutputStream( new FileOutputStream(uploadPath + saveFileName) );
			
			int numRead;
			byte b[] = new byte[ Integer.parseInt(request.getHeader("file-size")) ];
			while( (numRead = is.read(b, 0, b.length)) != -1 ){
				os.write(b, 0, numRead);
			}
			
			if( is != null ){ is.close(); }
			os.flush();
		}catch(Exception e){
			throw new InternalServerErrorException("업로드 실패 - 물리 저장소 쓰기 실패");
		}finally{
			try{ is.close(); }catch(IOException e){e.printStackTrace();}
			try{ os.close(); }catch(IOException e){e.printStackTrace();}
		}
		
		logger.debug("ImageUpload - SaveName: {}, Path: {}", saveFileName, uploadPath);
		
		// 저장 경로 문자열로 정리
		StringBuilder result = new StringBuilder();
		result.append("&bNewLine=true&sFileName=");	// 이미지 사이에 간격주기
		result.append(originalFileName);			// img 태그의 title 속성에 쓰일 원본 파일명
		result.append("&sFileURL=");				// &sFileURL=/nox/resources/upload/20180809102839dbb939e5-a3c2-4062-bd1d-eea17d2146e9.jpg
		result.append(request.getContextPath());	// img 태그의 src 속성에 쓰일 저장 경로
		result.append(uploadPathBuffer.toString().replaceAll(Matcher.quoteReplacement(File.separator), "/"));
		result.append(saveFileName);
		result.append("&sFileNo=");
		result.append(fileVO.getFileNo());
		result.append("&sSaveFileName=");
		result.append(fileVO.getSaveFileName());
		
		// 저장 경로 반환
		try( PrintWriter print = response.getWriter() ){
			print.print(result.toString());
			print.flush();
		}catch(Exception e){
			throw new InternalServerErrorException("저장 경로 반환 실패");
		}
	}
}
