package kr.pe.hayarobys.nox.common.tempsave;

import org.springframework.beans.factory.annotation.Autowired;

import com.suph.security.core.enums.TempSaveCategory;
import com.suph.security.core.enums.TempSaveUse;

public class TempSaveServiceImpl implements TempSaveService{
	@Autowired
	private TempSaveDAO tempSaveDAO;
	
	@Override
	public void deleteTempSave(Integer memNo, TempSaveCategory tempSaveCategory, TempSaveUse tempSaveUse){
		
		TempSaveVO deleteCondition = new TempSaveVO(); // 조건
		deleteCondition.setMemNo(memNo);
		deleteCondition.setTempSaveCategory(TempSaveCategory.FREEBOARD);
		deleteCondition.setTempSaveUse(TempSaveUse.MODIFY);
		
		
		
		
		
		
		// 파일 그룹을 순회하며 연결된 파일들 제거. 물리 파일은 유지?
		
		// 임시 저장 제거
		tempSaveDAO.deleteTempSave(deleteCondition);
		
		
	}

}
