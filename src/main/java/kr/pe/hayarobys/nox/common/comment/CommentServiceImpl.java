package kr.pe.hayarobys.nox.common.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentDAO commentDAO;
	
	@Override
	public Integer insertCommentGroup(Boolean commentGroupNewWriteFlag){
		CommentGroupVO commentGroupVO = new CommentGroupVO();
		commentGroupVO.setCommentGroupNewWriteFlag(true);
		commentDAO.insertCommentGroup(commentGroupVO);
		return commentGroupVO.getCommentGroupNo();
	}

}
