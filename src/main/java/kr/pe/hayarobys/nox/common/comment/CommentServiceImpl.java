package kr.pe.hayarobys.nox.common.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.hayarobys.nox.common.upload.UploadService;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	private UploadService uploadService;
	
	@Override
	public List<CommentDetailVO> selectCommentDetailListByCommentGroupNo(Integer commentGroupNo){
		return commentDAO.selectCommentDetailListByCommentGroupNo(commentGroupNo);
	}
	
	@Override
	public void insertComment(CommentVO commentVO){
		commentDAO.insertComment(commentVO);
		
	}
	
	@Override
	public Integer insertCommentGroup(Boolean commentGroupNewWriteFlag){
		CommentGroupVO commentGroupVO = new CommentGroupVO();
		commentGroupVO.setCommentGroupNewWriteFlag(true);
		commentDAO.insertCommentGroup(commentGroupVO);
		return commentGroupVO.getCommentGroupNo();
	}
	
	@Override
	public void updateAllowCommentOfCommentGroupByCommentGroupNo(Integer commentGroupNo, Boolean allowComment){
		CommentGroupVO commentGroupVO = new CommentGroupVO();
		commentGroupVO.setCommentGroupNo(commentGroupNo);
		commentGroupVO.setCommentGroupNewWriteFlag(allowComment);
		commentDAO.updateAllowCommentOfCommentGroupByCommentGroupNo(commentGroupVO);
	}

	@Override
	public void deleteCommentGroup(Integer commentGroupNo){
		// 파일 그룹 목록 조회
		List<Integer> fileGroupNoList = commentDAO.selectFileGroupNoListFromCommentByCommentGroupNo(commentGroupNo);
		
		// 댓글 목록 제거
		commentDAO.deleteCommentByCommentGroupNo(commentGroupNo);
		
		// 댓글 그룹 제거
		commentDAO.deleteCommentGroupByCommentGroupNo(commentGroupNo);
		
		// 파일 그룹과 그에 연결된 DB파일, 물리파일 제거 서비스 요청
		if(fileGroupNoList.size() > 0){
			uploadService.deleteFileGroupByFileGroupNoList(fileGroupNoList);
		}
	}
	
}
