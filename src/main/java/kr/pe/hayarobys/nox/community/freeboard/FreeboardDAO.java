package kr.pe.hayarobys.nox.community.freeboard;

import org.springframework.stereotype.Repository;

import kr.pe.hayarobys.nox.common.tempsave.TempSaveVO;
import kr.pe.hayarobys.nox.common.upload.FileGrpVO;

@Repository
public interface FreeboardDAO{
	// 파일 묶음 레코드 생성
	public abstract void insertFileGrp(FileGrpVO fileGrpVO);
	
	// 게시글 임시 저장 레코드 생성
	public abstract void insertTempSave(TempSaveVO tempSaveVO);
	
	/**
	 * 특정 계정이 FREEBOARD항목에서 마지막으로 저장한 임시 글 조회
	 * @param memNo
	 * @return
	 */
	public abstract TempSaveVO selectLastTempSaveNoFromFreeboard(Integer memNo);
}
