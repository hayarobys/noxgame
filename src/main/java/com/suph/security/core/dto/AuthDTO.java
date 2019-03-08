package com.suph.security.core.dto;

/** 계정 보유 권한을 옮기는데 사용합니다 */
// TB_AUTH 권한 테이블
public class AuthDTO{
	/** AUTH_NO 권한 일련 번호 */
	private Integer authSqPk;
	/** AUTH_NM 권한 명 */
	private String authNmUnq;
	/** AUTH_EXPLANATION 권한 설명 */
	private String authExplanation;
	/** PRIORITY 우선순위 */
	private Integer priority;

	/**
	 * 권한 일련 번호를 반환합니다.
	 * 
	 * @return
	 */
	public Integer getAuthSqPk(){
		return authSqPk;
	}

	/**
	 * 이 권한의 일련 번호를 저장/변경 합니다.
	 * 
	 * @param authSqPk
	 */
	public void setAuthSqPk(Integer authSqPk){
		this.authSqPk = authSqPk;
	}

	/**
	 * 권한명을 반환합니다.
	 * 
	 * @return
	 */
	public String getAuthNmUnq(){
		return authNmUnq;
	}

	/**
	 * 권한명을 저장/변경 합니다.
	 * 
	 * @param authNmUnq
	 */
	public void setAuthNmUnq(String authNmUnq){
		this.authNmUnq = authNmUnq.trim();
	}

	/**
	 * 권한 설명을 반환합니다.
	 * 
	 * @return
	 */
	public String getAuthExplanation(){
		return authExplanation;
	}

	/**
	 * 권한 설명을 저장/변경 합니다.
	 * 
	 * @param authExplanation
	 */
	public void setAuthExplanation(String authExplanation){
		this.authExplanation = authExplanation.trim();
	}

	public Integer getPriority(){
		return priority;
	}

	public void setPriority(Integer priority){
		this.priority = priority;
	}

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("AuthDTO [authSqPk=").append(authSqPk).append(", authNmUnq=").append(authNmUnq)
				.append(", authExplanation=").append(authExplanation).append(", priority=").append(priority)
				.append("]");
		return builder.toString();
	}

}
