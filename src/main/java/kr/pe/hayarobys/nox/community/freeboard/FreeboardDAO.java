package kr.pe.hayarobys.nox.community.freeboard;

import org.springframework.stereotype.Repository;

@Repository
public interface FreeboardDAO{
	
	/**
	 * 자유게시판 그룹을 등록하고 생성된 일련 번호를 조회 합니다.
	 * @param freeboardGroupVO
	 * @return 
	 */
	public abstract void insertFreeboardGroup(FreeboardGroupVO freeboardGroupVO);
	
	/**
	 * 특정 일련 번호의 자유 게시판 그룹이 가진 계층 번호를 수정합니다.
	 * @param freeboardGroupVO
	 */
	public abstract void updateFreeboardGroupClassNo(FreeboardGroupVO freeboardGroupVO);
	
	/**
	 * 자유게시판 상세 정보를 등록합니다.
	 * @param freeboardVO
	 */
	public abstract void insertFreeboard(FreeboardVO freeboardVO);
	
	/**
	 * 게시글 상세 조회시 필요한 값들을 조회합니다.
	 * 특정 일련 번호의 게시글 그룹에 속한 마지막 게시글과 파일 그룹, 댓글 그룹, 계정 아이디, 계정 닉네임, 등록일 등의 정보를 조회합니다.
	 * @param freeboardGroupNo
	 */
	public abstract FreeboardDetailVO selectFreeboardDetail(Integer freeboardGroupNo);
}
