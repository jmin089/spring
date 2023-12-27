package com.java.www.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.MemberDto;
import com.java.www.mapper.MemberMapper;

@Service
public class MServiceImpl implements MService {

	@Autowired MemberMapper memberMapper;
	
	//로그인 확인
	@Override
	public MemberDto login(MemberDto mdto) {
		MemberDto memberDto = memberMapper.login(mdto);
		return memberDto;
	}

	//ajax 아이디 체크
	@Override
	public String idCheck(String id) {
		String result = "사용불가능";
		MemberDto mdto = memberMapper.idCheck(id);
		if(mdto == null) result = "사용가능";
		return result;
	}

	@Override
	public String mInsert(MemberDto mdto) {
		String result = "가입성공";
		memberMapper.mInsert(mdto);
		return result;
	}

}
