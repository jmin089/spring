package com.java.www.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.BoardDto;

@Mapper
public interface BoardMapper {

	//전체글 가져오기
	ArrayList<BoardDto> selectAll();

	//게시글 1개 가져오기 : 현재글
	BoardDto selectOne(int bno);
	//게시글 1개 가져오기 : 이전글
	BoardDto selectOnePrev(int bno);
	//게시글 1개 가져오기 : 다음글
	BoardDto selectOneNext(int bno);

	//게시글 쓰기 저장
	int bInsert(BoardDto bdto);

	//게시글 삭제하기
	int bDelete(int bno);

	//게시글 수정 저장
	int doBUpdate(BoardDto bdto);

}
