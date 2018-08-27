<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/common/common-head.jsp" flush="false"/>
	<title>자유게시판 글쓰기 테스트</title>
	
	<link rel="stylesheet" href="<c:url value='/resources/css/ui/community/freeboard/write.css'/>" />
	
</head>
<body>
	
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/common/top-nav.jsp"></jsp:include>
	
	<div id="contents">
		<section id="mainContents">
			<article class="freeboard-title">
				자유게시판 글쓰기 테스트
			</article>
			
			<article class="freeboard-contents">
				<section>
					<label>설정정보</label>
					<p>
						<input type="radio" id="setPublic" value="PUBLIC" name="openType">
						<label for="setPublic" title="비회원에게도 공개 합니다.">전체공개</label>
						<input type="radio" id="setMember" value="MEMBER" name="openType">
						<label for="setMember" title="로그인 한 계정에게 공개합니다.">회원공개</label>
						<input type="radio" id="setSecret" value="SECRET" name="openType">
						<label for="setSecret" title="작성자, 관리자, 답글의 경우 대상글의 작성자만 볼 수 있습니다.">비밀글</label>
						<sec:authorize access="hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')">
							<input type="radio" id="setCLOSE" value="CLOSE" name="openType">
							<label for="setClose" title="매니저, 어드민만 조회 할 수 있습니다.">비공개</label><!-- 이 항목은 관리자만 볼 수 있습니다. -->
						</sec:authorize>
					</p>
					<p>
						<input type="button" id="writeComplete" value="작성 완료" />
					</p>
				</section>
			</article>
		</section>
	</div>
	
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	
	<script>
		jQuery(function() {
			// 게시글 작성 완료 버튼 클릭 이벤트 입니다.
			jQuery("#writeComplete").on("click", function(event){
				event.preventDefault();
				
				var formData = {
						authgroup: jQuery("input[name='openType']:checked").val()
				};
				
				// 등록 요청 전송
				var token = jQuery("meta[name='_csrf']").attr("content");
				var header = jQuery("meta[name='_csrf_header']").attr("content");
				
				jQuery.ajax({
					type: "POST",
					url: CONTEXT_PATH + "/test/enum",
					data: JSON.stringify(formData),
					contentType: 'application/json',
					dataType: "json",	// 서버에서 응답한 데이터를 클라이언트에서 읽는 방식
					beforeSend: function(xhr){
						xhr.setRequestHeader("X-Ajax-call", "true");	// CustomAccessDeniedHandler에서 Ajax요청을 구분하기 위해 약속한 값
						xhr.setRequestHeader(header, token);	// 헤더의 csrf meta태그를 읽어 CSRF 토큰 함께 전송
					},
					success: function(data, statusText, xhr){
						console.log("응답값: ", data);
					},
					error: function(xhr){
						console.log("error", xhr);
					}
				});
			});
		});
	</script>
</body>
</html>