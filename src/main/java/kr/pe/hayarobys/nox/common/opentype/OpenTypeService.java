package kr.pe.hayarobys.nox.common.opentype;

import java.util.List;

import com.suph.security.core.enums.OpenType;
import com.suph.security.core.userdetails.MemberInfo;


public interface OpenTypeService{
		
	/**
	 * 요청자가 요구 권한을 가지고 있거나, 화이트 리스트에 해당하는지 여부를 검사 합니다.
	 * @param memberInfo 요청자의 계정 정보(계정 번호, 보유 권한)
	 * @param openType 공개 범위
	 * @param whiteMemNoList 계정 번호 화이트 리스트
	 * @return 통과 조건을 만족할 경우 true. 불충족시 false.
	 */
	public abstract boolean canIView(
			MemberInfo memberInfo,
			OpenType openType,
			List<Integer> whiteMemNoList
	);
	
	/**
	 * 요청자가 요구 권한을 가지고 있거나, 화이트 리스트에 해당하는지 여부를 검사 합니다.
	 * @param memberInfo
	 * @param openType
	 * @param whiteMemNo
	 * @return
	 */
	public abstract boolean canIView(
			MemberInfo memberInfo,
			OpenType openType,
			Integer whiteMemNo
	);
}
