package com.suph.security.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.suph.security.core.dto.AuthgroupDTO;
import com.suph.security.core.dto.PaginationRequest;
import com.suph.security.core.enums.Authgroup;

@Repository
public interface AuthgroupDAO{
	
	/**
	 * 모든 권한 그룹 목록을 페이징 처리하여 반환합니다.
	 * @param paginationRequest 페이징 검색조건
	 * @return
	 */
	public abstract List<AuthgroupDTO> getAuthgroupList(PaginationRequest paginationRequest);
	
	/**
	 * 모든 권한 그룹 목록의 개수를 반환합니다.
	 * @return
	 */
	public abstract int getAuthgroupListTotalRows();
	
	/**
	 * 권한 그룹을 등록합니다.
	 * @param authgroupDTO
	 */
	public abstract void insertAuthgroup(AuthgroupDTO authgroupDTO);
	
	/**
	 * 특정 권한 그룹을 수정합니다.
	 * @param authgroupDTO
	 */
	public abstract void updateAuthgroupByAuthgroup(AuthgroupDTO authgroupDTO);
	
	/**
	 * 특정 권한 그룹을 제거합니다.
	 * @param authgroup	제거할 권한 그룹의 일련 번호
	 */
	public abstract void deleteAuthgroupByAuthgroup(Authgroup authgroup);
}
