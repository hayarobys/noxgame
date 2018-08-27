package com.suph.security.core.dto;

import java.util.Date;

import com.suph.security.core.enums.Authgroup;

public class TestVO{
	private Integer fileGroupNo;
	
	private Integer memNo;
	
	private Authgroup authgroup;
	
	private Date fileGroupRegDate;

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

	public Authgroup getAuthgroup(){
		return authgroup;
	}

	public void setAuthgroup(Authgroup authgroup){
		this.authgroup = authgroup;
	}

	public Date getFileGroupRegDate(){
		return fileGroupRegDate;
	}

	public void setFileGroupRegDate(Date fileGroupRegDate){
		this.fileGroupRegDate = fileGroupRegDate;
	}

	@Override
	public String toString(){
		return "TestVO [fileGroupNo=" + fileGroupNo + ", memNo=" + memNo + ", authgroup=" + authgroup + "]";
	}
}
