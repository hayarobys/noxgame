package kr.pe.hayarobys.nox.common.comment;

public class CommentGroupVO{
	/** 댓글 그룹 일련 번호 */
	private Integer commentGroupNo;
	/** 댓글 그룹 생성 계정의 번호 */
	private Integer memNo;
	
	public Integer getMemNo(){
		return memNo;
	}

	public void setMemNo(Integer memNo){
		this.memNo = memNo;
	}

	/** 댓글 그룹 신규 작성 플래그. Y면 신규 작성 가능, N이면 신규 작성 불가 */
	private Boolean commentGroupNewWriteFlag;

	public Integer getCommentGroupNo(){
		return commentGroupNo;
	}

	public void setCommentGroupNo(Integer commentGroupNo){
		this.commentGroupNo = commentGroupNo;
	}

	public Boolean getCommentGroupNewWriteFlag(){
		return commentGroupNewWriteFlag;
	}

	public void setCommentGroupNewWriteFlag(Boolean commentGroupNewWriteFlag){
		this.commentGroupNewWriteFlag = commentGroupNewWriteFlag;
	}

	@Override
	public String toString(){
		return "CommentGroupVO [commentGroupNo=" + commentGroupNo + ", memNo=" + memNo + ", commentGroupNewWriteFlag="
				+ commentGroupNewWriteFlag + "]";
	}
}
