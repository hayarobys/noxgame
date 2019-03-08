package kr.pe.hayarobys.nox.common.comment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.suph.security.core.dto.PaginationRequest;
import com.suph.security.core.dto.PaginationResponse;
import com.suph.security.core.userdetails.MemberInfo;
import com.suph.security.core.util.ContextUtil;

import kr.pe.hayarobys.nox.common.exception.BadRequestException;
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
	public PaginationResponse<CommentDetailVO> selectCommentDetailListByCommentGroupNo(
			Integer commentGroupNo, PaginationRequest paginationRequest
	){
		// 1. 비밀글에 대한 화이트 리스트 생성
		// 	ㄱ. 댓글 목록 요청자 조회
		MemberInfo memberInfo = ContextUtil.getMemberInfo();
		Integer memNo = (memberInfo == null) ? null : memberInfo.getNo();
		
		// 	ㄴ. 댓글 그룹 등록자 조회
		Integer commentRegMemNo = commentDAO.selectMemNoFromCommentGroupByCommentGroupNo(commentGroupNo);
		
		// 	ㄷ. 누구에게 달은 답글인지 대상 계정 번호 조회
		Integer targetMemNo = null;
		
		// 	ㄹ. 리스트화
		List<Integer> whiteMemNoList = new ArrayList<Integer>();
		whiteMemNoList.add(commentRegMemNo);
		if(memNo != null){
			whiteMemNoList.add(memNo);
		}
		
		// 2. 댓글 목록 조회
		List<CommentDetailVO> commentDetailList = commentDAO.selectCommentDetailListByCommentGroupNo(
				commentGroupNo, paginationRequest.getStart(), paginationRequest.getPagesize()
		);
		
		// 3. 댓글 개수 조회
		Integer totalRows = commentDAO.selectCommentTotalRowsByCommentGroupNo(commentGroupNo);
		
		// 3. 댓글 본문 비밀 처리
		for(CommentDetailVO vo : commentDetailList){
			if(openTypeService.canIView(memberInfo, vo.getOpenType(), whiteMemNoList) == false){
				vo.setCommentBody("[조회 권한이 없습니다]");
			}
		}
		
		return new PaginationResponse<CommentDetailVO>(commentDetailList, totalRows);
	}
	
	@Override
	public void insertComment(CommentVO commentVO){
		if(StringUtils.isEmpty(commentVO.getCommentBody()) == true){
			throw new BadRequestException("댓글 내용이 비어있습니다.");
		}
		
		commentDAO.insertComment(commentVO);
		commentDAO.updateCommentClassNoByCommentNo(commentVO.getCommentNo());
	}
	
	@Override
	public void insertCommentReply(CommentVO commentVO){
		if(StringUtils.isEmpty(commentVO.getCommentBody()) == true){
			throw new BadRequestException("댓글 내용이 비어있습니다.");
		}
		
		// 대상글의 CMT_CLS_FK, CMT_CLS_ORD, CMT_CLS_DPTH 조회
		CommentVO targetCommentVO = commentDAO.selectCommentClass(commentVO.getCommentNo());
		
		// 이 결과가 NULL이면 맨 밑으로 가는거고, NULL이 아니면 중간에 끼어 들어가는 형태입니다.
		Integer commentClassOrder = commentDAO.selectMinCommentClassOrderByCommentClassInfo(targetCommentVO);
		
		if(commentClassOrder == null){
			// 대상글의 그룹내에서 max(commentClassOrder) + 1 값을 구합니다.
			commentClassOrder = commentDAO.selectCommentCountInCommentClass(targetCommentVO.getCommentClassNo()) + 1;
		}else{
			// 대상글의 그룹내에서 새롭게 끼어들어갈 댓글을 위해 commentClassOrder를 + 1 씩 업데이트 합니다.
			targetCommentVO.setCommentClassOrder(commentClassOrder);
			commentDAO.updateCommentClassInTargetCommentClass(targetCommentVO);
		}
		
		commentVO.setCommentClassNo(targetCommentVO.getCommentClassNo());
		commentVO.setCommentClassOrder(commentClassOrder);
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

	@Override
	public void deleteComment(Integer commentNo, Integer memNo){
		// 대상 댓글의 작성자 조회
		Integer writerNo = commentDAO.selectMemNoFromCommentsByCommentNo(commentNo);
	
		// 삭제 요청자와의 권한 비교
		
		
		// 댓글 삭제
		
	}
}
