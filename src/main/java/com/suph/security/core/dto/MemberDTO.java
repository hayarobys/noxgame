package com.suph.security.core.dto;

public class MemberDTO extends PaginationRequest{	
	/** MEM_NO 계정 일련 번호 PK */
	private Integer memSqPk;
	/** MEM_ID 계정 아이디 */
	private String memId;
	/** MEM_PASSWORD 계정 비밀번호 */
	private transient String memPassword;
	/** MEM_NICKNM 계정 사용자의 이름 또는 별명 */
	private String memNicknm;
	/** 계정 상태 (활성, 휴면, 차단, 탈퇴, ...) */
	private String memState;
	/** LAST_LOGIN_DATE 마지막 로그인 일자 */
	private java.util.Date memLastLoginDt;

	public Integer getMemSqPk(){
		return memSqPk;
	}

	public void setMemSqPk(Integer memSqPk){
		this.memSqPk = memSqPk;
	}

	public String getMemId(){
		return memId;
	}

	public void setMemId(String memId){
		this.memId = memId;
	}

	public String getMemPassword(){
		return memPassword;
	}

	public void setMemPassword(String memPassword){
		this.memPassword = memPassword;
	}

	public String getMemNicknm(){
		return memNicknm;
	}

	public void setMemNicknm(String memNicknm){
		this.memNicknm = memNicknm;
	}

	public String getMemState(){
		return memState;
	}

	public void setMemState(String memState){
		this.memState = memState;
	}

	public java.util.Date getMemLastLoginDt(){
		return memLastLoginDt;
	}

	public void setMemLastLoginDt(java.util.Date memLastLoginDt){
		this.memLastLoginDt = memLastLoginDt;
	}

	@Override
	public String toString(){
		return "MemberDTO [memSqPk=" + memSqPk + ", memId=" + memId + ", memNicknm=" + memNicknm + ", memState=" + memState + ", memLastLoginDt="
				+ memLastLoginDt + "]";
	}
}
