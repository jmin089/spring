package com.java.www.service;

import com.java.www.dto.MemberDto;

public interface MService {

	//jsp 로그인 확인
	MemberDto login(MemberDto mdto);

	//ajax 로그인 확인
	int a_login(MemberDto mdto);
	//result 타입이 String
	//String a_login(MemberDto mdto);

	//회원가입 저장
	void memInsert(MemberDto mdto);

}
