package kr.pe.hayarobys.nox.common.upload;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface UploadDAO{
	/**
	 * 파일 그룹을 생성하고 그 일련 번호를 전달받은 객체에 담습니다.
	 * @param fileGrpVO
	 */
	public abstract void insertFileGroup(FileGroupVO fileGroupVO);
	
	/**
	 * 하나의 파일 정보를 DB에 입력하고 생성된 파일 번호를 반환 합니다.
	 * @param fileVO
	 */
	public abstract void insertFile(FileVO fileVO);
	
	/**
	 * 파일 목록을 DB에 입력합니다.
	 * @param fileVO
	 */
	public abstract void insertFileList(List<FileVO> fileVO);
	
	/**
	 * 특정 파일 그룹에 속한 파일들의 목록 조회
	 * @param fileGroupNo
	 * @return
	 */
	public abstract List<FileVO> selectFileByFileGroupNo(Integer fileGroupNo);
	
	/**
	 * 특정 파일 그룹을 등록한 계정의 일련 번호 조회
	 * @param fileGroupNo
	 * @return
	 */
	public abstract Integer selectMemNoOfFileGroupByFileGroupNo(Integer fileGroupNo);
	
	/**
	 * 특정 파일 그룹의 조회 권한(공개 범위)을 수정합니다.
	 * @param fileGroupVO
	 */
	public abstract void updateAuthgroupOfFileGroupByFileGroupNo(FileGroupVO fileGroupVO);
	
	/**
	 * 특정 파일 그룹에 속한 모든 파일을 DB상에서 제거합니다.
	 * @param fileGroupNo
	 */
	public abstract void deleteFileByFileGroupNo(Integer fileGroupNo);
	
	/**
	 * 특정 파일 그룹을 DB상에서 제거합니다.
	 * @param fileGroupNo
	 */
	public abstract void deleteFileGroupByFileGroupNo(Integer fileGroupNo);
	
	
	
}
