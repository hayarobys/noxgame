package com.suph.security.core.service;

import java.util.Map;

import com.suph.security.core.dto.AuthGroupAuthDTO;

public interface AuthGroupAuthService{
	/**
	 * 특정 권한 그룹 접근에 필요한 AUTH 목록을 변경합니다.
	 * @param resourceAuthDTO
	 * @return
	 */
	public abstract Map<String, Object> changeAuthGroupAuth(AuthGroupAuthDTO authGroupAuthDTO);
	
}
