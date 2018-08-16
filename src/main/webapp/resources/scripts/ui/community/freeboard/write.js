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
	
	// 첨부파일 영역의 이미지 클릭시 현재 에디터 내 커서 위치에 해당 이미지를 삽입합니다.
	jQuery(document).on("click", "[data-type='attachment']", function(event){
		var fileNo = event.target.getAttribute("data-file-no");
		var title = event.target.title;
		var src = event.target.src;
		pasteHTML("<img data-file-no='" + fileNo + "' src='" + src + "' title='" + title + "' width='600px' />");
	});
	
	// 게시글 작성 완료 버튼 클릭 이벤트 입니다.
	jQuery("#writeComplete").on("click", function(event){
		event.preventDefault();
		oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);	// 에디터의 내용이 textarea에 적용됩니다.
		
		var formData = {
				tempSaveNo: jQuery("#tempSaveNo").val(),
				tempSaveTitle: jQuery("#tempSaveTitle").val(),
				tempSaveBody: jQuery("#ir1").val()
		};
		
		// 등록 요청 전송
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
				location.href = CONTEXT_PATH + "/community/" + data.data;
			},
			error: function(xhr){
				console.log("error", xhr);
			}
		});
	});
});

/**
 * iframe으로 띄워진 SE2에디터에서 작성한 내용을 개발자가 접근할 수 있는 textarea로 복사합니다.
 * @param sHTML
 * @returns
 */
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

/**
 * 첨부파일폼에 이미지 정보 추가
 * @returns
 */
function addPhotoInfoToAttachmentForm(htPhotoInfo){
	/*	htPhotoInfo is
		bNewLine:"true"
		sSaveFileName:"201808161750066922de9f-fead-4a73-a17a-8d8dd735e081.jpg"
		sFileNo:"45"
		sName:"waterfowl-lake-banff-national-park-alberta-canada.jpg"
		sOriginalImageURL:"/nox/resources/upload/201808161750066922de9f-fead-4a73-a17a-8d8dd735e081.jpg"
	 */
	var tag = '<img data-file-no="' + htPhotoInfo.sFileNo + '" data-type="attachment" id="' + htPhotoInfo.sSaveFileName + '" title="' + htPhotoInfo.sName + '" src="' + htPhotoInfo.sOriginalImageURL + '" style="width:100px; height: 100px;" />';
	jQuery("#attachmentPhoto").append(tag);
}
