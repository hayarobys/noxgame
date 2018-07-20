<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="com.suph.security.core.userdetails.MemberInfo" %>
<%@ page import="org.slf4j.Logger" %>
<%@ page import="org.slf4j.LoggerFactory" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/common/common-head.jsp" flush="false"/>
	<title>메인화면</title>
	
	<link rel="stylesheet" href="<c:url value='/resources/css/ui/main/main.css'/>" />
	<script src="<c:url value='/resources/scripts/ui/main/main.js'/>"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/common/top-nav.jsp"></jsp:include>
	
	<div id="contents">
		<%-- <jsp:include page="/WEB-INF/views/common/side-nav.jsp"></jsp:include> --%>
		
		<section id="mainContents">
			<article class="member-info">
				정보
			</article>
			<article class="board gongji">
				공지
			</article>
			<article class="jayu-notice">
				질문글은 질문 게시판에 해주시기 바랍니다! 지키지 않을 시 삭제!
			</article>
			<article class="board jayu">
				자유
			</article>
			<article class="board screenshot">
				스크린샷
			</article>
			<article>
				<input type="button" value="현재 닉네임 읽기" onclick="javascript:getNickname()"/>
				<div id="nickname">
				
				</div>
			</article>
		</section>
	</div>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>