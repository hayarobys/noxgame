package com.suph.security.core.dto;

import com.suph.security.core.enums.AuthGroup;

/** 권한 그룹을 옮기는데 사용합니다 */
//AUTH_GRP_TB 권한 그룹 테이블
public class AuthGroupDTO{	
	/** 권한 그룹 */
	private AuthGroup authGroup;
	/** 권한 그룹 설명 */
	private String authGroupExplanation;

	public AuthGroup getAuthGroup(){
		return authGroup;
	}

	public void setAuthGroup(AuthGroup authGroup){
		this.authGroup = authGroup;
	}

	public String getAuthGroupExplanation(){
		return authGroupExplanation;
	}

	public void setAuthGroupExplanation(String authGroupExplanation){
		this.authGroupExplanation = authGroupExplanation;
	}

	@Override
	public String toString(){
		return "AuthGroupDTO [authGroup=" + authGroup + ", authGroupExplanation=" + authGroupExplanation + "]";
	}
}
