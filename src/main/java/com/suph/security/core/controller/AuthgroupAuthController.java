package com.suph.security.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthgroupAuthController{
	/**
	 * 권한그룹-권한 관리 페이지로 이동
	 */
	@RequestMapping(value="/authgroup-auth/edit", method=RequestMethod.GET)
	public String getAuthgroupAuthEdit(){
		return "authgroup-auth/authgroup-auth";
	}
}
