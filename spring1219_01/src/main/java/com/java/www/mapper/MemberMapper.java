package com.java.www.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.MemberDto;

@Mapper
public interface MemberMapper {

	//로그인 아이디 패스워드
	MemberDto login(String id, String pw);

	//ajax 로그인 아이디 체크
	MemberDto idCheck(String id);

	//회원가입 저장
	void mInsert(MemberDto mdto);

}
