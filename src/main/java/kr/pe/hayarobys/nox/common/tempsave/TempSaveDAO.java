package kr.pe.hayarobys.nox.common.tempsave;

import org.springframework.stereotype.Repository;

@Repository
public interface TempSaveDAO{
	/**
	 * 특정 계정이 특정 카테고리에서 특정 용도로 마지막에 저장한 임시 글 조회
	 * @param memNo
	 * @return
	 */
	public abstract TempSaveVO selectLastTempSaveFromCategory(TempSaveVO tempSaveVO);
	
	/**
	 * 게시글 임시 저장 레코드를 생성하고 그 일련번호를 전달받은 객체에 담습니다.
	 * @param tempSaveVO
	 */
	public abstract void insertTempSave(TempSaveVO tempSaveVO);
	
	/**
	 * 특정 일련 번호의 임시 저장 글이 바라보고 있는 파일 그룹 일련 번호와 계정 일련 번호를 조회합니다.
	 * @param tempSaveNo
	 * @return
	 */
	public abstract TempSaveVO selectTempSaveByTempSaveNo(Integer tempSaveNo);
	
	/**
	 * 특정 일련 번호의 게시글 임시 저장 레코드를 제거합니다.
	 * @param tempSaveNo
	 */
	public abstract void deleteTempSaveByTempSaveNo(Integer tempSaveNo);
	
	/**
	 * 특정 계정이 특정 카테고리에 특정 용도로 저장한 모든 임시 저장 레코드를 제거합니다.
	 * @param tempSaveVO
	 */
	public abstract void deleteTempSave(TempSaveVO tempSaveVO);
}
