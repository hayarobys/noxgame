package kr.pe.hayarobys.nox.common.upload;

import java.io.File;

public interface UploadService{
	
	/**
	 * 파일 정보를 물리 저장소와 DB에 넣습니다.
	 * - 파일 그룹 생성
	 * - 파일 
	 * @param file
	 */
	public void insertFile(File file);
}
