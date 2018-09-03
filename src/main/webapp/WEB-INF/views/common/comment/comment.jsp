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
			<textarea class="comment-registration-textarea" id="commentRegistrationTextarea"></textarea>
		</article>
		<article class="comment-list">
			<div>
				댓글 그룹 번호: <span id="commentGroupNo">${param.commentGroupNo}</span>
			</div>
			<ul>
				<li>
					<header>
						<div class="info-panel">
							<span class="comment-no">1</span>
							<span class="nickname">홍길동</span>
							<span class="comment-reg-date">2018.08.31</span>
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
					<article>
						<p class="comment-body">내용입니다22.</p>
					</article>
				</li>
				<li>
					<header>
						<div class="info-panel">
							<span class="comment-no">2</span>
							<span class="nickname">김길동</span>
							<span class="comment-reg-date">2018.09.01</span>
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
					<article>
						<p class="comment-body">내용없음1</p>
					</article>
				</li>
			</ul>
		</article>
	</section>
</body>
</html>