package kr.pe.hayarobys.nox.common.comment;

import com.suph.security.core.enums.OpenType;

public class CommentVO{
	// 신규 등록시 필요한 변수들
	private Integer commentGroupNo;
	private Integer memNo;
	private Integer fileGroupNo;
	private OpenType openType;
	private Boolean commentSecretFlag;
	private String commentBody;
	
	// 답글 등록시 필요한 변수들
	private Integer commentNo;
	private Integer commentClassNo;
	private Integer commentClassOrder;
	private Integer commentClassDepth;
	
	public Integer getCommentGroupNo(){
		return commentGroupNo;
	}
	public void setCommentGroupNo(Integer commentGroupNo){
		this.commentGroupNo = commentGroupNo;
	}
	public Integer getMemNo(){
		return memNo;
	}
	public void setMemNo(Integer memNo){
		this.memNo = memNo;
	}
	public Integer getFileGroupNo(){
		return fileGroupNo;
	}
	public void setFileGroupNo(Integer fileGroupNo){
		this.fileGroupNo = fileGroupNo;
	}
	
	public OpenType getOpenType(){
		return openType;
	}
	public void setOpenType(OpenType openType){
		this.openType = openType;
	}
	public Boolean getCommentSecretFlag(){
		return commentSecretFlag;
	}
	public void setCommentSecretFlag(Boolean commentSecretFlag){
		this.commentSecretFlag = commentSecretFlag;
	}
	public String getCommentBody(){
		return commentBody;
	}
	public void setCommentBody(String commentBody){
		this.commentBody = commentBody;
	}
	
	public Integer getCommentNo(){
		return commentNo;
	}
	public void setCommentNo(Integer commentNo){
		this.commentNo = commentNo;
	}
	public Integer getCommentClassNo(){
		return commentClassNo;
	}
	public void setCommentClassNo(Integer commentClassNo){
		this.commentClassNo = commentClassNo;
	}
	public Integer getCommentClassOrder(){
		return commentClassOrder;
	}
	public void setCommentClassOrder(Integer commentClassOrder){
		this.commentClassOrder = commentClassOrder;
	}
	public Integer getCommentClassDepth(){
		return commentClassDepth;
	}
	public void setCommentClassDepth(Integer commentClassDepth){
		this.commentClassDepth = commentClassDepth;
	}
	
	@Override
	public String toString(){
		return "CommentVO [commentGroupNo=" + commentGroupNo + ", memNo=" + memNo + ", fileGroupNo=" + fileGroupNo
				+ ", openType=" + openType + ", commentSecretFlag=" + commentSecretFlag + ", commentBody=" + commentBody
				+ ", commentNo=" + commentNo + ", commentClassNo=" + commentClassNo + ", commentClassOrder="
				+ commentClassOrder + ", commentClassDepth=" + commentClassDepth + "]";
	}
}