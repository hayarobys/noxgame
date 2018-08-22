package com.suph.security.core.enums;

/**
 * DB상 상태 플래그에 대해 1, 2와 같은 불분명한 숫자 대신 Y, N과 같은 의도를 분명히 하기 위해 쓰이는 열거형 입니다.
 */
public enum Flag{
	Y(1),N(2);
	
	private final int value;
	
	private Flag(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
	
	public static Flag valueOf(int value){
		switch(value){
		case 1: return Flag.Y;
		case 2: return Flag.N;
		default: throw new AssertionError("Unknown flag: " + value);
		}
	}
}
