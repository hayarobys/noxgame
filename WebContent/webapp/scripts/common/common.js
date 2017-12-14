var prefix = "/noxgame/api";
var debug = true;	// consoel.log 사용시 if(debug){} 형식으로 사용. 이후 배포시 "debug"를 전체 검색하여 코드상에서 모두 제거.

/*ajax 공통*/
$.ajaxSetup({
	dataType : "json",
	contentType : "application/json;charset=UTF-8",
	cache : false,
	async : false,	
    beforeSend : function(xhr){
    	//xhr.setRequestHeader('loginToken', $.cookie('loginToken'));
    },
	success:function(result) {
		
	},
	error: function(jqXHR, textStatus, errorThrown) {
		var httpStatusCode = jqXHR.status;
		var httpStatusMessage = jqXHR.statusText;
		if(debug){
			console.log('상태코드: ', httpStatusCode);
			console.log('상태메시지: ', httpStatusMessage);
		}
		if(httpStatusCode == 401){
			$.removeCookie('loginToken');
			alert("로그인 후 오랜시간이 지났습니다. 다시 로그인해주세요.");
		}else if(httpStatusCode == 403){
			alert("사용 권한이 없습니다.");
		}else if(httpStatusCode == 406){
			$.removeCookie('loginToken');
			alert("로그인 토큰 정보가 올바르지 않습니다.\n다시 로그인해주세요.");
		}else if(httpStatusCode == 409){
			$.removeCookie('loginToken');
			alert("다시 로그인해주세요.");
		}else if(httpStatusCode == 499){
			alert("로그인이 필요한 기능입니다.");
		}else if(httpStatusCode == 0){
			alert("네트워크 연결 상태를 확인 해주세요.");
		}else{
			alert("에러가 발생했습니다. 시스템 관리자에게 문의 바랍니다.\nError Code: " + httpStatusCode + "\nError Message: " + httpStatusMessage);
		}
	}
});

/**
 * URL에서 파라미터 정보를 읽어들이는 함수입니다.
 * @param strParamName {string} 검색할 key 값 입니다.
 * @return 추출된 파라미터들을 Array 형태로 반환 합니다.
 */
function getParameter(strParamName) {
	var strURL = location.search;
	var tmpParam = strURL.substring(1).split("&");
	if(strURL.substring(1).length > 0){
		var Params = new Array;
		for(var i=0;i<tmpParam.length;i++){
			Params = tmpParam[i].split("=");
			if(strParamName == Params[0]){
				return Params[1];
			}
		}
	}
	return "";
}