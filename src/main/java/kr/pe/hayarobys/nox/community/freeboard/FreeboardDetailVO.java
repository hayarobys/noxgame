package kr.pe.hayarobys.nox.community.freeboard;

import java.util.Date;

/**
 * 자유 게시판 그룹 테이블과 자유 게시판 상세 테이블을 조인해 게시글 상세 화면 출력에 필요한 정보만 옮기는데 사용됩니다.
 * @author hayarobys
 *
 */
public class FreeboardDetailVO{
	/** 계정 일련 번호 */
	private Integer memNo;
	/** 글쓴이의 닉네임 */
	private String nickname;
	/** 글쓴이의 아이디 */
	private String id;
	
	/** 자유 게시판 그룹 번호 */
	private Integer freeboardGroupNo;
	/** 파일 그룹 번호 */
	private Integer fileGroupNo;
	/** 댓글 그룹 번호 */
	private Integer commentGroupNo;
	/** 최소 조회 권한 그룹 번호 */
	private Integer authGroupNo;
	
	/** 게시글 번호 */
	private Integer freeboardNo;
	/** 게시글 등록일 */
	private Date freeboardRegDate;
	/** 게시글 제목 */
	private String freeboardTitle;
	/** 게시글 내용 */
	private String freeboardBody;

	
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

	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	public Integer getFreeboardGroupNo(){
		return freeboardGroupNo;
	}

	public void setFreeboardGroupNo(Integer freeboardGroupNo){
		this.freeboardGroupNo = freeboardGroupNo;
	}

	public Integer getFileGroupNo(){
		return fileGroupNo;
	}

	public void setFileGroupNo(Integer fileGroupNo){
		this.fileGroupNo = fileGroupNo;
	}

	public Integer getCommentGroupNo(){
		return commentGroupNo;
	}

	public void setCommentGroupNo(Integer commentGroupNo){
		this.commentGroupNo = commentGroupNo;
	}

	public Integer getAuthGroupNo(){
		return authGroupNo;
	}

	public void setAuthGroupNo(Integer authGroupNo){
		this.authGroupNo = authGroupNo;
	}

	public Integer getFreeboardNo(){
		return freeboardNo;
	}

	public void setFreeboardNo(Integer freeboardNo){
		this.freeboardNo = freeboardNo;
	}

	public Date getFreeboardRegDate(){
		return freeboardRegDate;
	}

	public void setFreeboardRegDate(Date freeboardRegDate){
		this.freeboardRegDate = freeboardRegDate;
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
		return "FreeboardDetailVO [memNo=" + memNo + ", nickname=" + nickname + ", id=" + id + ", freeboardGroupNo="
				+ freeboardGroupNo + ", fileGroupNo=" + fileGroupNo + ", commentGroupNo=" + commentGroupNo
				+ ", authGroupNo=" + authGroupNo + ", freeboardNo=" + freeboardNo + ", freeboardRegDate="
				+ freeboardRegDate + ", freeboardTitle=" + freeboardTitle + ", freeboardBody=" + freeboardBody + "]";
	}
}
