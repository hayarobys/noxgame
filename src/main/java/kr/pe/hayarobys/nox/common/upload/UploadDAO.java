package kr.pe.hayarobys.nox.common.upload;

import org.springframework.stereotype.Repository;

@Repository
public interface UploadDAO{
	/**
	 * 파일에 대한 정보를 DB에 입력합니다.
	 * @param fileVO
	 */
	public abstract void insertFile(FileVO fileVO);
}
