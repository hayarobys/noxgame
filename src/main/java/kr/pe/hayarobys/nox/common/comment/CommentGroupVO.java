package kr.pe.hayarobys.nox.common.comment;

import com.suph.security.core.enums.Flag;

public class CommentGroupVO{
	/** 댓글 그룹 일련 번호 */
	private Integer commentGroupNo;
	/** 댓글 그룹 신규 작성 플래그. Y면 신규 작성 가능, N이면 신규 작성 불가 */
	private Flag commentGroupNewWriteFlag;
	
	public Integer getCommentGroupNo(){
		return commentGroupNo;
	}
	
	public void setCommentGroupNo(Integer commentGroupNo){
		this.commentGroupNo = commentGroupNo;
	}
	
	public Flag getCommentGroupNewWriteFlag(){
		return commentGroupNewWriteFlag;
	}
	
	public void setCommentGroupNewWriteFlag(Flag commentGroupNewWriteFlag){
		this.commentGroupNewWriteFlag = commentGroupNewWriteFlag;
	}
	
	@Override
	public String toString(){
		return "CommentGroupVO [commentGroupNo=" + commentGroupNo + ", commentGroupNewWriteFlag=" + commentGroupNewWriteFlag + "]";
	}
}
