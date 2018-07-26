package com.suph.security.core.dto;

import java.util.List;


/**
 * 리소스 정보를 담는데 사용됩니다.
 */
// TB_RESOURCE 테이블
public class ResourceDTO{
	/** 리소스 일련 번호 */
	private Integer resSqPk;
	/** 리소스 순서 */
	private Integer sortOrder;
	/** 리소스 타입 */
	private String resType;
	/** HTTP METHOD */
	private String httpMethodPk;
	/** 리소스 패턴 */
	private String resPattern;
	/** 리소스 이름 */
	private String resNmUnq;
	
	private List<ResourceDTO> list;

	public Integer getResSqPk(){
		return resSqPk;
	}

	public void setResSqPk(Integer resSqPk){
		this.resSqPk = resSqPk;
	}

	public Integer getSortOrder(){
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder){
		this.sortOrder = sortOrder;
	}

	public String getResType(){
		return resType;
	}

	public void setResType(String resType){
		this.resType = resType;
	}

	public String getHttpMethodPk(){
		return httpMethodPk;
	}

	public void setHttpMethodPk(String httpMethodPk){
		this.httpMethodPk = httpMethodPk;
	}

	public String getResPattern(){
		return resPattern;
	}

	public void setResPattern(String resPattern){
		this.resPattern = resPattern;
	}

	public String getResNmUnq(){
		return resNmUnq;
	}

	public void setResNmUnq(String resNmUnq){
		this.resNmUnq = resNmUnq;
	}

	public List<ResourceDTO> getList(){
		return list;
	}

	public void setList(List<ResourceDTO> list){
		this.list = list;
	}

	@Override
	public String toString(){
		return "ResourceDTO [resSqPk=" + resSqPk + ", sortOrder=" + sortOrder + ", resType=" + resType + ", httpMethodPk="
				+ httpMethodPk + ", resPattern=" + resPattern + ", resNmUnq=" + resNmUnq + ", list=" + list + "]";
	}
}
