package kr.pe.hayarobys.nox.common.comment;

import com.suph.security.core.dto.PaginationRequest;
import com.suph.security.core.dto.PaginationResponse;

public interface CommentService{
	/**
	 * 댓글 목록 조회
	 * @param commentGroupNo
	 * @param paginationRequest
	 * @return
	 */
	public abstract PaginationResponse<CommentDetailVO> selectCommentDetailListByCommentGroupNo(
			Integer commentGroupNo, PaginationRequest paginationRequest
	);
	
	/**
	 * 댓글 삭제 요청자의 권한이 대상 댓글 작성자보다 높을경우 해당 댓글을 제거합니다.
	 * @param commentNo 제거할 댓글 번호
	 * @param memNo 댓글 삭제 요청자
	 */
	public abstract void deleteComment(Integer commentNo, Integer memNo);
	
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
	 * 댓글 그룹을 생성하고, 생성한 댓글 그룹 번호를 반환합니다.
	 * @param memNo 댓글 그룹 생성자
	 * @param commentGroupNewWriteFlag 댓글 그룹내 신규 작성 허용 여부
	 * @return 생성한 댓글 그룹의 일련 번호
	 */
	public abstract Integer insertCommentGroup(Integer memNo, Boolean commentGroupNewWriteFlag);
	
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
