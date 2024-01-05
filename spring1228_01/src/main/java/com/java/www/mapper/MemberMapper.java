package com.java.www.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.MemberDto;

@Mapper
public interface MemberMapper {

	//jsp 로그인 확인
	MemberDto login(MemberDto mdto);

	//ajax 로그인 확인
	MemberDto a_login(MemberDto mdto);

	//회원가입 저장
	void memInsert(MemberDto mdto);

}
