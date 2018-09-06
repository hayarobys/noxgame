<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/common/common-head.jsp" flush="false"/>
	<title>자유게시판</title>
	
	<link rel="stylesheet" href="<c:url value='/resources/css/ui/community/freeboard/freeboard.css'/>" />
	<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/rxjs/4.1.0/rx.all.min.js"></script> -->
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
				<button class="small-button" id="write-btn" type="button">
					<div class="text">글쓰기</div>
				</button>
				<article id="freeboardList" class="freeboard-list">
					
				</article>
				<article id="pagination" class="pagination">
			
				</article>
			</article>
		</section>
	</div>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	
</body>
</html>