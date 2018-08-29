package kr.pe.hayarobys.nox.community.freeboard;

import org.springframework.web.servlet.ModelAndView;

import com.suph.security.core.dto.JsonResultVO;
import com.suph.security.core.enums.Authgroup;

import kr.pe.hayarobys.nox.common.tempsave.TempSaveVO;

public interface FreeboardService{
	/***
	 * 임시 저장글 번호를 생성 후 반환 합니다.
	 * @param mav
	 * @return
	 */
	public abstract ModelAndView getFreeboardTempSaveNo(ModelAndView mav);
	
	/**
	 * 신규 글쓰기 완료 요청 입니다. 요청받은 데이터를 DB상의 임시 저장 목록에서 제거 후, 자유게시판 상세 테이블에 등록합니다.
	 * @param tempSaveVO
	 * @return
	 */
	public abstract JsonResultVO<Integer> postWrite(TempSaveVO tempSaveVO);
	
	/**
	 * 특정 계정이 자유 게시판에서 진행중인 모든 신규 글 작성을 취소합니다.
	 * @param memNo
	 */
	public abstract void freeboardWriteCancel(Integer memNo);
	
	/**
	 * 게시글 상세 정보를 조회합니다.
	 * @param freeboardGroupNo
	 * @param mav
	 * @return
	 */
	public abstract ModelAndView getDetail(Integer freeboardGroupNo, ModelAndView mav);
	
	/**
	 * 게시글 수정 폼을 반환합니다.
	 * @param freeboardGroupNo
	 * @param mav
	 * @return
	 */
	public abstract ModelAndView getModifyForm(Integer freeboardGroupNo, ModelAndView mav);
	
	/**
	 * 특정 게시글 그룹에 수정 내용을 추가합니다.
	 */
	public abstract void patchFreeboard(Integer freeboardGroupNo, TempSaveVO tempSaveVO);
	
	/**
	 * 특정 자유게시판 그룹의 최소 조회 권한을 변경합니다.
	 * @param freeboardGroupNo
	 * @param authgroup
	 */
	public abstract void updateAuthgroupOfFreeboardGroupByFreeboardGroupNo(Integer freeboardGroupNo, Authgroup authgroup);
	
	/**
	 * 특정 계정이 자유 게시판에서 진행중인 모든 수정을 취소합니다.
	 * @param memNo
	 */
	public abstract void freeboardModifyCancel(Integer memNo);
	
	/**
	 * 자유게시판 그룹을 제거합니다.
	 * @param freeboardGroupNo
	 */
	public abstract void deleteFreeboardGroup(Integer freeboardGroupNo);
	
}
