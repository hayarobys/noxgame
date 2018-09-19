<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>댓글</title>

	<link rel="stylesheet" href="<c:url value='/resources/css/ui/common/comment.css'/>" />
	<script src="<c:url value='/resources/scripts/ui/common/comment.js'/>"></script>
	
</head>
<body>
	<section class="comment-contents">
		<article class="comment-registration">
			<button class="comment-registration-button" id="commentRegistrationButton" type="button">
				<div class="text">작 성</div>
			</button>
			<div class="comment-secret-flag-div">
				<label for="commentSecretFlag" title="댓글 작성자, 덧댓글일 경우 대상 댓글의 작성자, 게시글 작성자, 관리자 에게만 보여집니다.">
					<input type="checkbox" class="comment-secret-flag" id="commentSecretFlag">
					비밀댓글
				</label>
			</div>
			<div class="comment-file-div">
				<input type="file" class="comment-file-input" />
			</div>
			<textarea class="comment-registration-textarea" id="commentRegistrationTextarea" placeholder="comment..."></textarea>
			<!-- 
			<div class="comment-file-group">
				<span>drag &#38; drop file upload</span>
			</div>
			-->
		</article>
		<article class="comment-list">
			<div class="comment-group-no">
				<span title="댓글 그룹 번호">댓글 # <span id="commentGroupNo">${param.commentGroupNo}</span></span>
				<input type="hidden" id="openType" name="openType" value="${param.openType}" />
			</div>
			<ul id="commentList">
				<li>
					<header>
						<div class="info-panel">
							<span class="nickname">홍길동</span>
							<span class="comment-reg-date">2018.08.31</span>
							<span class="comment-no-shop" title="댓글 번호"># <span class="comment-no">1</span></span>
						</div>
						<div class="edit-panel">
							<button class="" type="button">
								<div class="text">수정</div>
							</button>
							<button class="" type="button">
								<div class="text">삭제</div>
							</button>
						</div>
					</header>
					<article class="comment-body">
						내용입니다22.
					</article>
					<article class="comment-file">
						<!-- 
						<img src="https://localhost:8443/nox/resources/upload/2018/08/31/20180831170923-27acd380-63df-42c7-b486-e6bd05695e1b.jpg" />
						<img src="https://localhost:8443/nox/resources/upload/2018/08/30/20180830105403-d1a260b4-8422-4de0-baee-41e2bb8690c2.jpg" />
						<img src="https://localhost:8443/nox/resources/upload/2018/09/04/20180904133458-5c2e1030-a579-4298-904b-8d044d858fb6.png" />
						-->
					</article>
				</li>
				<li>
					<header>
						<div class="info-panel">
							<span class="nickname">김길동</span>
							<span class="comment-reg-date">2018.09.01</span>
							<span class="comment-no-shop" title="댓글 번호"># <span class="comment-no">2</span></span>
						</div>
						<div class="edit-panel">
							<button class="" type="button">
								<div class="text">수정</div>
							</button>
							<button class="" type="button">
								<div class="text">삭제</div>
							</button>
						</div>
					</header>
					<article class="comment-body">
						내용없음1
					</article>
					<article class="comment-file">
						<!-- <img src="https://localhost:8443/nox/resources/upload/2018/08/31/20180831170923-27acd380-63df-42c7-b486-e6bd05695e1b.jpg" /> -->
					</article>
				</li>
			</ul>
		</article>
		
		<article id="pagination" class="pagination">
			
		</article>
		
		<!-- 댓글 답글 팝업 -->
		<article id="commentReplyPopup" class="pop">
			<div class="popup-mask"></div>
			<div id="deletePopContent" class="pop-content pop-ani" style="width:830px;"><!--넓이 조절-->
				<div class="pop-head">
					<h2># <span id="replyTargetCommentNo"></span> 에 대한 답글 작성</h2>
					<button class="btn-x"></button>
				</div>
				<div class="pop-body">
					<article class="comment-registration">
						<button class="comment-registration-button" id="commentReplyRegistrationButton" type="button">
							<div class="text">작 성</div>
						</button>
						<div class="comment-secret-flag-div">
							<label for="commentReplySecretFlag" title="댓글 작성자, 덧댓글일 경우 대상 댓글의 작성자, 게시글 작성자, 관리자 에게만 보여집니다.">
								<input type="checkbox" class="comment-secret-flag" id="commentReplySecretFlag">
								비밀댓글
							</label>
						</div>
						<textarea class="comment-registration-textarea" id="commentReplyRegistrationTextarea" placeholder="comment..."></textarea>
					</article>
				</div>
			</div>
		</article>
	</section>
	
</body>
</html>