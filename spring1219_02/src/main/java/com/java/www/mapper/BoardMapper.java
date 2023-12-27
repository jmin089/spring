package com.java.www.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.BoardDto;

@Mapper
public interface BoardMapper {

	ArrayList<BoardDto> selectAll();

	BoardDto selectOne(int bno);

	BoardDto selectOnePrev(int bno);

	BoardDto selectOneNext(int bno);

}
