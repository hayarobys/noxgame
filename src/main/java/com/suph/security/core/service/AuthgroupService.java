package com.suph.security.core.service;

import java.util.Map;

import com.suph.security.core.dto.AuthgroupDTO;
import com.suph.security.core.dto.PaginationRequest;
import com.suph.security.core.enums.Authgroup;

public interface AuthgroupService{
	/**
	 * 모든 권한 그룹 목록을 조회합니다.
	 * @param paginationRequest 페이징 검색 조건
	 * @return
	 */
	public abstract Map<String, Object> getAuthgroupList(PaginationRequest paginationRequest);
	
	/**
	 * 특정 권한 그룹 접근에 필요한 권한 목록을 조회합니다.
	 * @param authgroup
	 * @return
	 */
	public abstract Map<String, Object> getAuthListByAuthgroupNo(Authgroup authgroup);
	
	/**
	 * 권한 그룹을 등록합니다.
	 * @param authgroupDTO
	 * @return
	 */
	public abstract Map<String, Object> postAuthgroup(AuthgroupDTO authgroupDTO);
	
	/**
	 * 특정 권한 그룹을 수정합니다.
	 * @param authgroup
	 * @param authgroupDTO
	 * @return
	 */
	public abstract Map<String, Object> patchAuthgroupByAuthgroupNo(Authgroup authgroup, AuthgroupDTO authgroupDTO);
	
	/**
	 * 특정 권한 그룹을 삭제합니다.
	 * @return
	 */
	public abstract Map<String, Object> deleteAuthgroupByAuthgroupNo(Authgroup authgroup);
}
