package kr.pe.hayarobys.nox.common.tempsave;

import com.suph.security.core.enums.TempSaveCategory;
import com.suph.security.core.enums.TempSaveUse;

public interface TempSaveService{
	/**
	 * 특정 계정이 특정 카테고리에 특정 용도로 저장한 모든 임시 저장 레코드를 제거합니다.
	 * @param memNo
	 * @param tempSaveCategory
	 * @param tempSaveUse
	 */
	public abstract void deleteTempSave(Integer memNo, TempSaveCategory tempSaveCategory, TempSaveUse tempSaveUse);
}
