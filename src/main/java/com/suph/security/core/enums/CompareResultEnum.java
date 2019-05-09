package com.suph.security.core.enums;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 비교 결과 값 (1:A>B, 0:A=B, -1:A<B)
 */
public enum CompareResultEnum{
	/** 1, "A>B" */
	A_GREATER_THAN_B(1, "A>B"),
	/** 0, "A=B" */
	A_AND_B_IS_SAME(0, "A=B"),
	/** -1, "A<B" */
	A_RESS_THAN_B(-1, "A<B");
	
	private final Integer key;
	private final String value;
	
	private CompareResultEnum(Integer key, String value){
		this.key = key;
		this.value = value;
	}
	
	/**
	 * KEY를 반환합니다.
	 * @return
	 */
	@JsonValue
	public Integer getKey(){
		return key;
	}

	/**
	 * VALUE를 반환합니다.
	 * @return
	 */
	public String getValue(){
		return value;
	}
	
	/**
	 * 이 열거형에서 입력받은 key에 해당하는 요소를 찾습니다.
	 * 
	 * @param key
	 * @return
	 */
	public static CompareResultEnum getEnumByKey(Integer key){
		if(key == null){
			return null;
		}

		List<CompareResultEnum> list = Arrays.asList(CompareResultEnum.values());
		for(CompareResultEnum e : list){
			if(key.equals(e.getKey())){
				return e;
			}
		}
		return null;
	}

	/**
	 * 이 열거형에서 입력받은 key에 해당하는 value를 찾습니다.
	 * 
	 * @param key
	 * @return
	 */
	public static String getValueByKey(Integer key){
		CompareResultEnum enumElement = CompareResultEnum.getEnumByKey(key);
		if(enumElement == null){
			return null;
		}

		return enumElement.getValue();
	}
}
