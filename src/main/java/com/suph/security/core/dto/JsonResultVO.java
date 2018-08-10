/**
 * 웹 요청에 대한 결과값을 json형식으로 반환하는데 사용합니다. 제네릭에 유의하십시오.
 */
package com.suph.security.core.dto;

public class JsonResultVO<T>{
	/** 응답 메시지(success, fail, 0, 1, 2, ...) */
	private T data;
	
	public JsonResultVO(T data){
		this.data = data;
	}

	public T getData(){
		return data;
	}

	public void setData(T data){
		this.data = data;
	}
}
