package com.suph.security.core.dto;

import java.util.List;

/**
 * 리소스-권한 정보를 담는데 사용됩니다.
 * (리소스 접근에 필요한 권한 정보)
 */
// TB_RESOURCE_AUTH 테이블
public class ResourceAuthDTO{
	/** 리소스 일련 번호 */
	private Integer resFkPk;
	/** HTTP Method 패턴 (GET / POST / PATCH / DELETE / ...) */
	private String httpMethodPk;
	/** 리소스 이름 */
	private String resNmUnq;
	/** URL 패턴 */
	private String resPattern;	// pattern
	/** 권한 일련 번호 */
	private Integer authFkPk;
	/** 접근 가능한 권한(필요권한) */
	private String authNmUnq;	// authority
	/** 접근 가능한 권한 목록 */
	private List<Integer> authSqPkList;
	
	public Integer getResFkPk(){
		return resFkPk;
	}
	
	public void setResSqPk(Integer resFkPk){
		this.resFkPk = resFkPk;
	}
	
	public String getHttpMethodPk(){
		return httpMethodPk;
	}
	
	public void setHttpMethodPk(String httpMethodPk){
		this.httpMethodPk = httpMethodPk;
	}
	
	public String getResNmUnq(){
		return resNmUnq;
	}
	
	public void setResNmUnq(String resNmUnq){
		this.resNmUnq = resNmUnq;
	}
	
	public String getResPattern(){
		return resPattern;
	}

	public void setResPattern(String resPattern){
		this.resPattern = resPattern;
	}
	
	public Integer getAuthFkPk(){
		return authFkPk;
	}
	
	public void setAuthSqPk(Integer authFkPk){
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
		return "ResourceAuthDTO [resFkPk=" + resFkPk + ", httpMethodPk=" + httpMethodPk + ", resNmUnq=" + resNmUnq
				+ ", authFkPk=" + authFkPk + ", authNmUnq=" + authNmUnq + ", authSqPkList=" + authSqPkList + "]";
	}
}
