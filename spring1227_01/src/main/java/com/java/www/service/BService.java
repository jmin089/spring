package com.java.www.service;

import java.util.List;
import java.util.Map;

import com.java.www.dto.BCommentDto;
import com.java.www.dto.BoardDto;

public interface BService {

	//공지사항 전체가져오기
	Map<String, Object> selectAll(int page, String category, String searchWord);
	//게시글 검색
	Map<String, Object> selectSearch(int page, String category, String searchWord);

	//공지사항 게시글 1개 가져오기
	Map<String, Object> selectOne(int bno);

	//ajax 댓글 1개 저장 후 댓글 1개 가져오기
	BCommentDto BCommentInsert(BCommentDto cdto);

	//댓글 1개 삭제
	String BCommentDelete(int cno);

	//댓글 수정 저장
	BCommentDto BCommentUpdate(BCommentDto cdto);

}
