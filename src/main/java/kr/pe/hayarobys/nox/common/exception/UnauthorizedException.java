package kr.pe.hayarobys.nox.common.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends GlobalException{

	private static final long serialVersionUID = 8126024027737944409L;

	public UnauthorizedException(String message){
		super(message);
		
		/*
		 * 401(인증되지 않은 요청)
		 */
		httpStatus = HttpStatus.UNAUTHORIZED;
	}

}
