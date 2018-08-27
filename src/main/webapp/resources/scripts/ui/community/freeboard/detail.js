$(function() {
	// 수정 버튼 클릭 시 수정 페이지로 이동
	$("#modify-btn").on("click", function(){
		$(location).attr("href", CONTEXT_PATH + "/community/freeboard/" + $("#freeboardGroupNo").text() + "/edit");
	});
});