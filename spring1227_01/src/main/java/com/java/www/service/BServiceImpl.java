package com.java.www.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.BCommentDto;
import com.java.www.dto.BoardDto;
import com.java.www.mapper.BoardMapper;

import jakarta.servlet.http.HttpSession;

@Service
public class BServiceImpl implements BService {

	@Autowired BoardMapper boardMapper;
	@Autowired HttpSession session;
	
	//게시글 검색 & 하단넘버링
	@Override
	public Map<String, Object> selectSearch(int page, String category, String searchWord) {
		if(page<=0) page = 1;
		int countPerPage = 10;
		int bottomPerNaum = 10;
		int countAll = boardMapper.selectSearchCount(category, searchWord);
		System.out.println("BServiceImpl selectSearch countAll : "+countAll);
		int maxPage = (int)Math.ceil((double)countAll/countPerPage);
		int startPage = ((page-1)/10)*10+1;
		int endPage = (startPage+bottomPerNaum)-1;
		int StartRow = (page-1)*countPerPage+1;
		int endRow = StartRow+countPerPage-1;
		System.out.println("BServiceImpl selectSearch maxPage : "+maxPage);
		
		if(endPage>maxPage) endPage=maxPage;
		
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
	
	//공지사항 전체가져오기
	@Override
	public Map<String, Object> selectAll(int page, String category, String searchWord) {
		if(page<=0) page = 1;
		int countPerPage = 10;
		int bottomPerNaum = 10;
		int countAll = boardMapper.selectCountAll(category, searchWord);
		System.out.println("BServiceImpl selectSearch countAll : "+countAll);
		int maxPage = (int)Math.ceil((double)countAll/countPerPage);
		int startPage = ((page-1)/10)*10+1;
		int endPage = (startPage+bottomPerNaum)-1;
		int StartRow = (page-1)*countPerPage+1;
		int endRow = StartRow+countPerPage-1;
		System.out.println("BServiceImpl selectSearch maxPage : "+maxPage);
		
		if(endPage>maxPage) endPage=maxPage;
		
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
	
	//공지사항 게시글 1개 가져오기
	@Override
	public Map<String, Object> selectOne(int bno) {
		BoardDto bdto = boardMapper.selectOne(bno);
		//하단댓글 모두 가져오기
		List<BCommentDto> bCommentlist = boardMapper.bCommentSelectAll(bno);
		Map<String, Object> map = new HashMap<>();
		map.put("bdto", bdto);
		map.put("bCommentlist", bCommentlist);
		return map;
	}

	//ajax 댓글 1개 저장 후 댓글 1개 가져오기
	@Override
	public BCommentDto BCommentInsert(BCommentDto cdto) {
		//session id를 cdto의 id에 저장
		cdto.setId((String)session.getAttribute("session_id"));
		
		//댓글 1개 저장하기 - cno, cdate를 가지고옴.
		boardMapper.BCommentInsert(cdto);  //댓글폼에서 입력한 내용을 저장시킴
		System.out.println("BServiceImpl BCommentInsert cno : "+cdto.getCno());
		System.out.println("BServiceImpl BCommentInsert cdate : "+cdto.getCdate());
		
		//댓글 1개 가져오기
		//BCommentDto bCommentDto = boardMapper.BCommentSelectOne(cdto.getCno());
		return cdto;
	}
	
	//댓글 1개 삭제
	@Override
	public String BCommentDelete(int cno) {
		String result = "";
		int re =  boardMapper.BCommentDelete(cno);
		return result+re;
	}

	//댓글 수정 저장
	@Override
	public BCommentDto BCommentUpdate(BCommentDto cdto) {
		//session id를 cdto의 id에 저장
		cdto.setId((String)session.getAttribute("session_id"));
		
		//수정 저장
		boardMapper.BCommentUpdate(cdto);
		
		//댓글 1개 가져오기
		BCommentDto bCommentDto = boardMapper.BCommentSelectOne(cdto.getCno());
		return bCommentDto;
	}

}
