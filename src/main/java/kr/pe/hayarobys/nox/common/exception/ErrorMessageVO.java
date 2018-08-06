package kr.pe.hayarobys.nox.common.exception;

public class ErrorMessageVO{
	private String status;
	private String method;
	private String uri;
	private String exception;
	private String message;
	
	public ErrorMessageVO(){
		super();
	}
	
	public ErrorMessageVO(Exception e){
		super();
		this.exception = ErrorMessageVO.getMessage(e);
		this.message = e.getMessage();
	}
	
	private static String getMessage(final Throwable th){
		if(th == null){
			return "";
		}
		return th.getMessage();
	}

	public String getStatus(){
		return status;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getMethod(){
		return method;
	}

	public void setMethod(String method){
		this.method = method;
	}

	public String getUri(){
		return uri;
	}

	public void setUri(String uri){
		this.uri = uri;
	}

	public String getException(){
		return exception;
	}

	public void setException(String exception){
		this.exception = exception;
	}

	public String getMessage(){
		return message;
	}

	public void setMessage(String message){
		this.message = message;
	}

	@Override
	public String toString(){
		return "ErrorMessageVO [status=" + status + ", method=" + method + ", uri=" + uri + ", exception=" + exception + ", message=" + message + "]";
	}
}
