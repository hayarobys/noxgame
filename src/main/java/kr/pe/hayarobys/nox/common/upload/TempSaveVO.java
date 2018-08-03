package kr.pe.hayarobys.nox.common.upload;

import com.suph.security.core.enums.TempSaveCategory;

public class TempSaveVO{
	/** TEMP_SAVE_SQ_PK 임시 저장 번호 */
	private Integer temoSaveNo;
	/** MEM_FK 임시 저장을 요청한 계정의 번호 */
	private Integer memNo;
	/** TEMP_SAVE_CATEGORY 어느 URL로부터의 임시 저장 요청인지 카테고리 번호로 관리 */
	private TempSaveCategory tempSaveCategory;
	/** FILE_GRP_FK 파일 묶음 번호 */
	private Integer fileGrpNo;
	/** TEMP_SAVE_TITLE 임시 저장 제목 */
	private Integer tempSaveTitle;
	/** TEMP_SAVE_BODY 임시 저장 본문 */
	private Integer tempSaveBody;
	
	public Integer getTemoSaveNo(){
		return temoSaveNo;
	}
	
	public void setTemoSaveNo(Integer temoSaveNo){
		this.temoSaveNo = temoSaveNo;
	}
	
	public Integer getMemNo(){
		return memNo;
	}
	
	public void setMemNo(Integer memNo){
		this.memNo = memNo;
	}
	
	public TempSaveCategory getTempSaveCategorization(){
		return tempSaveCategory;
	}

	public void setTempSaveCategorization(TempSaveCategory tempSaveCategorization){
		this.tempSaveCategory = tempSaveCategorization;
	}

	public Integer getFileGrpNo(){
		return fileGrpNo;
	}
	
	public void setFileGrpNo(Integer fileGrpNo){
		this.fileGrpNo = fileGrpNo;
	}
	
	public Integer getTempSaveTitle(){
		return tempSaveTitle;
	}
	
	public void setTempSaveTitle(Integer tempSaveTitle){
		this.tempSaveTitle = tempSaveTitle;
	}
	
	public Integer getTempSaveBody(){
		return tempSaveBody;
	}
	
	public void setTempSaveBody(Integer tempSaveBody){
		this.tempSaveBody = tempSaveBody;
	}

	@Override
	public String toString(){
		return "TempSaveVO [temoSaveNo=" + temoSaveNo + ", memNo=" + memNo + ", tempSaveCategory="
				+ tempSaveCategory + ", fileGrpNo=" + fileGrpNo + ", tempSaveTitle=" + tempSaveTitle
				+ ", tempSaveBody=" + tempSaveBody + "]";
	}
}
