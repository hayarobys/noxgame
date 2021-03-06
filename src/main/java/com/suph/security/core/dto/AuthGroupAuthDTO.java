package com.suph.security.core.dto;

import java.util.List;

import com.suph.security.core.enums.AuthGroup;

/**
 * 권한 그룹-권한 정보를 담는데 사용됩니다.
 * (권한 그룹 접근에 필요한 권한 정보)
 */
// AUTH_GRP_AUTH_TB 테이블
public class AuthGroupAuthDTO{
	/** 권한 그룹 일련 번호 */
	private AuthGroup authGroup;
	/** 권한 일련 번호 */
	private Integer authFkPk;
	/** 접근 가능한 권한(필요권한) */
	private String authNmUnq;	// authority
	/** 접근 가능한 권한 목록 */
	private List<Integer> authSqPkList;

	public AuthGroup getAuthGroup(){
		return authGroup;
	}

	public void setAuthGroup(AuthGroup authGroup){
		this.authGroup = authGroup;
	}

	public Integer getAuthFkPk(){
		return authFkPk;
	}

	public void setAuthFkPk(Integer authFkPk){
		this.authFkPk = authFkPk;
	}

	public String getAuthNmUnq(){
		return authNmUnq;
	}

	public void setAuthNmUnq(String authNmUnq){
		this.authNmUnq = authNmUnq;
	}

	public List<Integer> getAuthSqPkList(){
		return authSqPkList;
	}

	public void setAuthSqPkList(List<Integer> authSqPkList){
		this.authSqPkList = authSqPkList;
	}

	@Override
	public String toString(){
		return "AuthgroupAuthDTO [authGroup=" + authGroup + ", authFkPk=" + authFkPk + ", authNmUnq=" + authNmUnq + ", authSqPkList=" + authSqPkList
				+ "]";
	}
}
