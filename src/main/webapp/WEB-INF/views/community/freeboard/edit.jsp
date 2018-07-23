<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
				<form action="" method="post">
					<textarea name="ir1" id="ir1" rows="10" cols="100">에디터에 기본으로 삽입할 글(수정 모드)이 없다면 이 value 값을 지정하지 않으시면 됩니다.</textarea>
					
					<p>
						<input type="button" onclick="pasteHTML();" value="본문에 내용 넣기" />
						<input type="button" onclick="showHTML();" value="본문 내용 가져오기" />
						<input type="button" onclick="submitContents(this);" value="서버로 내용 전송" />
						<input type="button" onclick="setDefaultFont();" value="기본 폰트 지정하기 (궁서_24)" />
					</p>
				</form>
			</article>
		</section>
	</div>
	
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	
</body>
</html>