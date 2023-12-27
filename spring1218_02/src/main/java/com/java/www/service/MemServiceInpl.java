package com.java.www.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.MemberDto;
import com.java.www.mapper.MemMapper;

@Service
public class MemServiceInpl implements MemService{
	
	@Autowired
	MemMapper memMapper;

	@Override
	public MemberDto loginSelect(MemberDto mdto) {
		MemberDto memberDto = memMapper.loginSelect(mdto);
		return memberDto;
	}

}
