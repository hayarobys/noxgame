package kr.pe.hayarobys.nox.community.freeboard;

public class FreeboardVO{
	/** 자유게시판 상세 일련 번호 */
	private Integer freeboardGroupNo;
	/** 자유게시판 상세 제목 */
	private String freeboardTitle;
	/** 자유게시판 상세 본문 */
	private String freeboardBody;
	/** 자유게시판 파일 묶음 일련 번호 */
	private Integer fileGroupNo;
	
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
	
	public Integer getFileGroupNo(){
		return fileGroupNo;
	}
	
	public void setFileGroupNo(Integer fileGroupNo){
		this.fileGroupNo = fileGroupNo;
	}

	@Override
	public String toString(){
		return "FreeboardVO [freeboardGroupNo=" + freeboardGroupNo + ", freeboardTitle=" + freeboardTitle + ", freeboardBody=" + freeboardBody
				+ ", fileGroupNo=" + fileGroupNo + "]";
	}
}
