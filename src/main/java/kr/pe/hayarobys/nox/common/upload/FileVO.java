package kr.pe.hayarobys.nox.common.upload;

import java.util.Date;

public class FileVO{
	/** 파일 번호 */
	private Integer fileNo;
	/** 이 파일이 속해있는 파일 그룹의 번호 */
	private Integer fileGroupNo;
	/** 이 파일을 등록한 계정의 계정 번호 */
	private Integer memNo;
	/** 등록일 */
	private Date fileRegDt;
	/** 다운로드 횟수 */
	private Integer downloadCount;
	/** 파일 크기 */
	private Long fileSize;
	/** 확장자. MIME TYPE 아님 */
	private String extensionName;
	/** 원본 파일명 */
	private String originalFileName;
	/** 저장하기 위해 변경한 파일 명 */
	private String saveFileName;
	/** 물리 저장 경로 */
	private String fileSaveDirectory;
	
	public Integer getFileNo(){
		return fileNo;
	}

	public void setFileNo(Integer fileNo){
		this.fileNo = fileNo;
	}

	public Integer getFileGroupNo(){
		return fileGroupNo;
	}

	public void setFileGroupNo(Integer fileGroupNo){
		this.fileGroupNo = fileGroupNo;
	}

	public Integer getMemNo(){
		return memNo;
	}

	public void setMemNo(Integer memNo){
		this.memNo = memNo;
	}

	public Date getFileRegDt(){
		return fileRegDt;
	}

	public void setFileRegDt(Date fileRegDt){
		this.fileRegDt = fileRegDt;
	}

	public Integer getDownloadCount(){
		return downloadCount;
	}

	public void setDownloadCount(Integer downloadCount){
		this.downloadCount = downloadCount;
	}

	public Long getFileSize(){
		return fileSize;
	}

	public void setFileSize(Long fileSize){
		this.fileSize = fileSize;
	}

	public String getExtensionName(){
		return extensionName;
	}

	public void setExtensionName(String extensionName){
		this.extensionName = extensionName;
	}

	public String getOriginalFileName(){
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName){
		this.originalFileName = originalFileName;
	}

	public String getSaveFileName(){
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName){
		this.saveFileName = saveFileName;
	}

	public String getFileSaveDirectory(){
		return fileSaveDirectory;
	}

	public void setFileSaveDirectory(String fileSaveDirectory){
		this.fileSaveDirectory = fileSaveDirectory;
	}

	@Override
	public String toString(){
		return "FileVO [fileNo=" + fileNo + ", fileGroupNo=" + fileGroupNo + ", memNo=" + memNo + ", fileRegDt=" + fileRegDt + ", downloadCount="
				+ downloadCount + ", fileSize=" + fileSize + ", extensionName=" + extensionName + ", originalFileName=" + originalFileName
				+ ", saveFileName=" + saveFileName + ", fileSaveDirectory=" + fileSaveDirectory + "]";
	}
}
