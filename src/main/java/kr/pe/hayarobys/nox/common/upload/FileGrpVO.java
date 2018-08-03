package kr.pe.hayarobys.nox.common.upload;
/**
 * FILE_GRP_TB 파일 묶음 테이블
 * 정보를 담기 위한 Value Object
 * @author hayarobys
 */
public class FileGrpVO{
	/** FILE_GRP_SQ_PK 파일 묶음 시퀀스 기본키 */
	private Integer fileGrpNo;
	/** MEM_FK 계정 외래키 */
	private Integer memNo;
	/** AUTH_GRP_FK 권한 묶음 외래키 */
	private Integer authGrpNo;
	
	public Integer getFileGrpNo(){
		return fileGrpNo;
	}
	
	public void setFileGrpNo(Integer fileGrpNo){
		this.fileGrpNo = fileGrpNo;
	}
	
	public Integer getMemNo(){
		return memNo;
	}
	
	public void setMemNo(Integer memNo){
		this.memNo = memNo;
	}
	
	public Integer getAuthGrpNo(){
		return authGrpNo;
	}
	
	public void setAuthGrpNo(Integer authGrpNo){
		this.authGrpNo = authGrpNo;
	}
}
