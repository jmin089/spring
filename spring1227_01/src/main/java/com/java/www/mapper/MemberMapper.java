package com.java.www.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.MemberDto;
import com.java.www.dto.MemberDto2;

@Mapper
public interface MemberMapper {

	//로그인 확인
	MemberDto2 login(MemberDto2 mdto2);

	//아이디 찾기
	MemberDto2 a_idsearch(String name, String email);

	//비밀번호 찾기
	MemberDto2 pwsearch(String id, String email);

	//비밀번호 변경
	void update_pw(String pwcode, String id);

	//아이디 중복확인
	MemberDto2 idCheck(String id);

}
