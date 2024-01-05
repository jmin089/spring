package com.java.service;

import java.util.List;

import com.java.dto.BoardDto;

public interface BService {

	//전체 게시글 가져오기
	List<BoardDto> blist();

	//submit버튼 클릭시 파일 업로드 - 게시글저장
	void write(BoardDto bdto);

	//게시글 1개 가져오기
	BoardDto selectOne(int bno);

}
