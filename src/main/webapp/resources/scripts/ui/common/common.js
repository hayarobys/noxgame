
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


var PageMaker = {
	start: 1,
	page: 0,
	count: 0,
	end: 0,
	prev: false,
	next: false,
	getPage: function(){
		return page;
	},
	setPage: function(page){
		if(page < 1){
			this.page = 1;
			return;
		}
		
		this.page = page;
	},
	setCount(count){
		if(count < 1){
			return;
		}
		
		this.count = count;
		//console.log("총 컬럼 갯수 = " + count);
		this.calcPage();
	},
	calcPage(){
		// page변수는 현재 페이지 번호
		var tempEnd = Math.ceil(this.page / 10.0) * 10;
		// 현재 페이지번호를 기준으로 끝 페이지를 계산한다.
				
		// 시작 페이지 계산
		this.start = tempEnd - 9;
		
		if(tempEnd * 10 > this.count){ // 가상으로 계산한 tempEnd 크기가 실제 count보다 많을 경우
			this.end = Math.ceil(this.count / 10.0);
		}else{
			this.end = tempEnd; // 실제 count가 tempEnd보다 많을 경우
		}
				
		this.prev = this.start != 1;
		this.next = this.end * 10 < this.count;
	},
	/**
	 * 페이징바를 반환합니다.
	 * @param param{
	 *  url: 페이지 버튼에 연결할 링크
	 *  pagenumParam: 페이지 번호를 넣을 파라미터명
	 *  pagenum: 현재 페이지 번호. 1부터 시작
	 *  pagesizeParam: 한 페이지당 출력 개수를 넣을 파라미터명
	 *  pagesize: 한 페이지당 출력 개수
	 *  totalRows: 전체 데이터 개수
	 * }
	 * @returns 만들어진 페이징 바의 html코드를 텍스트로 반환
	 */
	getPagingBar: function(param){
		var path = param.url + '?' + param.pagesizeParam + '=' + param.pagesize + '&' + param.pagenumParam + '=';
		
		PageMaker.setPage(param.pagenum);
		PageMaker.setCount(param.totalRows); // 페이지 계산
		
		var str = '';
			str +=	'<ul class="paging-bar">';
		if(PageMaker.prev){
			str +=	'	<li><a href="#' + (PageMaker.start - 1) + '" class="pageButton" data-pagenum="' + (PageMaker.start - 1) + '">이전</a></li>';
		}
		
		for(idx = PageMaker.start; idx <= PageMaker.end; idx++){
			str +=	'	<li>';
			str +=	'		<a href="#' + idx + '" class="pageButton' + ((idx == PageMaker.page) ? ' current' : '') + '" data-pagenum="' + idx + '">' + idx + '</a>';
			str +=	'	</li>';
		}
		
		if(PageMaker.next){
			str +=	'	<li><a href="#' + (PageMaker.end + 1) + '" class="pageButton" data-pagenum="' + (PageMaker.end + 1) + '">다음</a></li>';
		}
			str +=	'<ul>';
		
		return str;
	}
}









