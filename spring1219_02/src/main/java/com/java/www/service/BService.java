package com.java.www.service;

import java.util.ArrayList;
import java.util.Map;

import com.java.www.dto.BoardDto;

public interface BService {

	ArrayList<BoardDto> selectAll();

	Map<String, Object> selectOne(int bno);

}
