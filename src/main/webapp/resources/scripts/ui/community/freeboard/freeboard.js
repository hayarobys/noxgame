$(function() {
	// 글쓰기 버튼 클릭 시 작성 페이지로 이동
	$("#write-btn").on("click", function(){
		$(location).attr("href", CONTEXT_PATH + "/community/freeboard/edit");
	});
});