package com.suph.security.core.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suph.security.core.dto.AuthgroupDTO;
import com.suph.security.core.dto.PaginationRequest;
import com.suph.security.core.service.AuthgroupService;

@Controller
public class AuthgroupController{
	@Autowired
	private AuthgroupService authgroupService;
	
	/**
	 * 권한 그룹 관리 페이지로 이동
	 */
	@RequestMapping(value="/authgroup/edit", method=RequestMethod.GET)
	public String getAuthgroupEdit(){
		return "authgroup/authgroup";
	}

	/**
	 * 모든 권한 그룹 목록을 조회합니다.
	 * @return
	 */
	@RequestMapping(value="/authgroup", method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAuthgroup(
			@RequestParam(name="pagenum", required=false, defaultValue="0") int pagenum,
			@RequestParam(name="pagesize", required=false, defaultValue="20") int pagesize
	){
		PaginationRequest paginationRequest = new PaginationRequest();
		paginationRequest.setPagenum(pagenum);
		paginationRequest.setPagesize(pagesize);
		
		return authgroupService.getAuthgroupList(paginationRequest);
	}
	
	/**
	 * 권한그룹을 등록합니다.
	 * @return
	 */
	@RequestMapping(value="/authgroup", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> postAuthgroup(@RequestBody AuthgroupDTO authgroupDTO){
		return authgroupService.postAuthgroup(authgroupDTO);
	}
	
	/**
	 * 특정 권한 그룹을 수정합니다.
	 * @param authgroupNo
	 * @param authgroupDTO
	 * @return
	 */
	@RequestMapping(value="/authgroup/{authgroupNo}", method=RequestMethod.PATCH)
	public @ResponseBody Map<String, Object> patchAuthgroupByAuthgroupNo(
			@PathVariable(required=true) Integer authgroupNo,
			@RequestBody AuthgroupDTO authgroupDTO
	){
		return authgroupService.patchAuthgroupByAuthgroupNo(authgroupNo, authgroupDTO);
	}
	
	/**
	 * 요청받은 특정 권한 그룹을 삭제 합니다.
	 */
	@RequestMapping(value="/authgroup/{authgroupNo}", method=RequestMethod.DELETE)
	public @ResponseBody Map<String, Object> deleteAuthgroupByAuthgroupNo(@PathVariable(required=true) Integer authgroupNo){
		return authgroupService.deleteAuthgroupByAuthgroupNo(authgroupNo);
	}
}