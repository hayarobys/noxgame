<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/common/common-head.jsp" flush="false"/>
	<title>AUTH 관리</title>
	
	<link rel="stylesheet" href="<c:url value='/resources/scripts/jqwidgets/styles/jqx.base.css'/>" />
	<link rel="stylesheet" href="<c:url value='/resources/css/ui/authgroup/authgroup.css'/>" />
	
	<script src="<c:url value='/resources/scripts/jqwidgets/jqx-all.js'/>"></script>
	<script src="<c:url value='/resources/scripts/ui/authgroup/authgroup.js'/>"></script>
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
				
				<input type="button" value="새로고침" onclick="javascript:reloadAuthgroupGrid();" />
				<%-- <input type="button" value="선택 항목 제거" onclick="javascript:deleteSelectedAuthgroup();" /> --%>
			</div>
			<%-- 
			<div class="data_box">
				<form method="post" id="authgroupForm" >
					<table id="authgroupFormTable">
						<tr>
							<th>
								<label for="authgroupName">권한 그룹 명</label>
							</th>
							<td>
								<input id="authgroupName" name="authgroupName" type="text" placeholder="관리자만 보기" />
							</td>
						</tr>
						<tr>
							<th>
								<label for="authgroupExplanation">권한 그룹 설명</label>
							</th>
							<td>
								<input id="authgroupExplanation" name="authgroupExplanation" type="text" placeholder="권한 그룹에 대한 설명을 적어주세요." />
							</td>
						</tr>
					</table>
					<input type="button" value="리소스 등록" onclick="javascript:insertAuthgroup();">
				</form>
			</div>
			--%>
		</section>
	</div>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>









