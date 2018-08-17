package com.suph.security.core.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suph.security.core.dto.AuthgroupAuthDTO;
import com.suph.security.core.service.AuthgroupAuthService;
import com.suph.security.core.service.AuthgroupService;

@Controller
public class AuthgroupAuthController{
	
	@Autowired
	private AuthgroupService authgroupService;
	
	@Autowired
	private AuthgroupAuthService authgroupAuthService;
	
	/**
	 * 권한그룹-권한 관리 페이지로 이동
	 */
	@RequestMapping(value="/authgroup-auth/edit", method=RequestMethod.GET)
	public String getAuthgroupAuthEdit(){
		return "authgroup-auth/authgroup-auth";
	}
	
	/**
	 * 특정 권한 그룹 접근에 필요한 권한 목록을 조회합니다.
	 * @param authgroupNo
	 * @return
	 */
	@RequestMapping(value="/authgroup/{authgroupNo}/auth", method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> authList(@PathVariable(required=true) Integer authgroupNo){
		return authgroupService.getAuthListByAuthgroupNo(authgroupNo);
	}
	
	/**
	 * 특정 권한 그룹 접근에 필요한 권한을 재지정 합니다.
	 * @return
	 */
	@RequestMapping(value="/authgroup/{authgroupNo}/auth", method=RequestMethod.PATCH)
	public @ResponseBody Map<String, Object> changeAuthgroupAuth(
			@PathVariable(required=true) Integer authgroupNo,
			@RequestBody AuthgroupAuthDTO authgroupAuthDTO
	){
		authgroupAuthDTO.setAuthgroupNo(authgroupNo);
		return authgroupAuthService.changeAuthgroupAuth(authgroupAuthDTO);
	}
}
