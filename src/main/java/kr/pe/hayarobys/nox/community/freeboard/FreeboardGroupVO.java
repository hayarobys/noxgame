package kr.pe.hayarobys.nox.community.freeboard;

import com.suph.security.core.enums.Authgroup;

/** 자유게시판 묶음 테이블 */
public class FreeboardGroupVO{
	/** 자유게시판 묶음 일련 번호 */
	private Integer freeboardGroupNo;
	/** 자유게시판 묶음 등록 계정의 일련 번호 */
	private Integer memNo;
	/** 자유게시판 댓글 묶음 일련 번호 */
	private Integer commentGroupNo;
	/** 자유게시판 권한 묶음 일련 번호(공개범위, 최소 조회 권한) */
	private Authgroup authgroup;
	
	/** 자유게시판 묶음 계층 번호 */
	private Integer freeboardGroupClassNo;
	/** 자유게시판 묶음 계층 내 순서 */
	private Integer freeboardGroupClassOrder;
	/** 자유게시판 묶음 계층 내 깊이 */
	private Integer freeboardGroupClassDepth;
	
	public Integer getFreeboardGroupNo(){
		return freeboardGroupNo;
	}
	public void setFreeboardGroupNo(Integer freeboardGroupNo){
		this.freeboardGroupNo = freeboardGroupNo;
	}
	public Integer getMemNo(){
		return memNo;
	}
	public void setMemNo(Integer memNo){
		this.memNo = memNo;
	}
	public Integer getCommentGroupNo(){
		return commentGroupNo;
	}
	public void setCommentGroupNo(Integer commentGroupNo){
		this.commentGroupNo = commentGroupNo;
	}
	public Authgroup getAuthgroup(){
		return authgroup;
	}
	public void setAuthgroup(Authgroup authgroup){
		this.authgroup = authgroup;
	}
	public Integer getFreeboardGroupClassNo(){
		return freeboardGroupClassNo;
	}
	public void setFreeboardGroupClassNo(Integer freeboardGroupClassNo){
		this.freeboardGroupClassNo = freeboardGroupClassNo;
	}
	public Integer getFreeboardGroupClassOrder(){
		return freeboardGroupClassOrder;
	}
	public void setFreeboardGroupClassOrder(Integer freeboardGroupClassOrder){
		this.freeboardGroupClassOrder = freeboardGroupClassOrder;
	}
	public Integer getFreeboardGroupClassDepth(){
		return freeboardGroupClassDepth;
	}
	public void setFreeboardGroupClassDepth(Integer freeboardGroupClassDepth){
		this.freeboardGroupClassDepth = freeboardGroupClassDepth;
	}
	@Override
	public String toString(){
		return "FreeboardGroupVO [freeboardGroupNo=" + freeboardGroupNo + ", memNo=" + memNo + ", commentGroupNo=" + commentGroupNo + ", authgroup="
				+ authgroup + ", freeboardGroupClassNo=" + freeboardGroupClassNo + ", freeboardGroupClassOrder=" + freeboardGroupClassOrder
				+ ", freeboardGroupClassDepth=" + freeboardGroupClassDepth + "]";
	}
}
