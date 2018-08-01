jQuery(function() {
	
	var bannerURL = CONTEXT_PATH + "/resources/image/common/banner/banner" + getRandomBannerNumber(0, 1) + ".gif";
	jQuery(".header img#banner").attr("src", bannerURL);
});

/**
 * min(포함)과 max(포함) 사이의 임의 자연수를 반환하되, 한자리 수라면 0을 붙여줍니다. 00, 01, 02, 03...
 */
function getRandomBannerNumber(min, max){
	var num = getRandomIntInclusive(min, max);
	return result =  (num < 10) ? ("0" + num) : num;
}

/**
 * min (포함) 과 max (포함) 사이의 임의 정수를 반환
 * Math.round() 를 사용하면 고르지 않은 분포를 얻게된다!
 */
function getRandomIntInclusive(min, max) {
	return Math.floor(Math.random() * (max - min + 1)) + min;
}