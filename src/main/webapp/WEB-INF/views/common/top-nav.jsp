<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="com.suph.security.core.userdetails.MemberInfo" %>
<%@ page import="org.slf4j.Logger" %>
<%@ page import="org.slf4j.LoggerFactory" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>상단 네비게이션</title>
	
	<link rel="stylesheet" href="<c:url value='/resources/css/ui/common/top-nav.css'/>" />
	<script src="<c:url value='/resources/scripts/ui/common/top-nav.js'/>"></script>
</head>
<body>
	<nav class="top-nav">
		<ul class="submenu">
			<li><a href="#">소개</a>
				<ul>
					<li><a href="#">녹스스토리</a></li>
					<li><a href="#">녹스특징</a></li>
					<li><a href="#">싱글플레이</a></li>
					<li><a href="#">멀티플레이</a></li>
				</ul>
			</li>
			<li><a href="#">메뉴얼</a>
				<ul>
					<li><a href="#">게임인터페이스</a></li>
					<li><a href="#">캐릭터소개</a></li>
					<li><a href="#">스킬&마법</a></li>
					<li><a href="#">아이템</a></li>
					<li><a href="#">몬스터</a></li>
					<li><a href="#">치트키</a></li>
				</ul>
			</li>
			<li><a href="#">자료실</a>
				<ul>
					<li><a href="#">기본자료실</a></li>
					<li><a href="#">회원자료실</a></li>
				</ul>
			</li>
			<li><a href="#">커뮤니티</a>
				<ul>
					<li><a href="#">자유게시판</a></li>
					<li><a href="#">스크린샷</a></li>
					<li><a href="#">카툰 갤러리</a></li>
				</ul>
			</li>
			<li><a href="#">전략포럼</a>
				<ul>
					<li><a href="#">전략토론방</a>
						<ul>
							<li><a href="#">추천전략</a></li>
							<li><a href="#">마법세팅</a></li>
						</ul>
					</li>
					<li><a href="#">아레나&엘리</a></li>
					<li><a href="#">퀘스트</a></li>
					<li><a href="#">싱글플레이</a></li>
				</ul>
			</li>
			<li><a href="#">소설게시판</a>
				<ul>
					<li><a href="#">자유소설</a></li>
					<li><a href="#">회원별 소설</a></li>
				</ul>
			</li>
			<li><a href="#">녹스가족</a>
				<ul>
					<li><a href="#">전사</a></li>
					<li><a href="#">마법사</a></li>
					<li><a href="#">소환술사</a></li>
				</ul>
			</li>
			<li><a href="#">녹스질문</a>
				<ul>
					<li><a href="#">질문하기</a></li>
					<li><a href="#">답변보기</a></li>
					<li><a href="#">운영방침</a></li>
					<li><a href="#">건의&문의</a></li>
				</ul>
			</li>
			<li><a href="#">사이트</a>
				<ul>
					<li><a href="#">운영진소개</a></li>
					<li><a href="#">오프모임</a></li>
					<li><a href="#">접속자</a></li>
					<li><a href="#">X-On</a></li>
				</ul>
			</li>
			<sec:authorize access="hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')">
			<li><a href="#">운영</a>
				<ul>
					<sec:authorize access="hasRole('ROLE_ADMIN')">
						<li><a href="<c:url value='/resource/edit' />" >RESOURCE 관리</a></li>
						<li><a href="<c:url value='/resource-auth/edit' />" >RESOURCE-AUTH 관리</a></li>
						<li><a href="<c:url value='/auth/edit' />" >AUTH 관리</a></li>
						<li><a href="<c:url value='/member-auth/edit' />" >MEMBER-AUTH 관리</a></li>
						<li><a href="<c:url value='/member/edit' />" >MEMBER 관리</a></li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')">
						<li><a href="<c:url value='/block-member/edit' />" >계정 차단 관리</a></li>
					</sec:authorize>
				</ul>
			</li>
			</sec:authorize>
		</ul>
	</nav>
	<div class="clear"></div>
</body>
</html>