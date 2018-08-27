package com.suph.security.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.suph.security.core.dto.AuthDTO;
import com.suph.security.core.dto.AuthgroupAuthDTO;
import com.suph.security.core.enums.Authgroup;

@Repository
public interface AuthgroupAuthDAO{
	/**
	 * 특정 권한 그룹 접근에 필요한 권한 목록을 조회합니다.
	 * @param 조회할 권한 그룹 번호
	 * @return
	 */
	public abstract List<AuthDTO> getAuthListByAuthgroup(Authgroup authgroup);
	
	/**
	 * 특정 AUTHGROUP에 AUTH 목록을 연결합니다.
	 * @param authgroupAuthDTO
	 */
	public abstract void insertAuthListByAuthgroup(AuthgroupAuthDTO authgroupAuthDTO);
	
	/**
	 * 특정 AUTHGROUP에 연결된 모든 AUTH를 제거합니다.
	 * @param authgroup
	 */
	public abstract void deleteAuthListByAuthgroup(Authgroup authgroup);
}
