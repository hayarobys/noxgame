package kr.pe.hayarobys.nox.common.exception;

import org.springframework.http.HttpStatus;

public class GlobalException extends RuntimeException{
	private static final long serialVersionUID = -2905197001414273409L;
	
	protected HttpStatus httpStatus;
	
	public GlobalException(String message){
		super(message);
	}
	
	public HttpStatus getHttpStatus(){
		return httpStatus;
	}
}
