package kr.pe.hayarobys.nox.common.comment;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface CommentDAO{
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
