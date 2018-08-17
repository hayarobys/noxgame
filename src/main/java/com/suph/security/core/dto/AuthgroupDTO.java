package com.suph.security.core.dto;

/** 권한 그룹을 옮기는데 사용합니다 */
//AUTH_GRP_TB 권한 그룹 테이블
public class AuthgroupDTO{
	/** 권한 그룹 일련 번호 */
	private Integer authgroupNo;		
	/** 권한 그룹 명 */
	private String authgroupName;
	/** 권한 그룹 설명 */
	private String authgroupExplanation;

	public Integer getAuthgroupNo(){
		return authgroupNo;
	}

	public void setAuthgroupNo(Integer authgroupNo){
		this.authgroupNo = authgroupNo;
	}

	public String getAuthgroupName(){
		return authgroupName;
	}

	public void setAuthgroupName(String authgroupName){
		this.authgroupName = authgroupName;
	}

	public String getAuthgroupExplanation(){
		return authgroupExplanation;
	}

	public void setAuthgroupExplanation(String authgroupExplanation){
		this.authgroupExplanation = authgroupExplanation;
	}

	@Override
	public String toString(){
		return "AuthgroupDTO [authgroupNo=" + authgroupNo + ", authgroupName=" + authgroupName + ", authgroupExplanation=" + authgroupExplanation
				+ "]";
	}
}
