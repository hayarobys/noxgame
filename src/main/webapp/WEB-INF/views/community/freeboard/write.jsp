<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/common/common-head.jsp" flush="false"/>
	<title>자유게시판 글쓰기</title>
	
	<link rel="stylesheet" href="<c:url value='/resources/css/ui/community/freeboard/write.css'/>" />
	
	<!-- Naver SmartEditor2 -->
	<script type="text/javascript" src="<c:url value='/resources/scripts/smarteditor2-2.9.0/js/service/HuskyEZCreator.js'/>" charset="utf-8"></script>
	<script src="<c:url value='/resources/scripts/ui/community/freeboard/write.js'/>"></script>
	
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
				<form action="<c:url value='/community/freeboard/write' />" method="post">
					<p>
						임시 저장 번호 <input type="number" id="tempSaveNo" name="tempSaveNo" value="${lastTempSaveVO.tempSaveNo}" />
					</p>
					제목 <input type="text" id="tempSaveTitle" name="tempSaveTitle" value="${lastTempSaveVO.tempSaveTitle}" />
					<textarea id="ir1" name="tempSaveBody" rows="10" cols="100" style="width: 100%; height: 400px;">
						<c:if test="${lastTempSaveVO.tempSaveBody}">
							${lastTempSaveVO.tempSaveBody}<br />"${lastTempSaveVO.tempSaveModDt}"에 임시 저장된 글입니다.
						</c:if>
					</textarea>
					<%-- <sec:csrfInput /> --%>
				</form>
				
				<%-- 첨부파일 영역 --%>
				<section class="width_100percent border_white">
					<header class="inline_block width_100percent">
						<article class="inline_block width_49percent align_left">
							<b>첨부파일</b> <input type="number" id="fileGroupNo" value="${lastTempSaveVO.fileGrpNo}" />
						</article>
						<article class="inline_block width_50percent align_right">
							<button>전체삭제</button>
							<button>선택삭제</button>
							<button>파일추가</button>
						</article>
					</header>
					<article id="attachmentPhoto">
						<c:forEach var="fileVO" items="${fileVOList}">
							<img data-file-no="${fileVO.fileNo}" data-type="attachment" id="${fileVO.saveFileName}" title="${fileVO.originalFileName}" src="<c:url value='/resources/upload/${fileVO.saveFileName}' />" style="width:90px; height: 90px;" />
						</c:forEach>
					</article>
				</section>
				
				<section>
					<ul>
						<li>
							<label>설정정보</label>
							<div>
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
									<input type="checkbox" id="allowComment">
									<label for="allowComment">댓글허용</label>
								</p>
							</div>
						</li>
					</ul>
				</section>
				<p>
					<!-- <input type="button" onclick="pasteHTML();" value="본문에 내용 넣기" /> -->
					<!-- <input type="button" onclick="showHTML();" value="본문 내용 가져오기" /> -->
					<input type="button" id="writeComplete" value="작성 완료" />
					<!-- <input type="button" onclick="setDefaultFont();" value="기본 폰트 지정하기 (궁서_24)" /> -->
				</p>
			</article>
		</section>
	</div>
	
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	
</body>
</html>