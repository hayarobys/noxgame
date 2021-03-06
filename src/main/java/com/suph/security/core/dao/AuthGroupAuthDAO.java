package com.suph.security.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.suph.security.core.dto.AuthDTO;
import com.suph.security.core.dto.AuthGroupAuthDTO;
import com.suph.security.core.enums.AuthGroup;

@Repository
public interface AuthGroupAuthDAO{
	/**
	 * 특정 권한 그룹 접근에 필요한 권한 목록을 조회합니다.
	 * @param 조회할 권한 그룹 번호
	 * @return
	 */
	public abstract List<AuthDTO> getAuthListByAuthGroup(AuthGroup authGroup);
	
	/**
	 * 특정 AUTHGROUP에 AUTH 목록을 연결합니다.
	 * @param authGroupAuthDTO
	 */
	public abstract void insertAuthListByAuthGroup(AuthGroupAuthDTO authGroupAuthDTO);
	
	/**
	 * 특정 AUTHGROUP에 연결된 모든 AUTH를 제거합니다.
	 * @param authGroup
	 */
	public abstract void deleteAuthListByAuthGroup(AuthGroup authGroup);
}
