<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/common/common-head.jsp" flush="false"/>
	<title>자유게시판</title>
	
	<link rel="stylesheet" href="<c:url value='/resources/css/ui/community/freeboard/freeboard.css'/>" />
	<script src="<c:url value='/resources/scripts/ui/community/freeboard/freeboard.js'/>"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/common/top-nav.jsp"></jsp:include>
	
	<div id="contents">
		<section id="mainContents">
			<article class="freeboard-title">
				자유게시판
			</article>
			
			<article class="freeboard-contents">
				<button class="write-btn" id="write-btn" type="button">
					<div class="text">글쓰기</div>
				</button>
				<ul class="contents-title">
					<li>
						<dl class="item-list">
							<dt class="no">번호</dt>
							<dt class="nick">닉네임</dt>
							<dt class="title">제목(댓글수)</dt>
							<dt class="last-date">마지막 수정일</dt>
							<dt class="views">조회수</dt>
						</dl>
					</li>
				</ul>
				<ul class="contents-list">
					<li>
						<dl class="item-list">
							<dt class="no">2</dt>
							<dt class="nick">홍길동</dt>
							<dt class="title">와 게시판이다(2)</dt>
							<dt class="last-date">30초 전</dt>
							<dt class="views">4</dt>
						</dl>
					</li>
					<li>
						<dl class="item-list">
							<dt class="no">1</dt>
							<dt class="nick">tester</dt>
							<dt class="title">첫 게시글 입니다(0)</dt>
							<dt class="last-date">2018.07.12</dt>
							<dt class="views">12</dt>
						</dl>
					</li>
					
				</ul>
			</article>
		</section>
	</div>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>