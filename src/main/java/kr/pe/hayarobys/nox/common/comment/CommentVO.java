package kr.pe.hayarobys.nox.common.comment;

import com.suph.security.core.enums.Authgroup;

public class CommentVO{
	private Integer commentGroupNo;
	private Integer memNo;
	private Integer fileGroupNo;
	private Authgroup authgroup;
	private Boolean commentSecretFlag;
	private String commentBody;
	
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
	public Authgroup getAuthgroup(){
		return authgroup;
	}
	public void setAuthgroup(Authgroup authgroup){
		this.authgroup = authgroup;
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
	@Override
	public String toString(){
		return "CommentVO [commentGroupNo=" + commentGroupNo + ", memNo=" + memNo + ", fileGroupNo=" + fileGroupNo + ", authgroup=" + authgroup
				+ ", commentSecretFlag=" + commentSecretFlag + ", commentBody=" + commentBody + "]";
	}
}
