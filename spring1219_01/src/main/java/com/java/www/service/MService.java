package com.java.www.service;

import com.java.www.dto.MemberDto;

public interface MService {

	//로그인 아이디 패스워드
	int login(String id, String pw);

	//ajax 로그인 아이디 체크
	String idCheck(String id);

	//회원가입 저장
	String mInsert(MemberDto mdto);

}
