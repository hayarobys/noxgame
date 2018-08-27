package com.suph.security.core.dto;

import com.suph.security.core.enums.Authgroup;

/** 권한 그룹을 옮기는데 사용합니다 */
//AUTH_GRP_TB 권한 그룹 테이블
public class AuthgroupDTO{	
	/** 권한 그룹 */
	private Authgroup authgroup;
	/** 권한 그룹 설명 */
	private String authgroupExplanation;

	public Authgroup getAuthgroup(){
		return authgroup;
	}

	public void setAuthgroup(Authgroup authgroup){
		this.authgroup = authgroup;
	}

	public String getAuthgroupExplanation(){
		return authgroupExplanation;
	}

	public void setAuthgroupExplanation(String authgroupExplanation){
		this.authgroupExplanation = authgroupExplanation;
	}

	@Override
	public String toString(){
		return "AuthgroupDTO [authgroup=" + authgroup + ", authgroupExplanation=" + authgroupExplanation + "]";
	}
}
