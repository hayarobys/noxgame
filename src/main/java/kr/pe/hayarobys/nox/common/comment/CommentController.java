package kr.pe.hayarobys.nox.common.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommentController{
	@Autowired
	private CommentService commentService;
	
	/** 댓글 목록 조회 */
	@RequestMapping(value="/comment-group/{commentGroupNo}", method=RequestMethod.GET)
	public @ResponseBody List<CommentDetailVO> getCommentList(@PathVariable("commentGroupNo") Integer commentGroupNo){
		return commentService.selectCommentDetailListByCommentGroupNo(commentGroupNo);
	}
	
	@RequestMapping(value="/comment", method=RequestMethod.POST)
	public @ResponseBody void postComment(@RequestBody CommentVO commentVO){
		
	}
}
