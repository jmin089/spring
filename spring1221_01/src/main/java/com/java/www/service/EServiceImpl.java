package com.java.www.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.EmpDeptDto;
import com.java.www.dto.EmpDto;
import com.java.www.dto.MemBoardDto;
import com.java.www.mapper.EmpMapper;

@Service
public class EServiceImpl implements EService {

	@Autowired EmpMapper empMapper;
	
	//사원정보 가져오기
	@Override
	public ArrayList<EmpDto> selectAll() {
		ArrayList<EmpDto> list = empMapper.selectAll();
		return list;
	}

	//사원정보 가져오기2
	@Override
	public ArrayList<EmpDeptDto> selectAll2() {
		ArrayList<EmpDeptDto> list = empMapper.selectAll2();
		return list;
	}

	//회원 게시글
	@Override
	public ArrayList<MemBoardDto> MBSelect() {
		ArrayList<MemBoardDto> list = empMapper.MBSelect();
		return list;
	}

}
