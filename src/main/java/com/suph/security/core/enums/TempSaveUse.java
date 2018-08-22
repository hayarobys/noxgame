package com.suph.security.core.enums;
/*	임시 저장시 TempSaveCategory에서도 어떤 용도의 임시 저장 데이터인지(수정인지 신규인지) 용도 분류를 위한 Enum 입니다.
	1신규작성,
	2수정
 */
public enum TempSaveUse{
	WRITE(1), MODIFY(2);
	
	private final int value;
	
	private TempSaveUse(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
	
	public static TempSaveUse valueOf(int value){
		switch(value){
		case 1: return TempSaveUse.WRITE;
		case 2: return TempSaveUse.MODIFY;
		default: throw new AssertionError("Unknown temp save use: " + value);
		}
	}
}


















