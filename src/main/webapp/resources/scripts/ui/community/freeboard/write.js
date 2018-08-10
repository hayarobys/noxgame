var oEditors = [];
var sLang = "ko_KR";	// 언어

jQuery(function() {
	jQuery.noConflict();
	// Naver SmartEditor2
	nhn.husky.EZCreator.createInIFrame({
	    oAppRef: oEditors,
	    elPlaceHolder: "ir1",
	    sSkinURI: CONTEXT_PATH + "/resources/scripts/smarteditor2-2.9.0/SmartEditor2Skin.html",
	    htParams : {
			bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
			//bSkipXssFilter : true,		// client-side xss filter 무시 여부 (true:사용하지 않음 / 그외:사용)
			//aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
			fOnBeforeUnload : function(){
				//alert("완료!");
			},
			I18N_LOCALE : sLang
		}, //boolean
		fOnAppLoad : function(){
			//예제 코드
			//oEditors.getById["ir1"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
		},
	    fCreator: "createSEditor2"

	});
	
	jQuery("[data-type='attachment']").on("click", function(event){
		var fileNo = event.target.getAttribute("data-file-no");
		var title = event.target.title;
		var src = event.target.src;
		console.log("이미지 클릭: ", event.target);
		pasteHTML("<img data-file-no='" + fileNo + "' src='" + src + "' title='" + title + "' width='600px' />");
	});
	
	jQuery("#writeComplete").on("click", function(event){
		event.preventDefault();
		oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);	// 에디터의 내용이 textarea에 적용됩니다.
		
		var formData = {
				tempSaveNo: jQuery("#tempSaveNo").val(),
				tempSaveTitle: jQuery("#tempSaveTitle").val(),
				tempSaveBody: jQuery("#ir1").val()
		};
		
		// 출력
		console.log("전송할 json 데이터", formData);
		
		// 수정 요청 전송
		var token = jQuery("meta[name='_csrf']").attr("content");
		var header = jQuery("meta[name='_csrf_header']").attr("content");
		
		jQuery.ajax({
			type: "POST",
			url: CONTEXT_PATH + "/community/freeboard/write",
			data: JSON.stringify(formData),
			contentType: 'application/json',
			dataType: "json",	// 서버에서 응답한 데이터를 클라이언트에서 읽는 방식
			beforeSend: function(xhr){
				xhr.setRequestHeader("X-Ajax-call", "true");	// CustomAccessDeniedHandler에서 Ajax요청을 구분하기 위해 약속한 값
				xhr.setRequestHeader(header, token);	// 헤더의 csrf meta태그를 읽어 CSRF 토큰 함께 전송
			},
			success: function(data, statusText, xhr){
				console.log("응답값: ", data);
			},
			error: function(xhr){
				console.log("error", xhr);
			}
		});
	});
});

function pasteHTML(sHTML) {
	//var sHTML = "<span style='color:#FF0000;'>이미지도 같은 방식으로 삽입합니다.<\/span>";
	oEditors.getById["ir1"].exec("PASTE_HTML", [sHTML]);
}

function showHTML() {
	var sHTML = oEditors.getById["ir1"].getIR();
	alert(sHTML);
}

/*	
function submitContents(elClickedObj) {
	oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);	// 에디터의 내용이 textarea에 적용됩니다.
	
	// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("ir1").value를 이용해서 처리하면 됩니다.
	
	try {
		elClickedObj.form.submit();
	} catch(e) {}
}
*/

function setDefaultFont() {
	var sDefaultFont = '궁서';
	var nFontSize = 24;
	oEditors.getById["ir1"].setDefaultFont(sDefaultFont, nFontSize);
}


