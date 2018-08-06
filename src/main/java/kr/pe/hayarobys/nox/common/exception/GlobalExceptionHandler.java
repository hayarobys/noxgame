package kr.pe.hayarobys.nox.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	/**
	 * 서버에서 발생하는 모든 GlobalException 유형의 Runtime예외를 가로채 json Message 형식으로 반환합니다.
	 * @param request
	 * @param res
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ErrorMessageVO handleException(HttpServletRequest request, HttpServletResponse res, GlobalException e){
		
		LOGGER.error("\n[GlobalExceptionHandler]", e);
		
		HttpStatus status = e.getHttpStatus() == null ? HttpStatus.INTERNAL_SERVER_ERROR : e.getHttpStatus();
		res.setStatus(status.value());
		
		ErrorMessageVO errorMessageVO = new ErrorMessageVO(e);
		errorMessageVO.setStatus(status.toString());
		errorMessageVO.setMethod(request.getMethod());
		errorMessageVO.setUri(request.getRequestURI());
		return errorMessageVO;
	}
}
