package kr.pe.hayarobys.nox.community.freeboard;

public class FreeboardVO{
	/** 자유게시판 상세 일련 번호 */
	private Integer freeboardGroupNo;
	/** 자유게시판 상세 제목 */
	private String freeboardTitle;
	/** 자유게시판 상세 본문 */
	private String freeboardBody;
	
	public Integer getFreeboardGroupNo(){
		return freeboardGroupNo;
	}
	
	public void setFreeboardGroupNo(Integer freeboardGroupNo){
		this.freeboardGroupNo = freeboardGroupNo;
	}
	
	public String getFreeboardTitle(){
		return freeboardTitle;
	}
	
	public void setFreeboardTitle(String freeboardTitle){
		this.freeboardTitle = freeboardTitle;
	}
	
	public String getFreeboardBody(){
		return freeboardBody;
	}
	
	public void setFreeboardBody(String freeboardBody){
		this.freeboardBody = freeboardBody;
	}
	
	@Override
	public String toString(){
		return "FreeboardVO [freeboardGroupNo=" + freeboardGroupNo + ", freeboardTitle=" + freeboardTitle + ", freeboardBody=" + freeboardBody + "]";
	}
}
