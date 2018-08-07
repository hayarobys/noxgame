package kr.pe.hayarobys.nox.common.upload;

import java.util.Date;

public class FileVO{
	private int fileNo;
	private int fileGrpNo;
	private int memNo;
	private Date fileRegDt;
	private int downloadCount;
	private Long fileSize;
	private String extensionName;
	private String originalFileName;
	private String saveFileName;
	private String fileSaveDirectory;
	
	public int getFileNo(){
		return fileNo;
	}

	public void setFileNo(int fileNo){
		this.fileNo = fileNo;
	}

	public int getFileGrpNo(){
		return fileGrpNo;
	}

	public void setFileGrpNo(int fileGrpNo){
		this.fileGrpNo = fileGrpNo;
	}

	public int getMemNo(){
		return memNo;
	}

	public void setMemNo(int memNo){
		this.memNo = memNo;
	}

	public Date getFileRegDt(){
		return fileRegDt;
	}

	public void setFileRegDt(Date fileRegDt){
		this.fileRegDt = fileRegDt;
	}

	public int getDownloadCount(){
		return downloadCount;
	}

	public void setDownloadCount(int downloadCount){
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
		return "FileVO [fileNo=" + fileNo + ", fileGrpNo=" + fileGrpNo + ", memNo=" + memNo + ", fileRegDt=" + fileRegDt
				+ ", downloadCount=" + downloadCount + ", fileSize=" + fileSize + ", extensionName=" + extensionName
				+ ", originalFileName=" + originalFileName + ", saveFileName=" + saveFileName + ", fileSaveDirectory="
				+ fileSaveDirectory + "]";
	}
}
