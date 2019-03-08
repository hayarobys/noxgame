package kr.pe.hayarobys.nox.common.comment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suph.security.core.dto.PaginationRequest;
import com.suph.security.core.dto.PaginationResponse;
import com.suph.security.core.userdetails.MemberInfo;
import com.suph.security.core.util.ContextUtil;

import kr.pe.hayarobys.nox.common.exception.UnauthorizedException;
/**
 * 댓글과 관련한 API를 정의하는 컨트롤러 클래스 입니다.
 * @author hayarobys@gmail.com
 * @date 20180914
 */
@Controller
public class CommentController{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CommentService commentService;
	
	/** 댓글 목록 조회 */
	@RequestMapping(value="/comment-group/{commentGroupNo}", method=RequestMethod.GET)
	public @ResponseBody PaginationResponse<CommentDetailVO> getCommentList(
			@PathVariable("commentGroupNo") Integer commentGroupNo,
			@RequestParam(name="pagenum", required=false, defaultValue="1") int pagenum,
			@RequestParam(name="pagesize", required=false, defaultValue="20") int pagesize
	){
		PaginationRequest paginationRequest = new PaginationRequest();
		paginationRequest.setPagenum(pagenum-1);
		paginationRequest.setPagesize(pagesize);
		
		return commentService.selectCommentDetailListByCommentGroupNo(commentGroupNo, paginationRequest);
	}
	
	/**
	 * 특정 댓글 삭제 요청
	 * @param commentNo 삭제할 댓글의 번호
	 */
	@RequestMapping(value="/comment/{commentNo}", method=RequestMethod.DELETE)
	public void deleteComment(@PathVariable("commentNo") Integer commentNo){
		// 댓글 삭제 요청자 확인
		MemberInfo memberInfo = ContextUtil.getMemberInfo();
		if(memberInfo == null){
			throw new UnauthorizedException("로그인이 필요한 기능입니다.");
		}
		commentService.deleteComment(commentNo, memberInfo.getNo());
		
	}
	
	/**
	 * 신규 댓글 등록 요청
	 * @param commentVO
	 */
	@RequestMapping(value="/comment", method=RequestMethod.POST)
	public void postComment(@RequestBody CommentVO commentVO){		
		// 댓글 등록 요청자 확인
		MemberInfo memberInfo = ContextUtil.getMemberInfo();
		if(memberInfo != null){
			commentVO.setMemNo(memberInfo.getNo());
		}
		
		// 댓글 등록
		commentService.insertComment(commentVO);
	}
	
	/**
	 * 대댓글(댓글의 답글) 등록 요청
	 * @param commentVO
	 */
	@RequestMapping(value="/comment/{commentNo}/reply", method=RequestMethod.POST)
	public void postComment(
			@PathVariable("commentNo") Integer commentNo,
			@RequestBody CommentVO commentVO
	){
		// 대상 댓글의 댓글 번호 저장
		commentVO.setCommentNo(commentNo);
		
		// 댓글 등록 요청자 확인
		MemberInfo memberInfo = ContextUtil.getMemberInfo();
		if(memberInfo != null){
			commentVO.setMemNo(memberInfo.getNo());
		}
		
		// 댓글 등록
		commentService.insertCommentReply(commentVO);
	}
}



