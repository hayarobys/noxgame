package com.suph.security.core.dto;

import java.util.Date;

import com.suph.security.core.enums.OpenType;

public class TestVO{
	private Integer fileGroupNo;
	
	private Integer memNo;
	
	private OpenType openType;
	
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

	public OpenType getOpenType(){
		return openType;
	}

	public void setOpenType(OpenType openType){
		this.openType = openType;
	}

	public Date getFileGroupRegDate(){
		return fileGroupRegDate;
	}

	public void setFileGroupRegDate(Date fileGroupRegDate){
		this.fileGroupRegDate = fileGroupRegDate;
	}

	@Override
	public String toString(){
		return "TestVO [fileGroupNo=" + fileGroupNo + ", memNo=" + memNo + ", fileGroupRegDate=" + fileGroupRegDate
				+ "]";
	}

	
}
