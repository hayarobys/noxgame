var PAGESIZE = 12;
var PAGEBAR_COUNT = 7;

$(function() {
	/*console.log('RxJS included?', !!Rx);*/
	
	// 글쓰기 버튼 클릭 시 작성 페이지로 이동
	$("#write-btn").on("click", function(){
		$(location).attr("href", CONTEXT_PATH + "/community/freeboard/write");
	});
	
	// 페이징바 클릭 이벤트 부여
	jQuery(document).on("click", "a[data-pagenum]", function(event){
		var pagenum = event.target.getAttribute("data-pagenum");
		//console.log("pagenum", pagenum);
		reloadFreeboardList(pagenum, PAGESIZE, PAGEBAR_COUNT);
	});
	
	// 최초 1회 목록 갱신. URL끝에 #27 과 같은 페이지 번호가 있다면 읽어들입니다.
	var currentURL = location.href;
	var pageParameterIndex = currentURL.lastIndexOf("#");
	if(pageParameterIndex > 0){
		reloadFreeboardList(currentURL.substring(pageParameterIndex + 1), PAGESIZE, PAGEBAR_COUNT);
	}else{
		reloadFreeboardList(1, PAGESIZE, PAGEBAR_COUNT);
	}
});


/**
 * 자유게시판의 게시글 목록과 페이징 바를 갱신합니다.
 * @param pagenum 조회할 페이지 번호. 1부터 시작
 * @param pagesize 한 페이지에 표시할 개수. 기본값은 20
 * @param pagebarCount 한 페이지 바에 표시할 페이지 개수. 기본값은 10
 * @returns
 */
function reloadFreeboardList(pagenum, pagesize, pagebarCount){
	if(pagenum == false){
		pagenum = 1;
	}
	
	if(pagesize == false){
		pagesize = 20;
	}
	
	if(pagebarCount == false){
		pagebarCount = 10;
	}
	
	// 게시글 목록 조회	
	jQuery.ajax({
		type: "GET",
		url: `${CONTEXT_PATH}/community/freeboard/list?pagenum=${pagenum}&pagesize=${pagesize}`,
		contentType: 'application/json',
		dataType: "json",	// 서버에서 응답한 데이터를 클라이언트에서 읽는 방식
		beforeSend: function(xhr){
			xhr.setRequestHeader("X-Ajax-call", "true");	// CustomAccessDeniedHandler에서 Ajax요청을 구분하기 위해 약속한 값
		},
		success: function(data, statusText, xhr){
			//console.log("data", data);
			replaceFreeboardList(data.rows);
			replacePagingBar(pagenum, pagesize, data.totalRows, pagebarCount);
		},
		error: function(xhr){
			console.log("error", xhr);
		}
	});
}

/**
 * 전달받은 데이터로 게시글 목록을 교체합니다.
 * @param rows
 * @returns
 */
function replaceFreeboardList(rows){
	/*
	freeboardGroupNo:11
	freeboardGroupRegDate:1535594290000
	freeboardTitle:"제목11-2"
	hits:0
	memId:"admin"
	memNickname:"관리자"
	memNo:1
	openType:"CLOSE"
	*/
	var str = `
			<ul class="contents-title">
				<li>
					<dl class="item-list">
						<dt class="group-no">그룹번호</dt>
						<dt class="nickname">닉네임</dt>
						<dt class="title">제목</dt>
						<dt class="reg-date">등록일</dt>
						<dt class="hits">조회수</dt>
					</dl>
				</li>
			</ul>
	`;
	if(rows.length == 0){
		str += `
			<span class="no-contents">등록된 글이  없습니다 ^^;</span>
		`;
	}else{
		str += `
			<ul class="contents-list">
		`;
		// rows를 루프하며 각 행은 row, 현재 루프 회수(배열번호와 동일)는 index(0부터 시작), 전체 데이터는 array(rows와 동일)에 전달
		var lastRegDateTitle = "";
		var freeboardGroupRegDate = "";
		rows.forEach(function(row, index, array){
			lastRegDateTitle =  (row.freeboardGroupRegDate != row.freeboardLastRegDate) ? ("마지막 수정일: " + getDateString(row.freeboardLastRegDate)) : "수정 이력이 없습니다.";
			freeboardGroupRegDate = getDateString(row.freeboardGroupRegDate);
			str += `
				<li>
					<dl class="item-list">
						<dt class="group-no">${row.freeboardGroupNo}</dt>
						<dt class="nickname" title="${row.memNo}">${row.memNickname}</dt>
						<a href="${CONTEXT_PATH}/community/freeboard/${row.freeboardGroupNo}" id="groupNo${row.freeboardGroupNo}">
							<dt class="title">${row.freeboardTitle}</dt>
							<dt class="reg-date" title="${lastRegDateTitle}">${freeboardGroupRegDate}</dt>
							<dt class="hits">${row.hits}</dt>
						</a>
					</dl>
				</li>
			`
		});
		
		str += `
			</ul>
		`;
	}
	jQuery("#freeboardList").html(str);
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


	
	






