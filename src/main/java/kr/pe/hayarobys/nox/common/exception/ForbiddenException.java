package kr.pe.hayarobys.nox.common.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends GlobalException{
	private static final long serialVersionUID = -4424649889746008100L;

	public ForbiddenException(String message){
		super(message);
		
		/*
		 * 401(권한 없음): 이 요청은 인증이 필요하다.
		 * 서버는 로그인이 필요한 페이지에 대해 이 요청을 제공할 수 있다.
		 * 상태 코드 이름이 권한 없음(Unauthorized)으로 되어 있지만
		 * 실제 뜻은 인증 안됨(Unauthenticated)에 더 가깝다.
		 */
		httpStatus = HttpStatus.FORBIDDEN;
	}

}
