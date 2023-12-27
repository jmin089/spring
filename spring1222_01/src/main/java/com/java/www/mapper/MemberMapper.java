package com.java.www.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.MemberDto;

@Mapper
public interface MemberMapper {
	
	//로그인 확인
	MemberDto login(MemberDto mdto);

	//ajax 아이디 체크
	MemberDto idCheck(String id);

	void mInsert(MemberDto mdto);

}
