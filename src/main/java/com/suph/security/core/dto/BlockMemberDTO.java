package com.suph.security.core.dto;

/**
 * TB_BLOCK_MEMBER 차단 계정 테이블의 정보를 옮기는 역할
 */
public class BlockMemberDTO{
	/**
	 * 실제 존재하는 컬럼이 아닌, SQL문으로 concat함수를 이용해 생성한 가상 컬럼 입니다.
	 * ex) tester/테스터/11 아이디/닉네임/계정일련번호
	 * dataField는 blockSqPk로, displayField는 memInfo로 출력하는데 쓰입니다.
	 */
	private String memInfo;
	
	/** 차단 일련 번호 */
	private Integer blockSqPk;
	/** 계정 일련 번호 */
	private Integer memFk;
	/** 차단 시작 일자 */
	private java.util.Date blockStartDt;
	/** 차단 만료 일자 */
	private java.util.Date blockExpireDt;
	/** 차단 사유 */
	private String blockCause;
	
	public String getMemInfo(){
		return memInfo;
	}
	
	public void setMemInfo(String memInfo){
		this.memInfo = memInfo;
	}
	
	public Integer getBlockSqPk(){
		return blockSqPk;
	}
	
	public void setBlockSqPk(Integer blockSqPk){
		this.blockSqPk = blockSqPk;
	}
	
	public Integer getMemFk(){
		return memFk;
	}
	
	public void setMemFk(Integer memFk){
		this.memFk = memFk;
	}
	
	public java.util.Date getBlockStartDt(){
		return blockStartDt;
	}
	
	public void setBlockStartDt(java.util.Date blockStartDt){
		this.blockStartDt = blockStartDt;
	}
	
	public java.util.Date getBlockExpireDt(){
		return blockExpireDt;
	}
	
	public void setBlockExpireDt(java.util.Date blockExpireDt){
		this.blockExpireDt = blockExpireDt;
	}
	
	public String getBlockCause(){
		return blockCause;
	}
	
	public void setBlockCause(String blockCause){
		this.blockCause = blockCause;
	}
	
	@Override
	public String toString(){
		return "BlockMemberDTO [memInfo=" + memInfo + ", blockSqPk=" + blockSqPk + ", memFk=" + memFk
				+ ", blockStartDt=" + blockStartDt + ", blockExpireDt=" + blockExpireDt + ", blockCause=" + blockCause
				+ "]";
	}
}


