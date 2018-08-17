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
						<td>${freeboardDetailVO.freeboardGroupNo}</td>
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
					<tr>
						<th>본문</th>
						<td>
							<p>${freeboardDetailVO.freeboardBody}</p>
						</td>
					</tr>
				</table>
			</article>
			
			<article class="freeboard-comment">
				<table>
					<tr>
						<th>댓글 그룹 번호</th>
						<td><span id="commentGroupNo">${freeboardDetailVO.commentGroupNo}</span></td>
					</tr>
					<tr>
						<th>작성자</th>
						<td>-</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>-</td>
					</tr>
				</table>
			</article>
		</section>
	</div>
	
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	
</body>
</html>