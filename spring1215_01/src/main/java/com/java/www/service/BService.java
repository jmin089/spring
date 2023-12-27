package com.java.www.service;

import java.util.ArrayList;

import com.java.www.dto.BoardDto;

public interface BService {

	//게시글 전체가져오기
	ArrayList<BoardDto> selectAll();

}
