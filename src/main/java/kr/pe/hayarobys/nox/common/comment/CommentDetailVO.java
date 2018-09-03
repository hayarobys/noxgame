package kr.pe.hayarobys.nox.common.comment;

import java.util.Date;

import com.suph.security.core.enums.Authgroup;

/**
 * 각각의 댓글 정보를 종합해 CommentGroupDetailVO에 담기 위한 클래스 입니다.
 */
public class CommentDetailVO{
	/** 댓글 번호 */
	private Integer commentNo;
	
	/** 계정 번호 */
	private Integer memNo;
	/** 계정 닉네임 */
	private String nickname;
	
	/** 파일 그룹 번호 */
	private Integer fileGroupNo;
	/** 최소 조회 권한 */
	private Authgroup authgroup;
	
	/** 댓글 계층 번호 */
	private Integer commentClassNo;
	/** 댓글 계층 깊이 default 1 */
	private Integer commentClassDepth;
	
	/** 댓글 등록일 */
	private Date commentRegDate;
	/** 댓글 수정일 */
	private Date commentModDate;
	/** 댓글 내용 */
	private String commentBody;

	public Integer getCommentNo(){
		return commentNo;
	}

	public void setCommentNo(Integer commentNo){
		this.commentNo = commentNo;
	}

	public Integer getMemNo(){
		return memNo;
	}

	public void setMemNo(Integer memNo){
		this.memNo = memNo;
	}

	public String getNickname(){
		return nickname;
	}

	public void setNickname(String nickname){
		this.nickname = nickname;
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

	public Integer getCommentClassNo(){
		return commentClassNo;
	}

	public void setCommentClassNo(Integer commentClassNo){
		this.commentClassNo = commentClassNo;
	}

	public Integer getCommentClassDepth(){
		return commentClassDepth;
	}

	public void setCommentClassDepth(Integer commentClassDepth){
		this.commentClassDepth = commentClassDepth;
	}

	public Date getCommentRegDate(){
		return commentRegDate;
	}

	public void setCommentRegDate(Date commentRegDate){
		this.commentRegDate = commentRegDate;
	}

	public Date getCommentModDate(){
		return commentModDate;
	}

	public void setCommentModDate(Date commentModDate){
		this.commentModDate = commentModDate;
	}

	public String getCommentBody(){
		return commentBody;
	}

	public void setCommentBody(String commentBody){
		this.commentBody = commentBody;
	}

	@Override
	public String toString(){
		return "CommentDetailVO [commentNo=" + commentNo + ", memNo=" + memNo + ", nickname=" + nickname + ", fileGroupNo=" + fileGroupNo
				+ ", commentClassNo=" + commentClassNo + ", commentClassDepth=" + commentClassDepth + ", commentBody=" + commentBody + "]";
	}
}
