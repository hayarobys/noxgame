package kr.pe.hayarobys.nox.common.comment;

public interface CommentService{
	/**
	 * 댓글 그룹을 생성하고, 생성한 댓글 그룹 번호를 반환합니다.
	 * @param commentGroupNewWriteFlag 댓글 그룹내 신규 작성 허용 여부
	 * @return 생성한 댓글 그룹의 일련 번호
	 */
	public abstract Integer insertCommentGroup(Boolean commentGroupNewWriteFlag);
}
