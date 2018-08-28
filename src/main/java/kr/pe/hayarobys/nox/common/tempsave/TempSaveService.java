package kr.pe.hayarobys.nox.common.tempsave;

import com.suph.security.core.enums.TempSaveCategory;
import com.suph.security.core.enums.TempSaveUse;

public interface TempSaveService{
	/**
	 * 특정 카테고리에 특정 용도로 생성된 특정 요청자의 임시 저장글이 있는지 확인합니다.
	 * @param memNo 검색할 계정 번호
	 * @param tempSaveCategory 임시 저장 테이블에서 검색할 카테고리
	 * @param tempSaveUse 임시 저장 테이블에서 검색할 용도
	 * @return 첫번째로 발견된 임시 저장 글 한 개 반환
	 */
	public abstract TempSaveVO selectLastTempSaveFromMemNoAndCategoryAndUse(
			Integer memNo,
			TempSaveCategory tempSaveCategory,
			TempSaveUse tempSaveUse
	);
	
	/**
	 * 특정 임시 저장 번호의 정보 조회
	 * @param tempSaveNo
	 * @return
	 */
	public abstract TempSaveVO selectTempSaveByTempSaveNo(Integer tempSaveNo);
	
	/**
	 * 특정 임시 저장 글에 연결된 파일 그룹 번호 조회
	 * @param tempSaveNo
	 * @return
	 */
	public abstract Integer selectFileGroupNoFromTempSaveByTempSaveNo(Integer tempSaveNo);
	
	/**
	 * 임시 저장글을 등록하고, 새롭게 생성한 임시 저장 일련 번호를 매개변수로 넘어온 객체에 담습니다.
	 * @param tempSaveVO
	 */
	public abstract void insertTempSave(TempSaveVO tempSaveVO);
	
	/**
	 * 특정 계정이 특정 카테고리에 특정 용도로 저장한 모든 임시 저장 레코드를 제거합니다. 파일 그룹과 파일을 모두 제거하고, 임시 플래그가 true인 물리 파일까지 제거합니다.
	 * @param memNo
	 * @param tempSaveCategory
	 * @param tempSaveUse
	 */
	public abstract void deleteTempSaveByMemNoAndCategoryAndUse(Integer memNo, TempSaveCategory tempSaveCategory, TempSaveUse tempSaveUse);
	
	/**
	 * 특정 임시 저장 번호의 데이터를 제거합니다.  단, 연결된 파일그룹은 그대로 유지합니다.
	 * @param tempSaveNo
	 * @return
	 */
	public abstract void deleteTempSaveByTempSaveNo(Integer tempSaveNo);
}
