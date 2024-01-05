package com.java.www.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.BCommentDto;
import com.java.www.dto.BoardDto;

@Mapper
public interface BoardMapper {

	//공지사항 전체가져오기
	List<BoardDto> selectAll();

	//공지사항 게시글 1개 가져오기
	BoardDto selectOne(int bno);

	//하단댓글 모두 가져오기
	List<BCommentDto> bC_selectAll(int bno);



}
