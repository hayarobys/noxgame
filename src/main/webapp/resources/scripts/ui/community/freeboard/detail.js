
$(function() {
	// 목록 버튼 클릭 시 목록 페이지로 이동
	$("#list-btn").on("click", function(){
		$(location).attr("href", CONTEXT_PATH + "/community/freeboard");
	});
	
	// 수정 버튼 클릭 시 수정 페이지로 이동
	$("#modify-btn").on("click", function(){
		$(location).attr("href", CONTEXT_PATH + "/community/freeboard/" + $("#freeboardGroupNo").text() + "/edit");
	});
	
	// 게시글 삭제 버튼 클릭 이벤트 입니다.
	jQuery("#delete-btn").on("click", function(event){
		event.preventDefault();
		
		// 삭제 요청 전송
		var token = jQuery("meta[name='_csrf']").attr("content");
		var header = jQuery("meta[name='_csrf_header']").attr("content");
		
		jQuery.ajax({
			type: "DELETE",
			url: CONTEXT_PATH + "/community/freeboard/" + jQuery("#freeboardGroupNo").text(),
			contentType: 'application/json',
			//dataType: "json",	// 서버에서 응답한 데이터를 클라이언트에서 읽는 방식
			beforeSend: function(xhr){
				xhr.setRequestHeader("X-Ajax-call", "true");	// CustomAccessDeniedHandler에서 Ajax요청을 구분하기 위해 약속한 값
				xhr.setRequestHeader(header, token);	// 헤더의 csrf meta태그를 읽어 CSRF 토큰 함께 전송
			},
			success: function(data, statusText, xhr){
				console.log("응답값: ", data);
				//location.href = CONTEXT_PATH + "/community/freeboard/" + data.data;
			},
			error: function(xhr){
				console.log("error", xhr);
			}
		});
	});
});