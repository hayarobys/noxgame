package kr.pe.hayarobys.nox.common.comment;

import org.springframework.stereotype.Repository;

@Repository
public interface CommentDAO{
	/**
	 * 댓글 그룹을 생성하고 그 일련번호를 전달받은 객체에 담습니다.
	 * @param commentGroupVO
	 */
	public abstract void insertCommentGroup(CommentGroupVO commentGroupVO);
}
