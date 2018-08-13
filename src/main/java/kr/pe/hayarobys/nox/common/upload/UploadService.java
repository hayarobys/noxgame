package kr.pe.hayarobys.nox.common.upload;

public interface UploadService{
	
	/**
	 * form 형식의 파일 업로드를 처리합니다.
	 * 먼저 DB에 파일 정보를 등록후 물리적 경로에 저장합니다.
	 * 대상 파일 그룹의 소유주와 요청자가 불일치 할 경우 ForbiddenException을 발생시킵니다.
	 * 대상의 파일 그룹이 미리 생성되어 있어야 합니다.
	 * @param fileVO
	 * @return
	 */
	public String imageUploadSmartEditorByForm(ImageFileVO fileVO);
	
	/**
	 * stream 형식의 파일 업로드를 처리합니다.
	 * 먼저 DB에 파일 정보를 등록후 물리적 경로에 저장합니다.
	 * 대상 파일 그룹의 소유주와 요청자가 불일치 할 경우 ForbiddenException을 발생시킵니다.
	 * 대상의 파일 그룹이 미리 생성되어 있어야 합니다.
	 * @param fileGroupNo
	 */
	public void imageUploadSmartEditorByStream(Integer fileGroupNo);
	
}
