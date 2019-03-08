package kr.pe.hayarobys.nox.profile;

import java.util.Map;

public interface ProfileService{
	/**
	 * 특정 멤버의 프로파일을 조회합니다.
	 * @param memNo 조회할 계정의 계정번호를 입력합니다. null값이 들어올시 현재 세션의 계정번호를 사용합니다.
	 * @return 관련 정보가 담긴 Map 형식의 오브젝트를 반환합니다.
	 */
	public abstract Map<String, Object> getProfile(Integer memNo);
}
