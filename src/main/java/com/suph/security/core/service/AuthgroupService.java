package com.suph.security.core.service;

import java.util.Map;

import com.suph.security.core.dto.AuthDTO;
import com.suph.security.core.dto.AuthgroupDTO;
import com.suph.security.core.dto.PaginationRequest;

public interface AuthgroupService{
	/**
	 * 모든 권한 그룹 목록을 조회합니다.
	 * @param paginationRequest 페이징 검색 조건
	 * @return
	 */
	public abstract Map<String, Object> getAuthgroupList(PaginationRequest paginationRequest);
	
	/**
	 * 권한 그룹을 등록합니다.
	 * @param authgroupDTO
	 * @return
	 */
	public abstract Map<String, Object> postAuthgroup(AuthgroupDTO authgroupDTO);
	
	/**
	 * 특정 권한 그룹을 수정합니다.
	 * @param authgroupNo
	 * @param authgroupDTO
	 * @return
	 */
	public abstract Map<String, Object> patchAuthgroupByAuthgroupNo(Integer authgroupNo, AuthgroupDTO authgroupDTO);
	
	/**
	 * 특정 권한 그룹을 삭제합니다.
	 * @return
	 */
	public abstract Map<String, Object> deleteAuthgroupByAuthgroupNo(Integer authgroupNo);
}
