package kr.pe.hayarobys.nox.common.exception;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends GlobalException{
	public InternalServerErrorException(String message){
		super(message);
		/*
		 * 500(서버 에러): 어떠한 이유로 서버상 에러가 발생했다.
		 */
		httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
	}

	private static final long serialVersionUID = 5013724522074074642L;

}
