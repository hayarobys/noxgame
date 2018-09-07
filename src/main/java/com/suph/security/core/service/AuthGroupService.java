package com.suph.security.core.service;

import java.util.List;
import java.util.Map;

import com.suph.security.core.dto.AuthDTO;
import com.suph.security.core.dto.AuthGroupDTO;
import com.suph.security.core.dto.PaginationRequest;
import com.suph.security.core.enums.AuthGroup;

public interface AuthGroupService{
	/**
	 * 모든 권한 그룹 목록을 조회합니다.
	 * @param paginationRequest 페이징 검색 조건
	 * @return
	 */
	public abstract Map<String, Object> getAuthGroupList(PaginationRequest paginationRequest);
	
	/**
	 * 특정 권한 그룹 접근에 필요한 권한 목록을 조회합니다.
	 * @param authGroup
	 * @return
	 */
	public abstract List<AuthDTO> getAuthListByAuthGroup(AuthGroup authGroup);
	
	/**
	 * 권한 그룹을 등록합니다.
	 * @param authGroupDTO
	 * @return
	 */
	public abstract Map<String, Object> postAuthGroup(AuthGroupDTO authGroupDTO);
	
	/**
	 * 특정 권한 그룹을 수정합니다.
	 * @param authGroupDTO
	 * @return
	 */
	public abstract Map<String, Object> patchAuthGroupByAuthGroup(AuthGroupDTO authGroupDTO);
	
	/**
	 * 특정 권한 그룹을 삭제합니다.
	 * @return
	 */
	public abstract Map<String, Object> deleteAuthGroupByAuthGroup(AuthGroup authGroup);
}
