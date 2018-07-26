package com.suph.security.core.dto;

/**
 * JWT(security context)로부터 차단 계정 정보를 옮기는 데 사용하는 객체
 */
public class BlockInfoDTO{
	
	/** 차단 시작 일자 */
	private java.util.Date blockStartDt;
	/** 차단 만료 일자 */
	private java.util.Date blockExpireDt;
	/** 차단 사유 */
	private String blockCause;
	
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
		return "BlockInfoDTO [blockStartDt=" + blockStartDt + ", blockExpireDt=" + blockExpireDt + ", blockCause="
				+ blockCause + "]";
	}
}


