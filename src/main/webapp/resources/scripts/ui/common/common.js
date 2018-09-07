
var CONTEXT_PATH = "/nox";

/**
* jwt의 payload를 파싱 합니다.
* signature 검사를 하지 않습니다.
*/
function parseJwt(token){
	var base64Url = token.split('.')[1];	// 점 단위로 잘라서(header, payload, signature) payload부를 얻습니다.
	var base64 = base64Url.replace('-', '+').replace('_', '/');	// 대쉬를 플러스로 바꾸고 언더바를 슬래시로 바꿉니다.
	return JSON.parse(window.atob(base64));	// base64타입으로 인코딩된 payload를 javascript의 window.atob()함수로 디코드 한 후, JSON 파싱 합니다.
}

/**
* 특정 쿠키를 불러옵니다.
*/
function getCookie(cname){
	var name = cname + "=";
	var decodedCookie = decodeURIComponent(document.cookie);
	var ca = decodedCookie.split(';');
	for(var i = 0; i < ca.length; i++){
		var c = ca[i];
		while(c.charAt(0) == ' '){
			c = c.substring(1);
		}
		if(c.indexOf(name) == 0){
			return c.substring(name.length, c.length);
		}
	}
}

/**
* 닉네임 쿠키로부터 jwt를 읽어 파싱한 뒤에 읽어들인 닉네임을 #nickname에 추가합니다.
*/
function getNickname(){
	var jwtNameToken = getCookie("info");
	if(jwtNameToken === undefined){
		$("#nickname").append("미로그인 상태 입니다.<br />");
		return;
	}
	var payload = parseJwt(jwtNameToken);
	$("#nickname").append(decodeURI(payload.name) + "<br />");
	console.log("쿠키내용",payload);
}

/**
 * 페이징 바의 html 코드를 생성해 반환합니다.
 * @param param{
 * 	totalRows: 전체 게시물 수,
 * 	pagenum: 현재 페이지 번호
 * 	pagesize: 한 페이지 당 게시물 수,
 * 	pagebarCount: 한 화면에 나타낼 페이지 번호 수,
 * }
 * @returns
 */
function getPagingBar(param){
	var totalData = param.totalRows;	// 전체 게시물 수
	var dataPerPage = param.pagesize;	// 한 페이지당 게시물 수
	var pageCount = param.pagebarCount;	// 한 화면에 나타낼 페이지 수
	var currentPage = param.pagenum;	// 현재 페이지 번호
	
	var totalPage = Math.ceil(totalData / dataPerPage);	// 총 페이지 수
	var pageGroup = Math.ceil(currentPage / pageCount); // 페이지 그룹(한 화면에 페이지를 열 개씩 출력한다면 1~10페이즈는 그룹1, 11~20페이지는 그룹2)
	var last = pageGroup * pageCount; // 화면에 보여질 마지막 페이지 번호
	
	if(last > totalPage){ last = totalPage; }
	var first = last - (pageCount-1); // 화면에 보여질 첫번째 페이지 번호
	if(first < 0){ first = 1; }
	var next = last + 1;	// 다음 버튼의 페이지 번호
	var prev = first - 1;	// 이전 버튼의 페이지 번호
	
	var html = "";
	html +=	`<ul class="paging-bar">`;
	if(prev > 0){
		html +=	`<li><a href="#${prev}" class="pageButton" data-pagenum="${prev}"><</a></li>`;
	}
	for(var idx = first; idx <= last; idx++){
		var currentClass = ((idx == currentPage) ? " current" : "");
		html +=	`<li><a href="#${idx}" class="pageButton${currentClass}" data-pagenum="${idx}">${idx}</a></li>`;
	}
	if(last < totalPage){
		html +=	`<li><a href="#${next}" class="pageButton" data-pagenum="${next}">></a></li>`;
	}
	html +=	`<ul>`;
	return html;
}

/**
 * long 형태의 숫자값을 날짜 형태로 변환합니다.
 * @param longValue
 * @returns
 */
function getDateString(longValue){
	if(longValue){
		var date = new Date(longValue);
		return	date.getFullYear() + "."
			+ ("0" + (date.getMonth() + 1)).slice(-2) + "."
			+ ("0" + date.getDate()).slice(-2) + " "
			+ ("0" + date.getHours()).slice(-2) + ":"
			+ ("0" + date.getMinutes()).slice(-2) + ":"
			+ ("0" + date.getSeconds()).slice(-2);
	}else{
		return "";
	}
}

/**
 * 지정한 아이디의 레이어 팝업을 숨김 처리 합니다.
 * 사용방법은 이러합니다.
 * 
 * 콜백 이벤트로서 사용하는 법
 * 	$('~~').click({
 * 		before: function(){},	// 팝업 닫기 전 수행 동작. 작성하지 않아도 무방합니다.
 * 		target: "#myPopupId",
 * 		after: function(){}		// 팝업 닫은 후 수행 동작. 작성하지 않아도 무방합니다.
 * 	}, closeLayerPopup);
 * 
 * 함수 직접 호출로 사용하는 법
 * 	closeLayerPopup({
 * 		before: function(){},
 * 		target: "#myPopupId",	// 타겟은 필수값 입니다.
 * 		after: function(){}
 * 	});
 * 
 * @param data {before, target, after} 숨길 팝업의 선택자와 콜백 함수를 지정합니다.
 */
function closeLayerPopup(param) {
    // $("selector").click({target:"selector2"}, closeLayerPopup);
    // closeLayerPopup({target:"selector2"})
    // 위와 같이 이벤트에 의해 호출될때와 직접 호출될때의 파라미터 위치가 다릅니다. 따라서 아래와 같이 삼항 연산자로 파라미터 존재 여부를 확인후에 그에 따른 처리를 해줍니다.
    handlesPopup((param.data ? param.data : param), true);
}

/**
 * 지정한 아이디의 레이어 팝업을 노출 시킵니다.
 * 사용방법은 이러합니다.
 * 
 *  콜백 이벤트로서 사용하는 법
 * 	$('~~').click({
 * 		before: function(){},	// 팝업 열기 전 수행 동작. 작성하지 않아도 무방합니다.
 * 		target: "#myPopupId",
 * 		after: function(){}		// 팝업 열은 후 수행 동작. 작성하지 않아도 무방합니다.
 * 	}, openLayerPopup);
 * 
 *  함수 직접 호출로 사용하는 법
 * 	openLayerPopup({
 * 		before: function(){},
 * 		target: "#myPopupId",	// 타겟은 필수값 입니다.
 * 		after: function(){}
 * 	});
 * @param data {before, target, after} 노출시킬 팝업의 선택자와 콜백 함수를 지정합니다.
 */
function openLayerPopup(param) {
    handlesPopup((param.data ? param.data : param), false);
}

/**
 * 레이어 팝업을 숨기거나 노출하고 그에 따른 콜백함수를 호출합니다.
 * 이 함수를 직접 호출하지 말고 위의 closeLayerPopup 또는 openLayerPopup을 경유하십시오.
 * @param data{before, target, after} 팝업의 선택자와 콜백 함수를 지정합니다.
 * @param isHidden 팝업의 숨김여부를 지정합니다. true: 숨김, false: 노출
 */
function handlesPopup(data, isHidden) {
    if (data.before) {
        data.before();
    }
    $(data.target).css("display", (isHidden ? "none" : "block"));
    if (data.after) {
        data.after();
    }
}





