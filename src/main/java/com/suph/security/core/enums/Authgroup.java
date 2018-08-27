package com.suph.security.core.enums;

public enum Authgroup{
	/*
	 * 권한 그룹에 대한 열거형 입니다. 반드시 DB의 권한 그룹 레코드와 1:1 매칭되어야 합니다.
	 * 1전체공개(PUBLIC)
	 * 2회원공개(MEMBER)
	 * 3비밀글(SECRET)
	 * 4비공개(CLOSE)
	 */
	PUBLIC, MEMBER, SECRET, CLOSE
}
