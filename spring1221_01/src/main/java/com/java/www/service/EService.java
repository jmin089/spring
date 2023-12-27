package com.java.www.service;

import java.util.ArrayList;

import com.java.www.dto.EmpDeptDto;
import com.java.www.dto.EmpDto;
import com.java.www.dto.MemBoardDto;

public interface EService {

	//사원정보 가져오기
	ArrayList<EmpDto> selectAll();

	//사원정보 가져오기2
	ArrayList<EmpDeptDto> selectAll2();

	//회원 게시글
	ArrayList<MemBoardDto> MBSelect();

}
