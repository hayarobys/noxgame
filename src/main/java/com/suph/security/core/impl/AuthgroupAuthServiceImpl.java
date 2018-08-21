package com.suph.security.core.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.suph.security.core.dao.AuthgroupAuthDAO;
import com.suph.security.core.dto.AuthgroupAuthDTO;
import com.suph.security.core.service.AuthgroupAuthService;

@Service("authgroupAuthService")
public class AuthgroupAuthServiceImpl implements AuthgroupAuthService{
	//private static final Logger logger = LoggerFactory.getLogger(ResourceAuthServiceImpl.class);

	@Autowired
	private AuthgroupAuthDAO authgroupAuthDAO;
	
	@Override
	@Transactional
	public Map<String, Object> changeAuthgroupAuth(AuthgroupAuthDTO authgroupAuthDTO){
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		try{
			authgroupAuthDAO.deleteAuthListByAuthgroupNo(authgroupAuthDTO.getAuthgroup());
			if(		authgroupAuthDTO.getAuthSqPkList() != null
				&&	authgroupAuthDTO.getAuthSqPkList().size() > 0
			){
				authgroupAuthDAO.insertAuthListByAuthgroupNo(authgroupAuthDTO);
			}
			returnMap.put("result", "success");
		}catch(DataAccessException e){
			returnMap.put("result", "fail");
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		return returnMap;
	}

}


