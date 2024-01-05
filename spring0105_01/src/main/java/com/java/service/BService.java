package com.java.service;

import java.util.List;

import com.java.dto.BoardDto;

public interface BService {

	//전체 게시글 가져오기
	List<BoardDto> selectAll();

	//게시글 쓰기 저장
	void bwrite(BoardDto bdto);

	//1개 게시글 가져오기
	BoardDto selectOne(int bno);

}
