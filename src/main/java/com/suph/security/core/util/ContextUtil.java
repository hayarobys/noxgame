package com.suph.security.core.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.suph.security.core.userdetails.MemberInfo;

public class ContextUtil{
	/**
     * HttpServletReqeust 객체를 직접 얻습니다.
     * @return
     */
	public static HttpServletRequest getCurrentRequest(){
		ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		return attributes.getRequest();
	}
	
	/**
	 * HttpServletResponse 객체를 직접 얻습니다.
	 * @return
	 */
	public static HttpServletResponse getCurrentResponse() {
		ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		return attributes.getResponse();
	}
	
	/**
	 * 현재 처리중인 계정의 정보를 가져옵니다.
	 * 로그인 유저의 요청일 경우만 인증 정보를 반환할 수 있습니다. 미로그인 유저의 경우 null값을 반환하게 됩니다.
	 * @return
	 */
	public static MemberInfo getMemberInfo(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null){ return null; }
		
		MemberInfo memberInfo = null;
		Object principal = authentication.getPrincipal();
		if(principal instanceof MemberInfo){
			memberInfo = (MemberInfo)principal;
		}
		return memberInfo;
	}
}
