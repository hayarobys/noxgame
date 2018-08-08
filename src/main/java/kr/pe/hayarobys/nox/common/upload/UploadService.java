package kr.pe.hayarobys.nox.common.upload;

public interface UploadService{
	
	/**
	 * 파일 정보를 DB에 넣습니다.
	 * - 파일 그룹 생성
	 * - 파일 
	 * @param fileVO
	 */
	public void insertFile(FileVO fileVO);
	
	// noneHTML5 이미지 저장
	public String imageUploadSmartEditorByForm(ImageFileVO fileVO);
	
	// ajax 이미지 저장
	public void imageUploadSmartEditorByStream(Integer fileGroupNo);
	
	// 파일을 저장합니다.
}
