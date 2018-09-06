package kr.pe.hayarobys.nox.common.upload;

import java.util.List;

import com.suph.security.core.enums.OpenType;

public interface UploadService{
	/**
	 * 특정 파일 그룹 번호에 속한 모든 파일 목록을 반환합니다.
	 * @param fileGroupNo
	 */
	public abstract List<FileVO> selectFileByFileGroupNo(Integer fileGroupNo);
	
	/**
	 * 특정 파일 그룹 번호에 해당하는 파일 그룹의 공개 범위를 수정합니다.
	 * @param fileGroupNo
	 * @param openType
	 */
	public abstract void updateOpenTypeOfFileGroupByFileGroupNo(
			Integer fileGroupNo, OpenType openType
	);
	
	/**
	 * 이 파일 그룹 목록의 공개 범위를 일괄 수정 합니다.
	 * @param fileGroupNoList
	 * @param openType
	 */
	public abstract void updateOpenTypeOfFileGroupByFileGroupNoList(
			List<Integer> fileGroupNoList, OpenType openType
	);
	
	/**
	 * 특정 파일 그룹에 소속된 파일들의 임시 저장 플래그를 변경합니다.
	 * @param fileGroupNo
	 * @param tempFlag
	 */
	public abstract void updateTempFlagOfFileByFileGroupNo(Integer fileGroupNo, Boolean tempFlag);
	
	/**
	 * 특정 파일 그룹에 속한 모든 파일을 DB와 물리 경로에서 제거.
	 * @param fileGroupNo
	 * @param deleteTempOnly 물리 경로상에서 임시 파일만 제거할 것인지 여부. false면 모든 물리 파일 제거. true면 DB상에선 구분없이 지우고 물리경로에선 임시 파일만 제거
	 */
	public abstract void deleteFileByFileGroupNo(Integer fileGroupNo, Boolean deleteTempOnly);
		
	/**
	 * 파일 그룹 번호 목록으로 파일 그룹, 파일, 물리 파일을 모두 제거합니다.
	 * @param fileGroupNoList
	 */
	public abstract void deleteFileGroupByFileGroupNoList(List<Integer> fileGroupNoList);
	
	/**
	 * 특정 계정과 특정 공개 범위로 파일 그룹을 생성한 후, 그 일련 번호를 반환합니다.
	 * @param memNo
	 * @param openType
	 * @return 새롭게 생성한 파일 그룹의 일련 번호
	 */
	public abstract Integer insertFileGroup(Integer memNo, OpenType openType);
	
	/**
	 * 파일 목록을 저장합니다.
	 * @param fileList
	 */
	public abstract void insertFileList(List<FileVO> fileList);
	
	/**
	 * form 형식의 파일 업로드를 처리합니다.
	 * 먼저 DB에 파일 정보를 등록후 물리적 경로에 저장합니다.
	 * 대상 파일 그룹의 소유주와 요청자가 불일치 할 경우 ForbiddenException을 발생시킵니다.
	 * 대상의 파일 그룹이 미리 생성되어 있어야 합니다.
	 * @param fileVO
	 * @return
	 */
	public abstract String imageUploadSmartEditorByForm(SmartEditorImageFileVO fileVO);
	
	/**
	 * stream 형식의 파일 업로드를 처리합니다.
	 * 먼저 DB에 파일 정보를 등록후 물리적 경로에 저장합니다.
	 * 대상 파일 그룹의 소유주와 요청자가 불일치 할 경우 ForbiddenException을 발생시킵니다.
	 * 대상의 파일 그룹이 미리 생성되어 있어야 합니다.
	 * @param fileGroupNo
	 */
	public abstract void imageUploadSmartEditorByStream(Integer fileGroupNo);
	

}
