package kr.pe.hayarobys.nox.common.tempsave;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suph.security.core.enums.TempSaveCategory;
import com.suph.security.core.enums.TempSaveUse;

import kr.pe.hayarobys.nox.common.upload.UploadService;

@Service
public class TempSaveServiceImpl implements TempSaveService{
	@Autowired
	private TempSaveDAO tempSaveDAO;
	
	@Autowired
	private UploadService uploadService;
	
	@Override
	public TempSaveVO selectLastTempSaveFromMemNoAndCategoryAndUse(Integer memNo, TempSaveCategory tempSaveCategory, TempSaveUse tempSaveUse){
		// TODO: 정합성 검사
		TempSaveVO searchTempSaveVO = new TempSaveVO();
		searchTempSaveVO.setMemNo(memNo);
		searchTempSaveVO.setTempSaveCategory(tempSaveCategory);
		searchTempSaveVO.setTempSaveUse(tempSaveUse);
		return tempSaveDAO.selectLastTempSaveFromCategory(searchTempSaveVO);
	}
	
	@Override
	public TempSaveVO selectTempSaveByTempSaveNo(Integer tempSaveNo){
		// TODO: 정합성 검사
		return tempSaveDAO.selectTempSaveByTempSaveNo(tempSaveNo);
	}
	
	@Override
	public void insertTempSave(TempSaveVO tempSaveVO){
		// TODO: 정합성 검사
		tempSaveDAO.insertTempSave(tempSaveVO);
	}
	
	@Override
	public void deleteTempSaveByMemNoAndCategoryAndUse(Integer memNo, TempSaveCategory tempSaveCategory, TempSaveUse tempSaveUse){
		// TODO: 정합성 검사
		
		// 원본 임시 저장 정보 조회
		TempSaveVO tempSaveVO = selectLastTempSaveFromMemNoAndCategoryAndUse(memNo, tempSaveCategory, tempSaveUse);
		
		// 물리 경로에서 임시 파일만 제거, DB에서 특정 파일 그룹의 파일 전부 제거, DB에서 파일 그룹 제거
		uploadService.deleteFileByFileGroupNo(tempSaveVO.getFileGroupNo(), true);
		
		// DB상에서 임시 저장 제거
		deleteTempSaveByTempSaveNo(tempSaveVO.getTempSaveNo());
	}

	@Override
	public void deleteTempSaveByTempSaveNo(Integer tempSaveNo){
		// TODO: 정합성 검사
		tempSaveDAO.deleteTempSaveByTempSaveNo(tempSaveNo);
	}


}
