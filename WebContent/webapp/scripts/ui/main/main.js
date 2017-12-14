$(function(){
	init();
});

function init(){
	$.ajax({
		url : prefix + "/main/message",
		success : function(data){
			if(data.result == 'success'){
				if(debug)	console.log('메시지 요청 성공', data.message);
			}else if(data.result == 'fail'){
				alert("입력 정보를 확인해 주세요.");
			}else{
				console.log(data.result);
			}
		}
	});
}