<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/common/common-head.jsp" flush="false"/>
	<title>자유게시판 글쓰기</title>
	
	<link rel="stylesheet" href="<c:url value='/resources/css/ui/community/freeboard/edit.css'/>" />
	
	<!-- Naver SmartEditor2 -->
	<script type="text/javascript" src="<c:url value='/resources/scripts/smarteditor2-2.9.0/js/service/HuskyEZCreator.js'/>" charset="utf-8"></script>
	<script src="<c:url value='/resources/scripts/ui/community/freeboard/edit.js'/>"></script>
	
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
				<form method="post">
					<input type="text" value="${lastTempSaveVO.tempSaveTitle}" />
					<textarea name="ir1" id="ir1" rows="10" cols="100">
						${lastTempSaveVO.tempSaveBody}<br/>
						"${lastTempSaveVO.tempSaveModDt}"에 저장한 글입니다.
					</textarea>
					<div>파일 묶음 일련 번호: <input type="number" id="fileGroupNo" value="${lastTempSaveVO.fileGrpNo}"/></div>
					
					<p>
						<!-- <input type="button" onclick="pasteHTML();" value="본문에 내용 넣기" />
						<input type="button" onclick="showHTML();" value="본문 내용 가져오기" /> -->
						<input type="submit" onclick="submitContents(this);" value="서버로 내용 전송" />
						<!-- <input type="button" onclick="setDefaultFont();" value="기본 폰트 지정하기 (궁서_24)" /> -->
					</p>
				</form>
			</article>
		</section>
	</div>
	
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	
</body>
</html>