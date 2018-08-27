package com.suph.security.core.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.suph.security.core.dao.AuthgroupAuthDAO;
import com.suph.security.core.dao.AuthgroupDAO;
import com.suph.security.core.dto.AuthDTO;
import com.suph.security.core.dto.AuthgroupDTO;
import com.suph.security.core.dto.PaginationRequest;
import com.suph.security.core.dto.PaginationResponse;
import com.suph.security.core.enums.Authgroup;
import com.suph.security.core.service.AuthgroupService;

@Service("authgroupService")
public class AuthgroupServiceImpl implements AuthgroupService{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AuthgroupDAO authgroupDAO;
	
	@Autowired
	private AuthgroupAuthDAO authgroupAuthDAO;
	
	@Override
	public Map<String, Object> getAuthgroupList(PaginationRequest paginationRequest){
		Map<String, Object> result = new HashMap<String, Object>();
		
		try{
			List<AuthgroupDTO> list = authgroupDAO.getAuthgroupList(paginationRequest);
			int totalRows = authgroupDAO.getAuthgroupListTotalRows();
			
			PaginationResponse<AuthgroupDTO> data = new PaginationResponse<AuthgroupDTO>(list, totalRows);
			
			result.put("list", data);
			result.put("result", "success");
		}catch(DataAccessException sqle){
			sqle.printStackTrace();
			result.put("result", "fail");
		}
		
		return result;
	}
	
	@Override
	public Map<String, Object> getAuthListByAuthgroup(Authgroup authgroup){
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<AuthDTO> list = authgroupAuthDAO.getAuthListByAuthgroup(authgroup);
		
		result.put("result", "success");
		result.put("list", list);
		
		return result;
	}
	
	@Override
	public Map<String, Object> postAuthgroup(AuthgroupDTO authgroupDTO){
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		StringBuffer errorInfo = new StringBuffer();
		boolean isValid = true;
		if( authgroupDTO.getAuthgroup() == null ){
			errorInfo.append("\n권한 그룹 명이 없습니다.");
			isValid = false;
		}
		
		if( !StringUtils.hasText(authgroupDTO.getAuthgroupExplanation()) ){
			errorInfo.append("\n권한 그룹 설명이 없습니다.");
			isValid = false;
		}
		
		if(isValid == true){
			try{
				authgroupDAO.insertAuthgroup(authgroupDTO);
				returnMap.put("result", "success");
			}catch(DataAccessException sqle){
				sqle.printStackTrace();
				returnMap.put("result", "fail");
			}
		}else{
			returnMap.put("result", "fail");
		}
		
		returnMap.put("message", errorInfo.toString());
		return returnMap;
	}
	
	@Override
	public Map<String, Object> patchAuthgroupByAuthgroup(AuthgroupDTO authgroupDTO){
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		// 둘 다 공백 또는 null이 들어왔다면 fail 처리 합니다.
		if(		authgroupDTO.getAuthgroup() != null
			||	StringUtils.hasText(authgroupDTO.getAuthgroupExplanation())
		){
			try{
				authgroupDAO.updateAuthgroupByAuthgroup(authgroupDTO);
				returnMap.put("result", "success");
			}catch(DataAccessException dae){
				returnMap.put("result", "fail");
				returnMap.put("message", "수정에 실패했습니다. 데이터가 너무 길거나 잘못된 문자가 들어있진 않은지 확인 바랍니다.");
				dae.printStackTrace();
			}
		}else{
			returnMap.put("message", "서버로 수정 값이 전달되지 않았습니다.");
			returnMap.put("result", "fail");
		}
		
		return returnMap;
	}
	
	@Override
	public Map<String, Object> deleteAuthgroupByAuthgroup(Authgroup authgroup){
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		if(authgroup == null){
			returnMap.put("result", "fail");
			returnMap.put("message", "삭제할 권한 그룹으로 null값이 올 수 없습니다.");
		}else{
			StringBuffer errorInfo = new StringBuffer();
			
			try{
				authgroupDAO.deleteAuthgroupByAuthgroup(authgroup);
				returnMap.put("result", "success");
			}catch(DataIntegrityViolationException keyException){
				errorInfo.append("\nauthgroup: ");
				errorInfo.append(authgroup);
				errorInfo.append("\ncause: 제약조건에 위배되어 제거할 수 없습니다.");
				
				returnMap.put("message", errorInfo.toString());
				
				logger.debug("다음의 권한 제거중 오류가 발생했습니다.\n{}",errorInfo.toString());
				errorInfo.delete(0, errorInfo.length());
				
				keyException.printStackTrace();
			}catch(DataAccessException e){
				// 제거 실패한 권한 그룹의 정보를 따로 보관한다.
				errorInfo.append("\nauthgroup: ");
				errorInfo.append(authgroup);
				errorInfo.append("\ncause: 알 수 없는 이유로 제거에 실패했습니다.");
				
				returnMap.put("message", errorInfo.toString());
				
				logger.debug("다음의 권한 그룹 제거중 오류가 발생했습니다.\n{}",errorInfo.toString());
				errorInfo.delete(0, errorInfo.length());
				
				e.printStackTrace();
			}
		}
		
		return returnMap;
	}
}
