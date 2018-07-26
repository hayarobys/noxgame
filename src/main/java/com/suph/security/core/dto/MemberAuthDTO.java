package com.suph.security.core.dto;

import java.util.List;

/**
 * 계정-권한 정보를 담는데 사용합니다.
 */
public class MemberAuthDTO{
	/** MEM_NO: 계정 일련 번호 */
	private Integer memFkPk;
	/** 보유 권한 목록 */
	private List<Integer> authFkPkList;
	
	public Integer getMemFkPk(){
		return memFkPk;
	}
	
	public void setMemFkPk(Integer memFkPk){
		this.memFkPk = memFkPk;
	}
	
	public List<Integer> getAuthFkPkList(){
		return authFkPkList;
	}
	
	public void setAuthFkPkList(List<Integer> authFkPkList){
		this.authFkPkList = authFkPkList;
	}
	
	@Override
	public String toString(){
		return "MemberAuthDTO [memFkPk=" + memFkPk + ", authFkPkList=" + authFkPkList + "]";
	}
}
