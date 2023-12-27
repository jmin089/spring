package com.java.www.service;

import java.util.ArrayList;
import java.util.Map;

import com.java.www.dto.BoardDto;

public interface BService {

	//전체글 가져오기
	ArrayList<BoardDto> selectAll();

	//게시글 1개 가져오기 : 현재글, 이전글, 다음글
	Map<String, Object> selectOne(int bno);

	//게시글 쓰기 저장
	void bInsert(BoardDto bdto);

	//게시글 삭제하기
	void bDelete(int bno);

	//게시글 수정 저장
	void doBUpdate(BoardDto bdto);

}
