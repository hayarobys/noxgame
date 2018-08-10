package com.suph.security.core.enums;

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
