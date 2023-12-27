package com.java.www.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.BoardDto;
import com.java.www.mapper.BoardMapper;

@Service
public class BServiceImpl implements BService {

	@Autowired BoardMapper boardMapper;
	
	//전체글 가져오기
	@Override
	public ArrayList<BoardDto> selectAll() {
		ArrayList<BoardDto> list = boardMapper.selectAll();
		return list;
	}

	//게시글 1개 가져오기 : 현재글, 이전글, 다음글
	@Override
	public Map<String, Object> selectOne(int bno) {
		BoardDto bdto = boardMapper.selectOne(bno);
		BoardDto prevDto = boardMapper.selectOnePrev(bno);
		BoardDto nextDto = boardMapper.selectOneNext(bno);
		Map<String, Object> map = new HashMap<>();
		map.put("bdto", bdto);
		map.put("prevDto", prevDto);
		map.put("nextDto", nextDto);
		return map;
	}

	//게시글 쓰기 저장
	@Override
	public void bInsert(BoardDto bdto) {
		int result = boardMapper.bInsert(bdto);
		System.out.println("BServiceImpl bInsert result : "+result);
	}

	//게시글 삭제하기
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

}
