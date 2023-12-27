package com.java.www.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.BoardDto;
import com.java.www.mapper.BoardMapper;

@Service
public class BServiceImpl implements BService {

	@Autowired BoardMapper boardMapper;
	
	@Override
	public ArrayList<BoardDto> selectAll() {
		ArrayList<BoardDto> list = boardMapper.selectAll();
		return list;
	}

	@Override
	public Map<String, Object> selectOne(int bno) {
		BoardDto bdto = boardMapper.selectOne(bno);
		BoardDto prevDto = boardMapper.selectOnePrev(bno);
		BoardDto nextDto = boardMapper.selectOneNext(bno);
		
		Map<String, Object> map = new HashMap<>();
		map.put("bdto", bdto);
		map.put("prevDto", prevDto);
		map.put("nextDto", nextDto);
		return map;
	}

}
