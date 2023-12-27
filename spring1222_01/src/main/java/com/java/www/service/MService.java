package com.java.www.service;

import com.java.www.dto.MemberDto;

public interface MService {

	//로그인 확인
	MemberDto login(MemberDto mdto);

	//ajax 아이디 체크
	String idCheck(String id);

	String mInsert(MemberDto mdto);

}
