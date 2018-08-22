<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/common/common-head.jsp" flush="false"/>
	<title>자유게시판 글 수정</title>
	
	<link rel="stylesheet" href="<c:url value='/resources/css/ui/community/freeboard/modify.css'/>" />
	
	<!-- Naver SmartEditor2 -->
	<script type="text/javascript" src="<c:url value='/resources/scripts/smarteditor2-2.9.0/js/service/HuskyEZCreator.js'/>" charset="utf-8"></script>
	<script src="<c:url value='/resources/scripts/ui/community/freeboard/modify.js'/>"></script>
	
</head>
<body>
	
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/common/top-nav.jsp"></jsp:include>
	
	<div id="contents">
		<section id="mainContents">
			<article class="freeboard-title">
				자유게시판 글 수정
			</article>
			
			<article class="freeboard-contents">
				<form action="<c:url value='/community/freeboard/write' />" method="post">
					<p>
						<div>
							자유게시판 그룹 번호 <span id="freeboardGroupNo">${freeboardGroupNo}</span>
						</div>
						<div>
							임시 저장 번호 <input type="number" id="tempSaveNo" name="tempSaveNo" value="${tempSaveVO.tempSaveNo}" />
						</div>
						<div>
							<c:if test="${tempSaveVO.tempSaveBody ne null}">
								"${tempSaveVO.tempSaveModDt}"에 임시 저장된 글입니다.
							</c:if>
						</div>
					</p>
					제목 <input type="text" id="tempSaveTitle" name="tempSaveTitle" value="${tempSaveVO.tempSaveTitle}" />
					<textarea id="ir1" name="tempSaveBody" rows="10" cols="100" style="width: 100%; height: 400px;">${tempSaveVO.tempSaveBody}</textarea>
				</form>
				
				<%-- 첨부파일 영역 --%>
				<section class="width_100percent border_white">
					<header class="inline_block width_100percent">
						<article class="inline_block width_49percent align_left">
							<b>첨부파일</b> <input type="number" id="fileGroupNo" value="${tempSaveVO.fileGroupNo}" />
						</article>
						<article class="inline_block width_50percent align_right">
							<button>전체삭제</button>
							<button>선택삭제</button>
							<button>파일추가</button>
						</article>
					</header>
					<article id="attachmentPhoto">
						<c:forEach var="fileVO" items="${fileVOList}">
							<fmt:formatDate value="${fileVO.fileRegDt}" pattern="yyyy/MM/dd" var="datePath"/>
							<img data-file-no="${fileVO.fileNo}" data-type="attachment" id="${fileVO.saveFileName}" title="${fileVO.originalFileName}" src="<c:url value='/resources/upload/${datePath}/${fileVO.saveFileName}' />" style="width:90px; height: 90px;" />
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
										<label for="setClose" title="작성자와 매니저, 어드민만 조회 할 수 있습니다.">비공개</label><!-- 이 항목은 관리자만 볼 수 있습니다. -->
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
					<input type="button" id="modifyComplete" value="수정 완료" />
				</p>
			</article>
		</section>
	</div>
	
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	
</body>
</html>