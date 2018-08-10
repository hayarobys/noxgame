package kr.pe.hayarobys.nox.community.freeboard;

import org.springframework.stereotype.Repository;

import kr.pe.hayarobys.nox.common.comment.CommentGroupVO;
import kr.pe.hayarobys.nox.common.tempsave.TempSaveVO;
import kr.pe.hayarobys.nox.common.upload.FileGrpVO;

@Repository
public interface FreeboardDAO{
	/**
	 * 파일 그룹을 생성하고 그 일련번호를 전달받은 객체에 담습니다.
	 * @param fileGrpVO
	 */
	public abstract void insertFileGrp(FileGrpVO fileGrpVO);
	
	/**
	 * 게시글 임시 저장 레코드를 생성하고 그 일련번호를 전달받은 객체에 담습니다.
	 * @param tempSaveVO
	 */
	public abstract void insertTempSave(TempSaveVO tempSaveVO);
	
	/**
	 * 특정 계정이 FREEBOARD항목에서 마지막으로 저장한 임시 글 조회
	 * @param memNo
	 * @return
	 */
	public abstract TempSaveVO selectLastTempSaveNoFromFreeboard(Integer memNo);
	
	/**
	 * 특정 일련 번호의 임시 저장 글이 바라보고 있는 파일 그룹 일련 번호와 계정 일련 번호를 조회합니다.
	 * @param tempSaveNo
	 * @return
	 */
	public abstract TempSaveVO selectTempSaveByTempSaveNo(Integer tempSaveNo);
	
	/**
	 * 자유게시판 그룹을 등록하고 생성된 일련 번호를 조회 합니다.
	 * @param freeboardGroupVO
	 */
	public abstract void insertFreeboardGroup(FreeboardGroupVO freeboardGroupVO);
	
	/**
	 * 자유게시판 상세 정보를 등록합니다.
	 * @param freeboardVO
	 */
	public abstract void insertFreeboard(FreeboardVO freeboardVO);
	
	/**
	 * 특정 일련 번호의 게시글 임시 저장 레코드를 제거합니다.
	 * @param tempSaveNo
	 */
	public abstract void deleteTempSaveByTempSaveNo(Integer tempSaveNo);
	
	/**
	 * 게시글 상세 조회시 필요한 값들을 조회합니다.
	 * 특정 일련 번호의 게시글 그룹에 속한 마지막 게시글과 파일 그룹, 댓글 그룹, 계정 아이디, 계정 닉네임, 등록일 등의 정보를 조회합니다.
	 * @param freeboardGroupNo
	 */
	public abstract FreeboardDetailVO selectFreeboardDetail(Integer freeboardGroupNo);
}
