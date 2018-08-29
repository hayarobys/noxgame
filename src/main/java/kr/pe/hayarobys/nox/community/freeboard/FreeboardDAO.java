package kr.pe.hayarobys.nox.community.freeboard;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.suph.security.core.dto.PaginationRequest;

@Repository
public interface FreeboardDAO{
	
	/**
	 * 모든 자유게시판 목록을 페이징 처리하여 조회합니다.
	 * @param paginationRequest
	 * @return
	 */
	public abstract List<FreeboardGroupVO> getFreeboardGroupList(PaginationRequest paginationRequest);
	
	/**
	 * 모든 자유게시판 목록의 개수를 조회 합니다.
	 * @return
	 */
	public abstract Integer getFreeboardGroupListTotalRows();
	
	/**
	 * 자유게시판 그룹을 등록하고 생성된 일련 번호를 조회 합니다.
	 * @param freeboardGroupVO
	 * @return 
	 */
	public abstract void insertFreeboardGroup(FreeboardGroupVO freeboardGroupVO);
		
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
	public abstract FreeboardDetailVO selectLastFreeboardDetail(Integer freeboardGroupNo);
	
	/**
	 * 특정 게시글 그룹의 작성자를 조회합니다.
	 * @param freeboardGroupNo
	 * @return
	 */
	public abstract Integer selectMemNoFromFreeboardGroupByFreeboardGroupNo(Integer freeboardGroupNo);
	
	/**
	 * 특정 일련 번호의 자유 게시판 그룹에 연결된 댓글 그룹 번호를 조회합니다.
	 * @param freeboardGroupNo
	 * @return
	 */
	public abstract Integer selectCommentGroupNoFromFreeboardGroupByFreeboardGroupNo(Integer freeboardGroupNo);
	
	/**
	 * 특정 자유게시판 그룹의 최소 조회 권한을 변경합니다.
	 * @param freeboardGroupVO freeboardGroupNo와 authgroup만 사용합니다.
	 */
	public abstract void updateAuthgroupOfFreeboardGroupByFreeboardGroupNo(FreeboardGroupVO freeboardGroupVO);
	
	/**
	 * 특정 일련 번호의 자유 게시판 그룹을 제거합니다.
	 * @param freeboardGroupNo
	 */
	public abstract void deleteFreeboardGroupByFreeboardGroupNo(Integer freeboardGroupNo);
	
	/**
	 * 특정 게시글 그룹에 속한 게시글들을 제거합니다.
	 * @param freeboardGroupNo
	 */
	public abstract void deleteFreeboardByFreeboardGroupNo(Integer freeboardGroupNo);
	
	/**
	 * 자유게시판 그룹에 소속된 파일들의 일련 번호 목록을 조회합니다.
	 * @param freeboardGroupNo
	 * @return
	 */
	public abstract List<Integer> selectFileGroupFromFreeboardGroupByFreeobardGroupNo(Integer freeboardGroupNo);
}





