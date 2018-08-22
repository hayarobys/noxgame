package kr.pe.hayarobys.nox.common.upload;

import com.suph.security.core.enums.Authgroup;

/**
 * FILE_GRP_TB 파일 묶음 테이블
 * 정보를 담기 위한 Value Object
 * @author hayarobys
 */
public class FileGroupVO{
	/** FILE_GRP_SQ_PK 파일 묶음 시퀀스 기본키 */
	private Integer fileGroupNo;
	/** MEM_FK 계정 외래키 */
	private Integer memNo;
	/** AUTH_GRP_FK 권한 묶음 외래키(공개범위, 최소 조회 권한) */
	private Authgroup authgroup;
	
	public Integer getFileGroupNo(){
		return fileGroupNo;
	}
	
	public void setFileGroupNo(Integer fileGroupNo){
		this.fileGroupNo = fileGroupNo;
	}
	
	public Integer getMemNo(){
		return memNo;
	}
	
	public void setMemNo(Integer memNo){
		this.memNo = memNo;
	}

	public Authgroup getAuthgroup(){
		return authgroup;
	}

	public void setAuthgroup(Authgroup authgroup){
		this.authgroup = authgroup;
	}

	@Override
	public String toString(){
		return "FileGroupVO [fileGroupNo=" + fileGroupNo + ", memNo=" + memNo + ", authgroup=" + authgroup + "]";
	}
}
