<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/common/common-head.jsp" flush="false"/>
	<title>자유게시판 상세보기</title>
	
	<link rel="stylesheet" href="<c:url value='/resources/css/ui/community/freeboard/detail.css'/>" />
	<script src="<c:url value='/resources/scripts/ui/community/freeboard/detail.js'/>"></script>
	
</head>
<body>
	
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/common/top-nav.jsp"></jsp:include>
	
	<div id="contents">
		<section id="mainContents">
			<article class="freeboard-title">
				자유게시판 상세보기
			</article>
			
			<article class="freeboard-contents">
				<table>
					<tr>
						<th>게시글 그룹 번호</th>
						<td><span id="freeboardGroupNo">${freeboardDetailVO.freeboardGroupNo}</span></td>
					</tr>
					<tr>
						<th>게시글 상세 번호</th>
						<td>${freeboardDetailVO.freeboardNo}</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td><span data-mem-no="${freeboardDetailVO.memNo}">${freeboardDetailVO.nickname} (${freeboardDetailVO.id})</span></td>
					</tr>
					<tr>
						<th>작성일</th>
						<td>${freeboardDetailVO.freeboardRegDate}</td>
					</tr>
					<tr>
						<th>제목</th>
						<td>${freeboardDetailVO.freeboardTitle}</td>
					</tr>
				</table>
				<hr />
				<p>${freeboardDetailVO.freeboardBody}</p>
				<hr />
			</article>
			
			<article class="freeboard-detail-button-contents">
				<button class="small-button float-right" id="delete-btn" type="button">
					<div class="text">삭제</div>
				</button>
				<button class="small-button float-right" id="modify-btn" type="button">
					<div class="text">수정</div>
				</button>
				<button class="small-button float-left" id="list-btn" type="button">
					<div class="text">목록</div>
				</button>
			</article>
			
			<jsp:include page="/WEB-INF/views/common/comment/comment.jsp" flush="true">
				<jsp:param name="commentGroupNo" value="${freeboardDetailVO.commentGroupNo}"/>
			</jsp:include>
		</section>	
	</div>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	
</body>
</html>