package kr.pe.hayarobys.nox.community.freeboard;

import org.springframework.web.servlet.ModelAndView;

public interface FreeboardService{
	/***
	 * 임시 저장글 번호를 생성 후 반환 합니다.
	 * @param mav
	 * @return
	 */
	public abstract ModelAndView getTempSaveNo(ModelAndView mav);
}
