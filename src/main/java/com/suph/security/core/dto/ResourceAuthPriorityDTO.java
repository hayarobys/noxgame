package com.suph.security.core.dto;

import java.util.List;

/**
 * 각 리소스별 필요 권한과 우선순위 정보를 담는데 사용됩니다.
 */
public class ResourceAuthPriorityDTO{
	/** 리소스 일련 번호 */
	private Integer resSqPk;

	private List<AuthPrioritySet> authPrioritySetArray;

	public Integer getResSqPk(){
		return resSqPk;
	}

	public void setResSqPk(Integer resSqPk){
		this.resSqPk = resSqPk;
	}

	public List<AuthPrioritySet> getAuthPrioritySetArray(){
		return authPrioritySetArray;
	}

	public void setAuthPrioritySetArray(List<AuthPrioritySet> authPrioritySetArray){
		this.authPrioritySetArray = authPrioritySetArray;
	}

	private class AuthPrioritySet{
		/** 접근 가능한 권한(필요권한) */
		private String authSqPk; // authority
		/** 권한 우선순위 */
		private Integer priority;

		public String getAuthSqPk(){
			return authSqPk;
		}

		public void setAuthSqPk(String authSqPk){
			this.authSqPk = authSqPk;
		}

		public Integer getPriority(){
			return priority;
		}

		public void setPriority(Integer priority){
			this.priority = priority;
		}
	}
}
