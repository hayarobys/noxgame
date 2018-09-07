package com.suph.security.core.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suph.security.core.dto.AuthDTO;
import com.suph.security.core.dto.AuthGroupAuthDTO;
import com.suph.security.core.enums.AuthGroup;
import com.suph.security.core.service.AuthGroupAuthService;
import com.suph.security.core.service.AuthGroupService;

@Controller
public class AuthGroupAuthController{
	
	@Autowired
	private AuthGroupService authGroupService;
	
	@Autowired
	private AuthGroupAuthService authGroupAuthService;
	
	/**
	 * 권한그룹-권한 관리 페이지로 이동
	 */
	@RequestMapping(value="/auth-group-auth/edit", method=RequestMethod.GET)
	public String getAuthgroupAuthEdit(){
		return "auth-group-auth/auth-group-auth";
	}
	
	/**
	 * 특정 권한 그룹 접근에 필요한 권한 목록을 조회합니다.
	 * @param authgroup
	 * @return
	 */
	@RequestMapping(value="/auth-group/{authGroup}/auth", method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> authList(@PathVariable(required=true) AuthGroup authGroup){
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<AuthDTO> list = authGroupService.getAuthListByAuthGroup(authGroup);
		
		result.put("result", "success");
		result.put("list", list);
		
		return result;
	}
	
	/**
	 * 특정 권한 그룹 접근에 필요한 권한을 재지정 합니다.
	 * @return
	 */
	@RequestMapping(value="/auth-group/{authGroup}/auth", method=RequestMethod.PATCH)
	public @ResponseBody Map<String, Object> changeAuthGroupAuth(
			@PathVariable(required=true) AuthGroup authGroup,
			@RequestBody AuthGroupAuthDTO authGroupAuthDTO
	){
		authGroupAuthDTO.setAuthGroup(authGroup);
		return authGroupAuthService.changeAuthGroupAuth(authGroupAuthDTO);
	}
}
