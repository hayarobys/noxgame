package kr.pe.hayarobys.nox.common.comment;

import java.util.List;

public interface CommentService{
	/**
	 * 댓글 목록 조회
	 * @param commentGroupNo
	 * @return
	 */
	public abstract List<CommentDetailVO> selectCommentDetailListByCommentGroupNo(Integer commentGroupNo);
	
	/**
	 * 댓글 그룹을 생성하고, 생성한 댓글 그룹 번호를 반환합니다.
	 * @param commentGroupNewWriteFlag 댓글 그룹내 신규 작성 허용 여부
	 * @return 생성한 댓글 그룹의 일련 번호
	 */
	public abstract Integer insertCommentGroup(Boolean commentGroupNewWriteFlag);
	
	/**
	 * 특정 댓글 그룹의 신규 작성 가능 여부를 변경합니다.
	 * @param commentGroupNo
	 * @param allowComment
	 */
	public abstract void updateAllowCommentOfCommentGroupByCommentGroupNo(Integer commentGroupNo, Boolean allowComment);
	
	/**
	 * 댓글 그룹과 그에 연결된 댓글, 파일 그룹, 파일, 물리 파일들을 모두 제거합니다.
	 * @param commentGroupNo
	 */
	public abstract void deleteCommentGroup(Integer commentGroupNo);
}
