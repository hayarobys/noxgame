package com.suph.security.core.enums;

public enum Authgroup{
	/*
	 * 전체공개(PUBLIC)
	 * 회원공개(MEMBER)
	 * 비밀글(SECRET)
	 * 비공개(CLOSE)
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
