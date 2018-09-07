package com.suph.security.core.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.suph.security.core.dao.AuthGroupAuthDAO;
import com.suph.security.core.dto.AuthGroupAuthDTO;
import com.suph.security.core.service.AuthGroupAuthService;

@Service("authgroupAuthService")
public class AuthGroupAuthServiceImpl implements AuthGroupAuthService{
	//private static final Logger logger = LoggerFactory.getLogger(ResourceAuthServiceImpl.class);

	@Autowired
	private AuthGroupAuthDAO authGroupAuthDAO;
	
	@Override
	@Transactional
	public Map<String, Object> changeAuthGroupAuth(AuthGroupAuthDTO authGroupAuthDTO){
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		try{
			authGroupAuthDAO.deleteAuthListByAuthGroup(authGroupAuthDTO.getAuthGroup());
			if(		authGroupAuthDTO.getAuthSqPkList() != null
				&&	authGroupAuthDTO.getAuthSqPkList().size() > 0
			){
				authGroupAuthDAO.insertAuthListByAuthGroup(authGroupAuthDTO);
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


