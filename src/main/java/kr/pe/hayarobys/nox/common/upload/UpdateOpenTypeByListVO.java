package kr.pe.hayarobys.nox.common.upload;

import java.util.List;

import com.suph.security.core.enums.OpenType;

/**
 * 번호 목록으로 권한을 일괄 수정하는 mybatis 쿼리에 전달하는 용도의 클래스 입니다.
 */
public class UpdateOpenTypeByListVO{
	private List<Integer> list;
	private OpenType openType;
	
	public List<Integer> getList(){
		return list;
	}
	
	public void setList(List<Integer> list){
		this.list = list;
	}

	public OpenType getOpenType(){
		return openType;
	}

	public void setOpenType(OpenType openType){
		this.openType = openType;
	}

	@Override
	public String toString(){
		return "UpdateOpenTypeByListVO [list=" + list + "]";
	}
}
