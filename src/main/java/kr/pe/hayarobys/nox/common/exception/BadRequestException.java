package kr.pe.hayarobys.nox.common.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends GlobalException{

	private static final long serialVersionUID = 8126024027737944409L;

	public BadRequestException(String message){
		super(message);
		
		/*
		 * 400(잘못된 요청): 클라이언트의 요청 구문이 잘못됨.
		 */
		httpStatus = HttpStatus.BAD_REQUEST;
	}

}
