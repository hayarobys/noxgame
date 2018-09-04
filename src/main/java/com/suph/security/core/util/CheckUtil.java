package com.suph.security.core.util;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.suph.security.core.enums.Authgroup;
import com.suph.security.core.userdetails.MemberInfo;

public class CheckUtil{
	public static boolean canIView(
			MemberInfo memberInfo,
			Authgroup authgroup,
			List<Integer> memNoList
	){
		for(GrantedAuthority authrority : memberInfo.getAuthorities()){
			String myAuth = authrority.getAuthority();
			
			if(Authgroup.PUBLIC.equals(authgroup)){
				// 권한 검사X, 통과
			}else if(Authgroup.MEMBER.equals(authgroup)){
				// 요청자가 ROLE_USER, ROLE_MANAGER, ROLE_ADMIN 중 하나에 해당하는지 확인
				// 또는 요청자가 memNoList 목록에 존재하는지 확인 <- 무조건 위 셋 중 하나에 걸리기에 확인 할 필요가 있나 싶다.
			}else if(Authgroup.SECRET.equals(authgroup)){
				// 요청자가 memNoList 목록에 존재하는지 확인
			}else if(Authgroup.CLOSE.equals(authgroup)){
				// 요청자가 ROLE_ADMIN, ROLE_MANAGER 중 하나에 해당하는지 확인
			}
		}
		
		return false;
	}
}
