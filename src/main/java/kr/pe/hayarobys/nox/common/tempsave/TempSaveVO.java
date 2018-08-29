package kr.pe.hayarobys.nox.common.tempsave;

import java.util.Date;

import com.suph.security.core.enums.Authgroup;
import com.suph.security.core.enums.TempSaveCategory;
import com.suph.security.core.enums.TempSaveUse;



public class TempSaveVO{
	/** TEMP_SAVE_SQ_PK 임시 저장 번호 */
	private Integer tempSaveNo;
	/** MEM_FK 임시 저장을 요청한 계정의 번호 */
	private Integer memNo;
	/** TEMP_SAVE_CATEGORY 어느 URL로부터의 임시 저장 요청인지 카테고리 번호로 관리 */
	private TempSaveCategory tempSaveCategory;
	/** TEMP_SAVE_USE TEMP_SAVE_CATEGORY에서도 어떤 용도의 임시 저장 글 인지 구분(WRITE, MODIFY) */
	private TempSaveUse tempSaveUse;
	/** FILE_GRP_FK 파일 묶음 번호 */
	private Integer fileGroupNo;
	/** TEMP_SAVE_TITLE 임시 저장 제목 */
	private String tempSaveTitle;
	/** TEMP_SAVE_BODY 임시 저장 본문 */
	private String tempSaveBody;
	/** TEMP_SAVE_MOD_DT 마지막 수정 일 */
	private Date tempSaveModDt;
	/** AUTH_GRP_FK 최소 조회 권한 (공개범위) */
	private Authgroup openType;
	/** CMT_GRP_NEW_WRT_FL 댓글 신규 작성 허용 여부 */
	private Boolean allowComment;
	
	public Integer getTempSaveNo(){
		return tempSaveNo;
	}
	
	public void setTempSaveNo(Integer tempSaveNo){
		this.tempSaveNo = tempSaveNo;
	}
	
	public Integer getMemNo(){
		return memNo;
	}
	
	public void setMemNo(Integer memNo){
		this.memNo = memNo;
	}
	
	public TempSaveCategory getTempSaveCategory(){
		return tempSaveCategory;
	}
	
	public void setTempSaveCategory(TempSaveCategory tempSaveCategory){
		this.tempSaveCategory = tempSaveCategory;
	}
	
	public TempSaveUse getTempSaveUse(){
		return tempSaveUse;
	}

	public void setTempSaveUse(TempSaveUse tempSaveUse){
		this.tempSaveUse = tempSaveUse;
	}
	
	public Integer getFileGroupNo(){
		return fileGroupNo;
	}
	
	public void setFileGroupNo(Integer fileGroupNo){
		this.fileGroupNo = fileGroupNo;
	}
	
	public String getTempSaveTitle(){
		return tempSaveTitle;
	}
	
	public void setTempSaveTitle(String tempSaveTitle){
		this.tempSaveTitle = tempSaveTitle;
	}
	
	public String getTempSaveBody(){
		return tempSaveBody;
	}
	
	public void setTempSaveBody(String tempSaveBody){
		this.tempSaveBody = tempSaveBody;
	}
	
	public Date getTempSaveModDt(){
		return tempSaveModDt;
	}
	
	public void setTempSaveModDt(Date tempSaveModDt){
		this.tempSaveModDt = tempSaveModDt;
	}
	
	public Authgroup getOpenType(){
		return openType;
	}

	public void setOpenType(Authgroup openType){
		this.openType = openType;
	}
	
	public Boolean getAllowComment(){
		return allowComment;
	}

	public void setAllowComment(Boolean allowComment){
		this.allowComment = allowComment;
	}

	@Override
	public String toString(){
		return "TempSaveVO [tempSaveNo=" + tempSaveNo + ", memNo=" + memNo + ", tempSaveCategory=" + tempSaveCategory + ", tempSaveUse=" + tempSaveUse
				+ ", fileGroupNo=" + fileGroupNo + ", tempSaveTitle=" + tempSaveTitle + ", tempSaveBody=" + tempSaveBody + ", tempSaveModDt="
				+ tempSaveModDt + ", openType=" + openType + ", allowComment=" + allowComment + "]";
	}
}
