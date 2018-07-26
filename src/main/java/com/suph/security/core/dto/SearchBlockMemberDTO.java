package com.suph.security.core.dto;

import java.util.Date;
import java.util.List;

public class SearchBlockMemberDTO{
	
	/** 검색 유형 (아이디/닉네임/일련 번호) */
	private String searchType;
	/** 검색어  */
	private String searchKeyword;
	/** 시작일 필터 */
	private Date blockStartDt;
	/** 만료일 필터 */
	private Date blockExpireDt;
	/** 검색 시간대(과거/현재/미래) */
	private List<String> searchTime;
	
	public String getSearchType(){
		return searchType;
	}
	
	public void setSearchType(String searchType){
		this.searchType = searchType;
	}
	
	public String getSearchKeyword(){
		return searchKeyword;
	}
	
	public void setSearchKeyword(String searchKeyword){
		this.searchKeyword = searchKeyword;
	}
	
	public Date getBlockStartDt(){
		return blockStartDt;
	}
	
	public void setBlockStartDt(Date blockStartDt){
		this.blockStartDt = blockStartDt;
	}
	
	public Date getBlockExpireDt(){
		return blockExpireDt;
	}
	
	public void setBlockExpireDt(Date blockExpireDt){
		this.blockExpireDt = blockExpireDt;
	}
	
	public List<String> getSearchTime(){
		return searchTime;
	}
	
	public void setSearchTime(List<String> searchTime){
		this.searchTime = searchTime;
	}
	
	@Override
	public String toString(){
		return "SearchBlockMemberDTO [searchType=" + searchType + ", searchKeyword=" + searchKeyword + ", blockStartDt="
				+ blockStartDt + ", blockExpireDt=" + blockExpireDt + ", searchTime=" + searchTime + "]";
	}
}
