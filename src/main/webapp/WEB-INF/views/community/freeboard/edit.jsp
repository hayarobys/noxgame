<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/common/common-head.jsp" flush="false"/>
	<title>자유게시판 글쓰기</title>
	
	<link rel="stylesheet" href="<c:url value='/resources/css/ui/community/freeboard/freeboard.css'/>" />
	<script src="<c:url value='/resources/scripts/ui/community/freeboard/freeboard.js'/>"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/common/top-nav.jsp"></jsp:include>
	
	<div id="contents">
		<section id="mainContents">
			<article class="freeboard-title">
				자유게시판 글쓰기
			</article>
			
			<article class="freeboard-contents">
				<form>
					<table>
						<tr>
							<td>
								<input type="text" />
							</td>
						</tr>
						<tr>
							<td>
								<textarea rows="" cols="">
								
								</textarea>
							</td>
						</tr>
					</table>
					<div>
						이미지 업로드
					</div>
				</form>
			</article>
		</section>
	</div>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>