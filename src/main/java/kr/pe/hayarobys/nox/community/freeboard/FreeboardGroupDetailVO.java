package kr.pe.hayarobys.nox.community.freeboard;

import java.util.Date;

import com.suph.security.core.enums.OpenType;

/** 자유게시판 목록에서 개별 항목을 상세히 표시하기 위한 VO 입니다. */
public class FreeboardGroupDetailVO{
	/** 자유게시판 그룹 등록 계정의 일련 번호 */
	private Integer memNo;
	/** 자유게시판 그룹 등록 계정의 닉네임 */
	private String memNickname;
	
	/** 자유게시판 그룹 일련 번호 */
	private Integer freeboardGroupNo;
	/** 자유게시판 그룹의 최소 조회 권한 */
	private OpenType openType;
	/** 자유게시판 그룹 등록 날짜 */
	private Date freeboardGroupRegDate;
	/** 자유게시판 그룹 조회수 */
	private Integer hits;
	
	/** 자유게시판 상세 제목 */
	private String freeboardTitle;
	/** 마지막 자유게시판 상세 등록일(최종 수정일) */
	private Date freeboardLastRegDate;
	
	/** 댓글 개수 */
	private Integer commentCount;

	public Integer getMemNo(){
		return memNo;
	}

	public void setMemNo(Integer memNo){
		this.memNo = memNo;
	}

	public String getMemNickname(){
		return memNickname;
	}

	public void setMemNickname(String memNickname){
		this.memNickname = memNickname;
	}

	public Integer getFreeboardGroupNo(){
		return freeboardGroupNo;
	}

	public void setFreeboardGroupNo(Integer freeboardGroupNo){
		this.freeboardGroupNo = freeboardGroupNo;
	}

	public OpenType getOpenType(){
		return openType;
	}

	public void setOpenType(OpenType openType){
		this.openType = openType;
	}

	public Date getFreeboardGroupRegDate(){
		return freeboardGroupRegDate;
	}

	public void setFreeboardGroupRegDate(Date freeboardGroupRegDate){
		this.freeboardGroupRegDate = freeboardGroupRegDate;
	}

	public Integer getHits(){
		return hits;
	}

	public void setHits(Integer hits){
		this.hits = hits;
	}

	public String getFreeboardTitle(){
		return freeboardTitle;
	}

	public void setFreeboardTitle(String freeboardTitle){
		this.freeboardTitle = freeboardTitle;
	}

	public Date getFreeboardLastRegDate(){
		return freeboardLastRegDate;
	}

	public void setFreeboardLastRegDate(Date freeboardLastRegDate){
		this.freeboardLastRegDate = freeboardLastRegDate;
	}

	public Integer getCommentCount(){
		return commentCount;
	}

	public void setCommentCount(Integer commentCount){
		this.commentCount = commentCount;
	}

	@Override
	public String toString(){
		return "FreeboardGroupDetailVO [memNo=" + memNo + ", memNickname=" + memNickname + ", freeboardGroupNo="
				+ freeboardGroupNo + ", openType=" + openType + ", freeboardGroupRegDate=" + freeboardGroupRegDate
				+ ", hits=" + hits + ", freeboardTitle=" + freeboardTitle + ", freeboardLastRegDate="
				+ freeboardLastRegDate + ", commentCount=" + commentCount + "]";
	}	
}
