package com.suph.security.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.suph.security.core.dto.AuthDTO;
import com.suph.security.core.dto.MemberAuthDTO;

@Repository
public interface MemberAuthDAO{
	/**
	 * 해당 유저 일련 번호가 소유한 권한 목록을 반환합니다.
	 * @param memNo
	 * @return
	 */
	public abstract List<AuthDTO> selectAuthOfMemberByMemNo(Integer memNo);
	
	/**
	 * 특정 유저에게 권한들을 부여합니다.
	 * @param memberAuthDTO
	 */
	public abstract void insertAuthOfMemberByMemNo(MemberAuthDTO memberAuthDTO);
	
	/**
	 * 특정 유저가 보유한 권한들을 모두 제거합니다.
	 * @param memNo
	 */
	public abstract void deleteAuthOfMemberByMemNo(Integer memNo);
	
	/**
	 * 두 계정의 권한 우위를 비교하여, my>target은 1, my<target은 -1, my=target은 0을 반환합니다.
	 * 미구현상태. 리소스별 권한 우선순위 먼저 구현후 이어갈 계획 2019y03M05d
	 * @param myMemNo
	 * @param targetMemNo
	 * @return
	 */
	public abstract Integer comparePermissions(
			@Param("myMemNo") Integer myMemNo,
			@Param("targetMemNo") Integer targetMemNo
	);
}






