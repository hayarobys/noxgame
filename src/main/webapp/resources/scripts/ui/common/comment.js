
$(function() {
	refreshCommentGroup();
	
	jQuery("#commentRegistrationButton").on("click", function(event){
		event.preventDefault();
		postComment();
	});
	
});

/**
 * 댓글 목록을 새로고침 합니다.
 * @returns
 */
function refreshCommentGroup(){
	jQuery.ajax({
		type: "GET",
		url: CONTEXT_PATH + '/comment-group/' + jQuery("#commentGroupNo").text(),
		contentType: 'application/json',
		dataType: "json",	// 서버에서 응답한 데이터를 클라이언트에서 읽는 방식
		beforeSend: function(xhr){
			xhr.setRequestHeader("X-Ajax-call", "true");	// CustomAccessDeniedHandler에서 Ajax요청을 구분하기 위해 약속한 값
		},
		success: function(data, statusText, xhr){
			console.log("응답값: ", data);
			replactCommentList(data);
		},
		error: function(xhr){
			console.log("error", xhr);
		}
	});
}

/**
 * 댓글 목록을 인자로 전달된 데이터로 교체합니다.
 * @param commentList
 * @returns
 */
function replactCommentList(rows){
	var commentListString = "";
	var commentRegDate = "";
	var commentModDate = "";
	var commentSecretIcon = "";
	
	/* rows:[
	 *	{
	 *		authgroup:"PUBLIC"
	 *		commentBody:"댓글1-1"
	 *		commentClassDepth:1
	 *		commentClassNo:null
	 *		commentGroupNewWriteFlag:true
	 *		commentModDate:1536038787000
	 *		commentNo:1
	 *		commentRegDate:1536038787000
	 *		commentSecretFlag:false
	 *		fileGroupNo:null
	 *		memNo:1
	 *		nickname:"관리자"
	 *	}
	 * ]
	 */
	
	rows.forEach(function(row, index, array){
		commentRegDate = getDateString(row.commentRegDate);
		commentModDate = (row.commentRegDate != row.commentModDate) ? ("마지막 수정일: " + getDateString(row.commentModDate)) : "수정 이력이 없습니다.";
		commentSecretIcon = (row.commentSecretFlag == true) ? "비밀글" : ""; 
		commentListString += `
			<li>
				<header>
					<div class="info-panel">
						<span class="nickname" title="${row.memNo}">${row.nickname}</span>
						<span class="comment-reg-date" title="${commentModDate}">${commentRegDate}</span>
						<span class="comment-no-shop" title="댓글 번호"># <span class="comment-no">${row.commentNo}</span></span>
						<span class="comment-secret-flag">${commentSecretIcon}</span>
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
					${row.commentBody}
				</article>
				<article class="comment-file">
				</article>
			</li>
		`
	});
	
	jQuery("#commentList").html(commentListString);
}

/**
 * 댓글 등록 요청을 보냅니다.
 * @returns
 */
function postComment(){
	var formData = {
			commentGroupNo: jQuery("#commentGroupNo").text(),
			commentBody: jQuery("#commentRegistrationTextarea").val(),
			commentSecretFlag:  jQuery("#commentSecretFlag").is(":checked"),
			authgroup: jQuery("#openType").val()
	};
	
	// 등록 요청 전송
	var token = jQuery("meta[name='_csrf']").attr("content");
	var header = jQuery("meta[name='_csrf_header']").attr("content");
	
	console.log("전송할 데이터", JSON.stringify(formData));
	
	jQuery.ajax({
		type: "POST",
		url: CONTEXT_PATH + "/comment",
		data: JSON.stringify(formData),
		contentType: 'application/json',
		//dataType: "json",	// 서버에서 응답한 데이터를 클라이언트에서 읽는 방식
		beforeSend: function(xhr){
			xhr.setRequestHeader("X-Ajax-call", "true");	// CustomAccessDeniedHandler에서 Ajax요청을 구분하기 위해 약속한 값
			xhr.setRequestHeader(header, token);	// 헤더의 csrf meta태그를 읽어 CSRF 토큰 함께 전송
		},
		success: function(data, statusText, xhr){
			console.log("응답값: ", data);
			refreshCommentGroup();
			
		},
		error: function(xhr){
			console.log("error", xhr);
		}
	});
}

/**
 * 댓글 작성창을 초기화 합니다.
 * @returns
 */
function clearCommentRegistrationForm(){
	jQuery("#commentRegistrationTextarea").val("");
}


