package com.java.www.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.BCommentDto;
import com.java.www.dto.BoardDto;

@Mapper
public interface BoardMapper {

	//공지사항 전체가져오기
	ArrayList<BoardDto> selectAll(int startRow, int endRow, String category, String searchWord);
	//게시글 검색 전체 가져오기
	ArrayList<BoardDto> selectSearch(int startRow, int endRow, String category, String searchWord);
	//게시글 검색개수
	int selectSearchCount(String category, String searchWord);
	//게시글 총개수
	int selectCountAll(String category, String searchWord);

	//공지사항 게시글 1개 가져오기
	BoardDto selectOne(int bno);

	//하단댓글 모두 가져오기
	List<BCommentDto> bCommentSelectAll(int bno);

	//ajax 댓글 1개 저장하기
	void BCommentInsert(BCommentDto cdto);

	//ajax 댓글 1개 가져오기
	BCommentDto BCommentSelectOne(int cno);

	//댓글 1개 삭제
	int BCommentDelete(int cno);

	//댓글 수정 저장
	void BCommentUpdate(BCommentDto cdto);

}
