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
	
	//로그인 확인
	@Override
	public int login(MemberDto mdto) {
		int result = 0;
		//db연결하기(Mapper)  리턴값을 dto
		MemberDto memberdto =  memberMapper.login(mdto);
		if(memberdto != null) {
			session.setAttribute("session_id", memberdto.getId());
			session.setAttribute("session_name", memberdto.getName());
			System.out.println("MServiceImpl login session : "+memberdto.getId());
			result = 1;  //로그인 성공
		}
		return result;
	}

}
