package com.suph.security.core.enums;

public enum Authgroup{
	/*
	 * 권한 그룹에 대한 열거형 입니다. 반드시 DB의 권한 그룹 레코드와 1:1 매칭되어야 합니다.
	 * 1전체공개(PUBLIC)
	 * 2회원공개(MEMBER)
	 * 3비밀글(SECRET)
	 * 4비공개(CLOSE)
	 */
	PUBLIC(1), MEMBER(2), SECRET(3), CLOSE(4);	
	private final int value;
	
	private Authgroup(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
	
	public static Authgroup valueOf(int value){
		switch(value){
		case 1: return Authgroup.PUBLIC;
		case 2: return Authgroup.MEMBER;
		case 3: return Authgroup.SECRET;
		case 4: return Authgroup.CLOSE;
		default: throw new AssertionError("Unknown flag: " + value);
		}
	}
	
	
}
