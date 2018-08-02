package com.suph.security.core.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
	
}
