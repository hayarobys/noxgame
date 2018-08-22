package com.suph.security.core.enums;
/*	임시 저장시 어느 게시판의 데이터인지 이 Enum으로 분류합니다.
	1회원자료실,
	2자유게시판,
	3스크린샷,
	4카툰 갤러리,
	5추천전략,
	6마법세팅,
	7아레나&엘리,
	8퀘스트,
	9싱글플레이,
	10자유소설,
	11회원별 소설,
	12질문하기,
	13운영방침,
	14건의&문의,
	15오프모임
 */
public enum TempSaveCategory{
	MEMBER_SOFTWARE(1),FREEBOARD(2), SCREENSHOT(3), ART_GALLERY(4), RECOMMEND_STRATEGY(5);
	
	private final int value;
	
	private TempSaveCategory(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
	
	public static TempSaveCategory valueOf(int value){
		switch(value){
		case 1: return TempSaveCategory.MEMBER_SOFTWARE;
		case 2: return TempSaveCategory.FREEBOARD;
		case 3: return TempSaveCategory.SCREENSHOT;
		case 4: return TempSaveCategory.ART_GALLERY;
		case 5: return TempSaveCategory.RECOMMEND_STRATEGY;
		default: throw new AssertionError("Unknown temp save category: " + value);
		}
	}
}


















