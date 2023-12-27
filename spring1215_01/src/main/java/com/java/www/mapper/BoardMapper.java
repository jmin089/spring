package com.java.www.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.BoardDto;

@Mapper  //boardMapper.xml이랑 연결됨.
public interface BoardMapper {

	ArrayList<BoardDto> selectAll();

}
