<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/common/common-head.jsp" flush="false"/>
	<title>AUTHGROUP-AUTH 관리</title>
	
	<link rel="stylesheet" href="<c:url value='/resources/scripts/jqwidgets/styles/jqx.base.css'/>" />
	<link rel="stylesheet" href="<c:url value='/resources/css/ui/authgroup-auth/authgroup-auth.css'/>" />
	
	<script src="<c:url value='/resources/scripts/jqwidgets/jqx-all.js'/>"></script>
	<script src="<c:url value='/resources/scripts/ui/authgroup-auth/authgroup-auth.js'/>"></script>
</head>
<body>
	
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/common/top-nav.jsp"></jsp:include>
	
	<div id="contents">
		<jsp:include page="/WEB-INF/views/common/side-nav.jsp"></jsp:include>
		
		<section id="box" class="box">
			<div class="data_box">
				<header class="title">
					<span class="title_font">AUTHGROUP</span>
				</header>
				
				<section id="data_authgroup" class="data_body">
					
				</section>
				
				<input type="button" value="reload" onclick="javascript:reloadAuthgroupGrid();" />
			</div>
			
			<div class="data_box">
				<header class="title">
					<span class="title_font">AUTH</span>
				</header>
				
				<section id="data_auth" class="data_body">
					
				</section>
				
				<input type="button" value="save" onclick="javascipt:save();">
				<br/>
				<span>
					※ 권한 미 선택시, 해당 권한 그룹의 글은 관리자조차 조회할 수 없습니다.
				</span>
			</div>
		</section>
	</div>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	
</body>
</html>









