package kr.pe.hayarobys.nox.community.freeboard;

import org.springframework.web.servlet.ModelAndView;

import com.suph.security.core.dto.JsonResultVO;

import kr.pe.hayarobys.nox.common.tempsave.TempSaveVO;

public interface FreeboardService{
	/***
	 * 임시 저장글 번호를 생성 후 반환 합니다.
	 * @param mav
	 * @return
	 */
	public abstract ModelAndView getTempSaveNo(ModelAndView mav);
	
	/**
	 * 신규 글쓰기 완료 요청 입니다. 요청받은 데이터를 DB상의 임시 저장 목록에서 제거 후, 자유게시판 상세 테이블에 등록합니다.
	 * @param tempSaveVO
	 * @return
	 */
	public abstract JsonResultVO<Integer> postWrite(TempSaveVO tempSaveVO);
}
