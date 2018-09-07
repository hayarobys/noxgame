package kr.pe.hayarobys.nox.common.comment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suph.security.core.userdetails.MemberInfo;
import com.suph.security.core.util.ContextUtil;

import kr.pe.hayarobys.nox.common.opentype.OpenTypeService;
import kr.pe.hayarobys.nox.common.upload.UploadService;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	private UploadService uploadService;
	
	@Autowired
	private OpenTypeService openTypeService;
	
	@Override
	public List<CommentDetailVO> selectCommentDetailListByCommentGroupNo(Integer commentGroupNo){
		
		// 1. 비밀글에 대한 화이트 리스트 생성
		// 	ㄱ. 댓글 목록 요청자 조회
		MemberInfo memberInfo = ContextUtil.getMemberInfo();
		Integer memNo = memberInfo.getNo();
		
		// 	ㄴ. 댓글 그룹 등록자 조회
		Integer commentRegMemNo = commentDAO.selectMemNoFromCommentGroupByCommentGroupNo(commentGroupNo);
		
		// 	ㄷ. 누구에게 달은 답글인지 대상 계정 번호 조회
		Integer targetMemNo = null;
		
		// 	ㄹ. 리스트화
		List<Integer> whiteMemNoList = new ArrayList<Integer>();
		whiteMemNoList.add(memNo);
		whiteMemNoList.add(commentRegMemNo);
		
		// 2. 댓글 목록 조회
		List<CommentDetailVO> commentDetailList = commentDAO.selectCommentDetailListByCommentGroupNo(commentGroupNo);
		
		// 3. 댓글 본문 비밀 처리
		for(CommentDetailVO vo : commentDetailList){
			if(openTypeService.canIView(memberInfo, vo.getOpenType(), whiteMemNoList) == false){
				vo.setCommentBody("[조회 권한이 없습니다]");
			}
		}
		
		return commentDetailList;
	}
	
	@Override
	public void insertComment(CommentVO commentVO){
		commentDAO.insertComment(commentVO);
		commentDAO.updateCommentClassNoByCommentNo(commentVO.getCommentNo());
	}
	
	@Override
	public void insertCommentReply(CommentVO commentVO){
		// 대상글의 댓글 계층 번호로 commentClassOrder 업데이트 시작
		CommentVO targetCommentVO = commentDAO.selectCommentClass(commentVO.getCommentNo());
		
		// 넣고자 하는 댓글 계층 내 순서 최대값 조회
		Integer commentClassOrderMaxValue = commentDAO.selectMaxCommentClassOrderByCommentNo(commentVO.getCommentNo());
		
		// 넣고자 하는 위치 이후 댓글들에 대한 같은 계층 내 순서 +1
		commentDAO.updateCommentClass(commentVO.getCommentNo());
		
		commentVO.setCommentClassNo(targetCommentVO.getCommentClassNo());
		commentVO.setCommentClassOrder(commentClassOrderMaxValue + 1);
		commentVO.setCommentClassDepth(targetCommentVO.getCommentClassDepth() + 1);
		
		// 답글 등록
		commentDAO.insertCommentReply(commentVO);
	}
	
	@Override
	public Integer insertCommentGroup(Integer memNo, Boolean commentGroupNewWriteFlag){
		CommentGroupVO commentGroupVO = new CommentGroupVO();
		commentGroupVO.setMemNo(memNo);
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
