package com.java.www.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.www.dto.BoardDto;
import com.java.www.mapper.BoardMapper;

@Service
public class BServiceImpl implements BService {

	@Autowired
	BoardMapper boardMapper;

	//게시글 검색
	@Override
	public Map<String, Object> selectSearch(int page, String category, String searchWord) {
		//하단 넘버링 - page, countPerPage:1화면에 게시글 개수, startPage, endPage, maxPage
		if(page<=0) page=1;
		int countPerPage = 10;                         //1화면에 보이는 게시글 수
		int bottomPerNum = 10;                         //하단넘버링 개수
		int countAll = boardMapper.selectSearchCount(category, searchWord);   //게시글 검색 총 개수
		System.out.println("BServiceImpl selectSearch countAll: "+countAll);
		int maxPage = (int) Math.ceil((double)countAll/countPerPage);     //45개 = 45/10 = 4.5 올림해서 5
		int startPage = ((page-1)/10)*10+1;            //공식임.. 그냥 외우라 5-1=4/10 -> 0*10+1
		int endPage = (startPage + bottomPerNum)-1;    //이것도 공식임..  걍 외우라
		int StartRow = (page-1) * countPerPage+1;      //1, 11, 21, 31 ... 91, 101
		int endRow = StartRow + countPerPage-1;        //10, 20,30 ... 90, 100
		System.out.println("BServiceImpl selectSearch maxPage : "+maxPage);
		
		//startPage : 1 endPage : 10 maxPage가 5이면 endPage에 maxPage를 넣어서 1~10 나오는 것이 아니라 1~5까지만 나타나도록 함.
		if(endPage>maxPage) endPage=maxPage;
		
		//데이터 전송 - list, page, maxPage, startPage, endPage
		ArrayList<BoardDto> list = boardMapper.selectSearch(StartRow, endRow, category, searchWord);
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("countAll", countAll);
		map.put("page", page);
		map.put("maxPage", maxPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		return map;
	}
	
	//게시글 전체 가져오기
	@Override
	public Map<String, Object> selectAll(int page, String category, String searchWord) {
		//하단 넘버링 - page, countPerPage:1화면에 게시글 개수, startPage, endPage, maxPage
		if(page<=0) page=1;
		int countPerPage = 10;                         //1화면에 보이는 게시글 수
		int bottomPerNum = 10;                         //하단넘버링 개수
		int countAll = boardMapper.selectCountAll(category, searchWord);   //게시글 총 개수
		int maxPage = (int) Math.ceil((double)countAll/countPerPage);     //45개 = 45/10 = 4.5 올림해서 5
		int startPage = ((page-1)/10)*10+1;            //공식임.. 그냥 외우라 5-1=4/10 -> 0*10+1
		int endPage = (startPage + bottomPerNum)-1;    //이것도 공식임..  걍 외우라
		int StartRow = (page-1) * countPerPage+1;      //1, 11, 21, 31 ... 91, 101
		int endRow = StartRow + countPerPage-1;        //10, 20,30 ... 90, 100
		
		
		//startPage : 1 endPage : 10 maxPage가 5이면 endPage에 maxPage를 넣어서 1~10 나오는 것이 아니라 1~5까지만 나타나도록 함.
		if(endPage>maxPage) endPage=maxPage;
		
		//데이터 전송 - list, page, maxPage, startPage, endPage
		ArrayList<BoardDto> list = boardMapper.selectAll(StartRow, endRow, category, searchWord);
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("countAll", countAll);
		map.put("page", page);
		map.put("maxPage", maxPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		return map;
	}

	//게시글 1개 , 이전글, 다음글 가져오기
	@Override
	public Map<String, Object> selectOne(int bno) {
		BoardDto bdto = boardMapper.selectOne(bno);
		BoardDto prevdto = boardMapper.selectOnePrev(bno);
		BoardDto nextdto = boardMapper.selectOneNext(bno);
		
		//조회수 1증가
		boardMapper.bhitUp(bno);
		
		Map<String, Object> map = new HashMap<>();
		map.put("bdto", bdto);
		map.put("prevdto", prevdto);
		map.put("nextdto", nextdto);
		return map;
	}

	//글쓰기 저장
	@Override
	public void bInsert(BoardDto bdto) {
		int result = boardMapper.bInsert(bdto);
		System.out.println("BServiceImpl bInsert result : "+result);
	}

	//게시글 삭제
	@Override
	public void bDelete(int bno) {
		int result = boardMapper.bDelete(bno);
		System.out.println("BServiceImpl bDelete result : "+result);
	}

	//게시글 수정 저장
	@Override
	public void doBUpdate(BoardDto bdto) {
		int result = boardMapper.doBUpdate(bdto);
		System.out.println("BServiceImpl doBUpdate result : "+result);
	}

	//답변달기 저장
	@Override
	public void doBReply(BoardDto bdto) {
		// bdto 안에는 bgroup, bstep, bindent 들어가 있음.
		// 1. 부모보다 큰 bstep은 1씩 증가
		// 2. 현재글은 부모bstep +1 저장
		// 3. bindent는 부모의+1
		// 4. bgroup은 부모와 같음.
		boardMapper.bstepUp(bdto);
		/* 커리문에 #{bstep}+1,#{bindent}+1 동일함
		bdto.setBstep(bdto.getBstep()+1);
		bdto.setBindent(bdto.getBindent()+1);	*/
		
		int result = boardMapper.doBReply(bdto);
		System.out.println("BServiceImpl doBReply result : "+result);
	}


}
