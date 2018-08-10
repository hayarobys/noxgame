package com.suph.security.core.enums;

public enum MemberState{
	ACTIVE("활성 계정"),
	//DORMANT("휴면 계정"),
	WITHDRAWAL("탈퇴 계정");
	
	private String state;
	
	private MemberState(String state){
		this.state = state;
	}
	
	public String getState(){
		return state;
	}
}