package com.java.service;

import java.util.List;

import com.java.dto.BoardDto;

public interface BService {

	//전체 게시글 가져오기
	List<BoardDto> selectAll();

	//1개 게시글 가져오기
	BoardDto selectOne(int bno);

	//submit버튼 클릭시 파일 업로드 - 게시글저장
	void bwrite(BoardDto bdto);

}
