package com.suph.security.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;

import com.suph.security.core.dto.MemberAuthDTO;
import com.suph.security.core.enums.CompareResultEnum;

import kr.pe.hayarobys.nox.common.exception.InternalServerErrorException;

public interface MemberAuthService{
	public List<GrantedAuthority> loadUserAuthorities(Integer memNo);
	
	/**
	 * 특정 계정이 보유한 권한 목록 반환
	 * @param memNo 조회할 계정의 일련 번호
	 */
	public Map<String, Object> getAuthOfMemberByMemNo(Integer memNo);
	
	/**
	 * 툭정 계정이 보유한 권한 목록 업데이트
	 * @param memNo 수정할 계정의 일련 번호
	 */
	public Map<String, Object> patchAuthOfMemberByMemNo(Integer memNo, MemberAuthDTO memberAuthDTO);
	
	/**
	 * 유저 A가 유저 B보다 높은 권한을 가졌습니까?
	 * @param memNoA
	 * @param memNoB
	 * @return
	 * @throws InternalServerErrorException DB에서 유저 A와 유저 B의 권한 비교값이 null로 나왔을때 발생
	 */
	public abstract CompareResultEnum didAHaveHigherAuthorityThanB(Integer memNoA, Integer memNoB)
		throws InternalServerErrorException;
}
