package com.java.www.service;

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
	
	//공지사항 전체가져오기
	@Override
	public List<BoardDto> selectAll() {
		List<BoardDto> list = boardMapper.selectAll();
		return list;
	}

	//공지사항 게시글 1개 가져오기
	@Override
	public Map<String, Object> selectOne(int bno) {
		//게시글 1개 가져오기
		BoardDto bdto = boardMapper.selectOne(bno);
		
		//하단댓글 모두 가져오기
		List<BCommentDto> bCommentlist = boardMapper.bC_selectAll(bno);
		Map<String, Object> map = new HashMap<>();
		map.put("bdto", bdto);
		map.put("bCommentlist", bCommentlist);
		return map;
	}
	

}
