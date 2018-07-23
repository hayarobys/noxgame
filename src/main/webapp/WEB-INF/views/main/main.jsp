<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="com.suph.security.core.userdetails.MemberInfo" %>
<%@ page import="org.slf4j.Logger" %>
<%@ page import="org.slf4j.LoggerFactory" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%	
	// 로그인 정보를 가져오기 위해 스프링 시큐리티에서 제공하는 방법
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	Object principal = auth.getPrincipal();
	String name = "";
	if(principal != null && principal instanceof MemberInfo){
		name = ((MemberInfo)principal).getName();
		// 미인증 상태에선 "anonymousUser" 가 반환된다.
	}
%>

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
				<article class="mini-login-form">
					<sec:authorize access="isAnonymous()">
						<!-- 스프링 시큐리티 4.x 버전부터는 action 경로가 login으로, name들은 더 명확한걸로 변경되었음에 주의. -->
						<form id="loginfrm" name="loginfrm" method="POST" action="./login_check">
							<table>
								<tr>
									<td style="width:30px;">id</td>
									<td style="width:80px;">
										<input style="width:80px;" type="text" id="loginid" name="loginid" value=""/>
									</td>
								</tr>
								<tr>
									<td>pwd</td>
									<td>
										<input style="width:80px;" type="password" id="loginpwd" name="loginpwd" value=""/>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<button class="login-btn" id="loginbtn" type="submit">
											<div class="text">로그인</div>
										</button>
										<%-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> --%>
										<sec:csrfInput /> <!-- 을 사용하는 방법도 존재 -->
									</td>
								</tr>
							</table>
							
							<!-- 로그인 성공시 이동할 경로 지정 -->
							<!-- loginRedirect 값은 CustomAuthenticationFailureHandler에서 다룸 -->
							<input type="hidden" name="loginRedirect" value="${loginRedirect}"/>
						</form>
					</sec:authorize>
					<sec:authorize access="isAuthenticated()">
						<%=name%>님 반갑습니다<br/>
						<%-- <a href="<%=request.getContextPath()%>/j_spring_security_logout">로그아웃</a> --%>
						
						<%-- <c:url var="logoutUrl" value="/j_spring_security_logout"/> --%>
						<c:url var="logoutUrl" value="/logout"/>
						<form action="${logoutUrl}" method="post">
							<input type="submit" value="로그아웃" />
							<%-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> --%>
							<sec:csrfInput /> <!-- 을 사용하는 방법도 존재 -->
						</form>
						
						
					</sec:authorize>
				</article>
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
			<!-- 
			<article>
				<input type="button" value="현재 닉네임 읽기" onclick="javascript:getNickname()"/>
				<div id="nickname">
				
				</div>
			</article>
			-->
		</section>
	</div>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	
</body>
</html>