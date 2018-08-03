package kr.pe.hayarobys.nox.community.freeboard;

import org.springframework.stereotype.Repository;

import kr.pe.hayarobys.nox.common.upload.FileGrpVO;
import kr.pe.hayarobys.nox.common.upload.TempSaveVO;

@Repository
public interface FreeboardDAO{
	// 파일 묶음 레코드 생성
	public abstract void insertFileGrp(FileGrpVO fileGrpVO);
	
	// 게시글 임시 저장 레코드 생성
	public abstract void insertTempSave(TempSaveVO tempSaveVO);
}
