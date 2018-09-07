package kr.pe.hayarobys.nox.common.comment;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface CommentDAO{
	/**
	 * 특정 댓글 그룹을 생성한 계정의 계정 번호를 조회 합니다.
	 * @param commentGroupNo
	 * @return
	 */
	public abstract Integer selectMemNoFromCommentGroupByCommentGroupNo(Integer commentGroupNo);
	
	/**
	 * 여러 테이블을 조인해 특정 댓글 그룹의 상세 댓글 목록을 조회합니다.
	 * @param commentGroupNo
	 * @return
	 */
	public abstract List<CommentDetailVO> selectCommentDetailListByCommentGroupNo(Integer commentGroupNo);
	
	/**
	 * 특정 댓글의 계층 정보를 조회합니다.
	 * @param commentNo
	 * @return
	 */
	public abstract CommentVO selectCommentClass(Integer commentNo);
	
	/**
	 * 특정 댓글이 바로 밑에 가진 답글 수를 조회합니다. 1뎁쓰까지만 조회합니다. 답글의 답글은 제외합니다.
	 * @param commentNo
	 * @return
	 */
	public abstract Integer selectCommentReplyCountByCommentNo(Integer commentNo);
	
	/**
	 * 특정 댓글에 달린 답글들 중 계층 내 순서의 최대값 조회
	 * @param commentNo
	 * @return
	 */
	public abstract Integer selectMaxCommentClassOrderByCommentNo(Integer commentNo);
	
	/**
	 * 댓글을 등록합니다.
	 * @param commentVO
	 */
	public abstract void insertComment(CommentVO commentVO);
	
	/**
	 * 댓글의 답글을 등록합니다.
	 * @param commentVO
	 */
	public abstract void insertCommentReply(CommentVO commentVO);
	
	/**
	 * 댓글 그룹을 생성하고 그 일련번호를 전달받은 객체에 담습니다.
	 * @param commentGroupVO
	 */
	public abstract void insertCommentGroup(CommentGroupVO commentGroupVO);
	
	/**
	 * 특정 댓글 그룹의 각 댓글들이 참조하는 모든 파일 그룹 번호를 조회해 목록으로 반환합니다.
	 * @param commentGroupNo
	 * @return
	 */
	public abstract List<Integer> selectFileGroupNoListFromCommentByCommentGroupNo(Integer commentGroupNo);
	
	/**
	 * 특정 댓글이 속한 계층에서 그 댓글과 뎁쓰가 크거나 같은 댓글들의 계층내 순서 값을 +1씩 증가시킵니다.
	 * @param commentNo
	 */
	public abstract void updateCommentClass(Integer commentNo);
	
	/**
	 * 특정 댓글의 계층 번호를 댓글 번호와 동일하게 변경합니다.
	 * @param commentNo
	 */
	public abstract void updateCommentClassNoByCommentNo(Integer commentNo);
	
	/**
	 * 특정 댓글 그룹의 신규 작성 가능 여부를 변경합니다.
	 * @param commentGroupVO
	 */
	public abstract void updateAllowCommentOfCommentGroupByCommentGroupNo(CommentGroupVO commentGroupVO);
	
	/**
	 * 특정 댓글 그룹에 속한 모든 댓글을 제거합니다.
	 * @param commentGroupNo
	 */
	public abstract void deleteCommentByCommentGroupNo(Integer commentGroupNo);
	
	/**
	 * 특정 댓글 그룹을 제거합니다.
	 * @param commentGroupNo
	 */
	public abstract void deleteCommentGroupByCommentGroupNo(Integer commentGroupNo);
}
