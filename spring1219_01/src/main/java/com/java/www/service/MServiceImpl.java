package com.java.www.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.MemberDto;
import com.java.www.mapper.MemberMapper;

import jakarta.servlet.http.HttpSession;

@Service
public class MServiceImpl implements MService {

	@Autowired MemberMapper memberMapper;
	@Autowired HttpSession session;
	
	//로그인 아이디 패스워드
	@Override
	public int login(String id, String pw) {
		int result = 0;
		//db전송
		MemberDto mdto = memberMapper.login(id, pw);
		//1개의 데이터가 있으면 
		if(mdto != null) {
			result = 1;
			session.setAttribute("session_id", mdto.getId());
			session.setAttribute("session_name", mdto.getName());
		}
		System.out.println("MServiceImpl login result : "+result);
		return result;
	}

	//ajax 로그인 아이디 체크
	@Override
	public String idCheck(String id) {
		String result = "사용불가";
		MemberDto mdto = memberMapper.idCheck(id);
		if(mdto == null) result="사용가능";
		return result;
	}

	//회원가입 저장
	@Override
	public String mInsert(MemberDto mdto) {
		String result = "가입 완완완완완완완완";   //resutl값을 data로 넘겨줘서 동일해야함.
		//db전송
		memberMapper.mInsert(mdto);
		return result;
	}

}
