package kr.pe.hayarobys.nox.common.upload;

import java.util.List;

import com.suph.security.core.enums.Authgroup;

/**
 * 번호 목록으로 권한을 일괄 수정하는 mybatis 쿼리에 전달하는 용도의 클래스 입니다.
 */
public class UpdateAuthgroupByListVO{
	private List<Integer> list;
	private Authgroup authgroup;
	
	public List<Integer> getList(){
		return list;
	}
	
	public void setList(List<Integer> list){
		this.list = list;
	}
	
	public Authgroup getAuthgroup(){
		return authgroup;
	}
	
	public void setAuthgroup(Authgroup authgroup){
		this.authgroup = authgroup;
	}
	
	@Override
	public String toString(){
		return "UpdateAuthgroupByListVO [list=" + list + ", authgroup=" + authgroup + "]";
	}
}
