package com.java.www.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;
import com.java.www.dto.BoardDto;

@Mapper
public interface BoardMapper {
	
	//게시글 전체 가져오기
	public ArrayList<BoardDto> selectAll(int startRow, int endRow, String category, String searchWord);
	//게시글 검색 전체 가져오기
	public ArrayList<BoardDto> selectSearch(int startRow, int endRow, String category, String searchWord);
	//게시글 총개수
	public int selectCountAll(String category, String searchWord);
	//게시글 검색개수
	public int selectSearchCount(String category, String searchWord);

	//게시글 1개 가져오기 - 현재글
	public BoardDto selectOne(int bno);
	//게시글 1개 가져오기 - 이전글
	public BoardDto selectOnePrev(int bno);
	//게시글 1개 가져오기 - 다음글
	public BoardDto selectOneNext(int bno);
	
	//조회수 1증가
	public void bhitUp(int bno);

	//글쓰기 저장
	public int bInsert(BoardDto bdto);

	//게시글 삭제
	public int bDelete(int bno);

	//게시글 수정 저장
	public int doBUpdate(BoardDto bdto);

	//답변달기 저장
	//답변달기 저장 - 다른게시글 bstep 1증가
	public void bstepUp(BoardDto bdto);
	public int doBReply(BoardDto bdto);
	
}
