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
	
	//jsp 로그인 확인
	@Override
	public MemberDto login(MemberDto mdto) {
		MemberDto memberDto = memberMapper.login(mdto);
		return memberDto;
	}

	//ajax 로그인 확인
	@Override
	public int a_login(MemberDto mdto) {
		int result = 0;
		MemberDto memberDto = memberMapper.a_login(mdto);
		if(memberDto != null) {
			session.setAttribute("session_id", memberDto.getId());
			session.setAttribute("session_name", memberDto.getName());
			result = 1;
			System.out.println("MServiceImpl a_login result : "+result);
		}
		return result;
	}
	/* result 타입이 String
	@Override
	public int a_login(MemberDto mdto) {
		MemberDto memberDto = memberMapper.a_login(mdto);
		//id, pw를 가지고 회원을 찾으면 - 로그인성공, 없으면 - 로그인실패
		String result = "";
		if(memberDto != null) {
			session.setAttribute("session_id", memberDto.getId());
			session.setAttribute("session_name", memberDto.getName());
			result = "로그인성공";
			System.out.println("MServiceImpl a_login result : "+result);
		}else{
			result = "로그인실패";
		}
		
		return result;
	}
	*/

	//회원가입 저장
	@Override
	public void memInsert(MemberDto mdto) {
		System.out.println("MServiceImpl memInsert id : "+mdto.getId());
		memberMapper.memInsert(mdto);
		//System.out.println("MServiceImpl memInsert id(memberDto) : "+memberDto.getId());
		
	}

}
