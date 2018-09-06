package kr.pe.hayarobys.nox.common.opentype;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.suph.security.core.dto.AuthDTO;
import com.suph.security.core.enums.AuthGroup;
import com.suph.security.core.enums.OpenType;
import com.suph.security.core.service.AuthGroupService;
import com.suph.security.core.userdetails.MemberInfo;


@Service
public class OpenTypeServiceImpl implements OpenTypeService{
	
	@Autowired
	private AuthGroupService authGroupService;
	
	@Override
	public boolean canIView(
			MemberInfo memberInfo,
			OpenType openType,
			List<Integer> memNoList
	){
		if(OpenType.PUBLIC.equals(openType)){
		// 권한 검사X, 통과
			return true;
			
		}else if(OpenType.MEMBER.equals(openType)){
		// 요청자가 AuthGroup.MEMBER에 해당하는지 확인
		// 또는 요청자가 memNoList 목록에 존재하는지 확인 <- 무조건 위 셋 중 하나에 걸리기에 확인 할 필요가 있나 싶다.
			List<AuthDTO> authList = authGroupService.getAuthListByAuthGroup(AuthGroup.MEMBER);
			
			for(GrantedAuthority authority : memberInfo.getAuthorities()){
				for(AuthDTO dto : authList){
					if(dto.getAuthNmUnq().equals(authority.toString())){
						return true;
					}
				}
			}
			
			return isThisMemNoListed(memberInfo.getNo(), memNoList);
			
		}else if(OpenType.SECRET.equals(openType)){
		// 요청자가 AuthGroup.STSTEM_OPERATOR에 해당하는지 확인
		// 요청자가 memNoList 목록에 존재하는지 확인
			List<AuthDTO> authList = authGroupService.getAuthListByAuthGroup(AuthGroup.SYSTEM_OPERATOR);
			
			for(GrantedAuthority authority : memberInfo.getAuthorities()){
				for(AuthDTO dto : authList){
					if(dto.getAuthNmUnq().equals(authority.toString())){
						return true;
					}
				}
			}
			return isThisMemNoListed(memberInfo.getNo(), memNoList);
			
		}else if(OpenType.CLOSE.equals(openType)){
		// 요청자가 AuthGroup.SYSTEM_OPERATOR에 해당하는지 확인
			List<AuthDTO> authList = authGroupService.getAuthListByAuthGroup(AuthGroup.SYSTEM_OPERATOR);
			
			for(GrantedAuthority authority : memberInfo.getAuthorities()){
				for(AuthDTO dto : authList){
					if(dto.getAuthNmUnq().equals(authority.toString())){
						return true;
					}
				}
			}
			
		}
		return false;
	}
	
	@Override
	public boolean canIView(MemberInfo memberInfo, OpenType openType, Integer whiteMemNo){
		return canIView(memberInfo, openType, (whiteMemNo == null) ? null : new ArrayList<Integer>(whiteMemNo));
	}
	
	/**
	 * 특정 번호가 목록에 존재하는지 확인 합니다.
	 * @param memNo
	 * @param memNoList
	 * @return
	 */
	private boolean isThisMemNoListed(Integer memNo, List<Integer> memNoList){
		if(memNoList == null){
			return false;
		}
		
		for(Integer no : memNoList){
			if(no.equals(memNo)){
				return true;
			}
		}
		return false;
	}
}
