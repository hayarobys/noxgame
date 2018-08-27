package kr.pe.hayarobys.nox.common.upload;

import org.springframework.web.multipart.MultipartFile;

public class SmartEditorImageFileVO{
	private int fileGroupNo;
	private String callback;
	private String callback_func;
	private MultipartFile filedata;
	private Boolean tempFlag;
	
	public int getFileGroupNo(){
		return fileGroupNo;
	}

	public void setFileGroupNo(int fileGroupNo){
		this.fileGroupNo = fileGroupNo;
	}

	public String getCallback(){
		return callback;
	}
	
	public void setCallback(String callback){
		this.callback = callback;
	}
	
	public String getCallback_func(){
		return callback_func;
	}
	
	public void setCallback_func(String callback_func){
		this.callback_func = callback_func;
	}
	
	public MultipartFile getFiledata(){
		return filedata;
	}
	
	public void setFiledata(MultipartFile filedata){
		this.filedata = filedata;
	}

	public Boolean getTempFlag(){
		return tempFlag;
	}

	public void setTempFlag(Boolean tempFlag){
		this.tempFlag = tempFlag;
	}

	@Override
	public String toString(){
		return "SmartEditorImageFileVO [fileGroupNo=" + fileGroupNo + ", callback=" + callback + ", callback_func=" + callback_func + ", filedata="
				+ filedata + ", tempFlag=" + tempFlag + "]";
	}
}
