package com.java.www.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.BoardDto;
import com.java.www.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMapper boardMapper;
	
	//전체글
	@Override
	public ArrayList<BoardDto> BListAll() {
		ArrayList<BoardDto> list = boardMapper.BListAll();
		return list;
	}

}
