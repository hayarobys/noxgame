package kr.pe.hayarobys.nox.common.upload;

import java.io.File;

public interface UploadService{
	/**
	 * 특정 일련 번호의 파일 그룹이 있는지 조회하고, 없으
	 * @return
	 */
	public int ifTheFileGroupDoesNotExistCreateIt(int fileGrpSqPk);
	
	/**
	 * 파일 정보를 DB에 넣습니다.
	 * - 파일 그룹 생성
	 * - 파일 저장
	 * @param file
	 */
	public void insertFile(File file);
}
