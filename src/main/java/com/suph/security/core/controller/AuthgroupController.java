package com.suph.security.core.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suph.security.core.dto.AuthGroupDTO;
import com.suph.security.core.dto.PaginationRequest;
import com.suph.security.core.enums.AuthGroup;
import com.suph.security.core.service.AuthGroupService;


@Controller
public class AuthGroupController{
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AuthGroupService authGroupService;
	
	/**
	 * 권한 그룹 관리 페이지로 이동
	 */
	@RequestMapping(value="/auth-group/edit", method=RequestMethod.GET)
	public String getAuthGroupEdit(){
		return "auth-group/auth-group";
	}

	/**
	 * 모든 권한 그룹 목록을 조회합니다.
	 * @return
	 */
	@RequestMapping(value="/auth-group", method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAuthGroup(
			@RequestParam(name="pagenum", required=false, defaultValue="0") int pagenum,
			@RequestParam(name="pagesize", required=false, defaultValue="20") int pagesize
	){
		PaginationRequest paginationRequest = new PaginationRequest();
		paginationRequest.setPagenum(pagenum);
		paginationRequest.setPagesize(pagesize);
		
		return authGroupService.getAuthGroupList(paginationRequest);
	}
	
	/**
	 * 권한그룹을 등록합니다.
	 * @return
	 */
	/*	
	@RequestMapping(value="/auth-group", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> postAuthGroup(@RequestBody AuthGroupDTO authGroupDTO){
		return authGroupService.postAuthGroup(authGroupDTO);
	}
	*/
	/**
	 * 특정 권한 그룹을 수정합니다.
	 * @param authGroup
	 * @param authGroupExplanation
	 * @return
	 */
	@RequestMapping(value="/auth-group/{authGroup}", method=RequestMethod.PATCH)
	public @ResponseBody Map<String, Object> patchAuthGroupByAuthgroup(
			@PathVariable(required=true) AuthGroup authGroup,
			@RequestBody AuthGroupDTO authGroupDTO
	){
		authGroupDTO.setAuthGroup(authGroup);
		logger.debug("전달받은 문자열: {}", authGroupDTO);
		return authGroupService.patchAuthGroupByAuthGroup(authGroupDTO);
	}
	
	/**
	 * 요청받은 특정 권한 그룹을 삭제 합니다.
	 */
	/*
	@RequestMapping(value="/auth-group/{authGroup}", method=RequestMethod.DELETE)
	public @ResponseBody Map<String, Object> deleteAuthGroupByAuthgroupNo(@PathVariable(required=true) AuthGroup authGroup){
		return authGroupService.deleteAuthGroupByAuthGroupNo(authGroup);
	}
	*/
}
