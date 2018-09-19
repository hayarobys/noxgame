/** 한 페이지에 표시할 댓글의 개수 */
var PAGESIZE = 15;
/** 한번에 보여줄 페이지 번호의 개수 */
var PAGEBAR_COUNT = 10;

$(function() {
	// 페이징바 클릭 이벤트 부여
	jQuery(document).on("click", "a[data-pagenum]", function(event){
		var pagenum = event.target.getAttribute("data-pagenum");
		refreshCommentGroup(pagenum, PAGESIZE, PAGEBAR_COUNT);
	});
		
	// 최초 1회 목록 갱신. URL끝에 #27 과 같은 페이지 번호가 있다면 읽어들입니다.
	var currentURL = location.href;
	var pageParameterIndex = currentURL.lastIndexOf("#");
	if(pageParameterIndex > 0){
		refreshCommentGroup(currentURL.substring(pageParameterIndex + 1), PAGESIZE, PAGEBAR_COUNT);
	}else{
		refreshCommentGroup(1, PAGESIZE, PAGEBAR_COUNT);
	}
	
	// 댓글 신규 작성 버튼 클릭 이벤트
	jQuery("#commentRegistrationButton").on("click", function(event){
		event.preventDefault();
		postComment();
	});
	
	$(".btn-x").click({target: ".pop"}, closeLayerPopup);	// 모든 팝업의 x버튼 클릭 시 동작 정의
	$(".popup-mask").click({target: ".pop"}, closeLayerPopup);	// 모든 팝업의 배경화면 클릭 시 동작 정의
	
	// 답글 버튼 클릭 시 팝업 출력
	jQuery("#commentList").on("click", "#commentReplyButton", function(event){
		event.preventDefault();
		var replyCommentNo = event.currentTarget.getAttribute("data-comment-no");
		
		openLayerPopup({
			before: function(){
				jQuery("#commentReplyPopup").data({"commentNo":replyCommentNo});
				jQuery("#replyTargetCommentNo").text(replyCommentNo);
			},
			target: "#commentReplyPopup",
			after: function(){}
		});
	});
	
	// 팝업 내 등록 버튼 클릭 시 답글 등록 요청
	$("#commentReplyRegistrationButton").click({
		before: function(){
			replyComment(jQuery("#commentReplyPopup").data("commentNo"));
		},
		target: "#commentReplyPopup"
	}, closeLayerPopup);
});

/**
 * 댓글 목록을 새로고침 합니다.
 * @param pagenum 조회할 페이지 번호. 1부터 시작
 * @param pagesize 한 페이지에 표시할 개수. 기본값은 20
 * @param pagebarCount 한 페이지 바에 표시할 페이지 개수. 기본값은 10
 * @returns
 */
function refreshCommentGroup(pagenum, pagesize, pagebarCount){
	if(!pagenum){
		pagenum = 1;
	}
	
	if(!pagesize){
		pagesize = PAGESIZE;
	}
	
	if(!pagebarCount){
		pagebarCount = PAGEBAR_COUNT;
	}
	
	jQuery.ajax({
		type: "GET",
		url: CONTEXT_PATH + '/comment-group/' + jQuery("#commentGroupNo").text() + `?pagenum=${pagenum}&pagesize=${pagesize}`,
		contentType: 'application/json',
		dataType: "json",	// 서버에서 응답한 데이터를 클라이언트에서 읽는 방식
		beforeSend: function(xhr){
			xhr.setRequestHeader("X-Ajax-call", "true");	// CustomAccessDeniedHandler에서 Ajax요청을 구분하기 위해 약속한 값
		},
		success: function(data, statusText, xhr){
			//console.log("응답값: ", data);
			replactCommentList(data.rows);
			replacePagingBar(pagenum, pagesize, data.totalRows, pagebarCount);
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
	var paddingLeft = 0;
	var commentRegDate = "";
	var commentModDate = "";
	var commentSecretIcon = "";
	
	/* rows:[
	 *	{
	 *		openType:"PUBLIC"
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
		paddingLeft = (row.commentClassDepth - 1) * 20;
		commentRegDate = getDateString(row.commentRegDate);
		commentModDate = (row.commentRegDate != row.commentModDate) ? ("마지막 수정일: " + getDateString(row.commentModDate)) : "수정 이력이 없습니다.";
		commentSecretIcon = (row.commentSecretFlag == true) ? "비밀글" : "";
		commentListString += `
			<li style="padding-left: ${paddingLeft}px;">
				<header>
					<div class="info-panel">
						<span class="nickname" title="${row.memNo}">${row.nickname}</span>
						<span class="comment-reg-date" title="${commentModDate}">${commentRegDate}</span>
						<span class="comment-no-shop" title="댓글 번호"># <span class="comment-no">${row.commentNo}</span></span>
						<span class="comment-secret-flag">${commentSecretIcon}</span>
					</div>
					<div class="edit-panel">
						<button id="commentReplyButton" class="" type="button" data-comment-no="${row.commentNo}">
							<div class="text">답글</div>
						</button>
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
		`;
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
			openType: jQuery("#openType").val()
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

/**
 * 특정 댓글에 답글을 작성합니다.
 * @param commentNo 대상 댓글의 번호
 */
function replyComment(commentNo){
	var formData = {
			commentGroupNo: jQuery("#commentGroupNo").text(),
			commentBody: jQuery("#commentReplyRegistrationTextarea").val(),
			commentSecretFlag:  jQuery("#commentReplySecretFlag").is(":checked"),
			openType: jQuery("#openType").val()
	};
	
	// 등록 요청 전송
	var token = jQuery("meta[name='_csrf']").attr("content");
	var header = jQuery("meta[name='_csrf_header']").attr("content");
	
	console.log("전송할 데이터", JSON.stringify(formData));
	
	jQuery.ajax({
		type: "POST",
		url: `${CONTEXT_PATH}/comment/${commentNo}/reply`,
		data: JSON.stringify(formData),
		contentType: 'application/json',
		//dataType: "json",	// 서버에서 응답한 데이터를 클라이언트에서 읽는 방식
		beforeSend: function(xhr){
			xhr.setRequestHeader("X-Ajax-call", "true");	// CustomAccessDeniedHandler에서 Ajax요청을 구분하기 위해 약속한 값
			xhr.setRequestHeader(header, token);	// 헤더의 csrf meta태그를 읽어 CSRF 토큰 함께 전송
		},
		success: function(data, statusText, xhr){
			console.log("응답값: ", data);
			var currentURL = location.href;
			var pageParameterIndex = currentURL.lastIndexOf("#");
			if(pageParameterIndex > 0){
				refreshCommentGroup(currentURL.substring(pageParameterIndex + 1), PAGESIZE, PAGEBAR_COUNT);
			}else{
				refreshCommentGroup(1, PAGESIZE, PAGEBAR_COUNT);
			}
			
		},
		error: function(xhr){
			console.log("error", xhr);
		}
	});
}

/**
 * 페이징바를 교체합니다.
 * @param pagenum
 * @param pagesize
 * @param totalRows
 * @param pagebarCount
 * @returns
 */
function replacePagingBar(pagenum, pagesize, totalRows, pagebarCount){
	var param = {
		"pagenum":pagenum,
		"pagesize":pagesize,
		"totalRows":totalRows,
		"pagebarCount":pagebarCount
	};
		
	var pagingBar = getPagingBar(param);
	jQuery("#pagination").html(pagingBar);
}