package com.suph.security.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.suph.security.core.dto.AuthGroupDTO;
import com.suph.security.core.dto.PaginationRequest;
import com.suph.security.core.enums.AuthGroup;

@Repository
public interface AuthGroupDAO{
	
	/**
	 * 모든 권한 그룹 목록을 페이징 처리하여 반환합니다.
	 * @param paginationRequest 페이징 검색조건
	 * @return
	 */
	public abstract List<AuthGroupDTO> getAuthGroupList(PaginationRequest paginationRequest);
	
	/**
	 * 모든 권한 그룹 목록의 개수를 반환합니다.
	 * @return
	 */
	public abstract int getAuthGroupListTotalRows();
	
	/**
	 * 권한 그룹을 등록합니다.
	 * @param authGroupDTO
	 */
	public abstract void insertAuthGroup(AuthGroupDTO authGroupDTO);
	
	/**
	 * 특정 권한 그룹을 수정합니다.
	 * @param authGroupDTO
	 */
	public abstract void updateAuthGroupByAuthGroup(AuthGroupDTO authGroupDTO);
	
	/**
	 * 특정 권한 그룹을 제거합니다.
	 * @param authGroup	제거할 권한 그룹의 일련 번호
	 */
	public abstract void deleteAuthGroupByAuthGroup(AuthGroup authGroup);
}
