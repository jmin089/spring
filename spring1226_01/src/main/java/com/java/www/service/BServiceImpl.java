package com.java.www.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.www.dto.BcommentDto;
import com.java.www.dto.BoardDto;
import com.java.www.mapper.BoardMapper;

import jakarta.servlet.http.HttpSession;

@Service
public class BServiceImpl implements BService {

	@Autowired BoardMapper boardMapper;
	
	//공지사항 전체가져오기
	@Override
	public List<BoardDto> selectAll() {
		List<BoardDto> list = boardMapper.selectAll();
		return list;
	}

	//공지사항 게시글 1개 가져오기
	@Override
	public Map<String, Object> selectOne(int bno) {
		System.out.println("BServiceImpl selectOne bno : "+bno);
		BoardDto bdto = boardMapper.selectOne(bno);
		//댓글 전체 가져오기
		List<BcommentDto> list = boardMapper.BcommentSelectAll(bno);
		//map에 게시글, 댓글 넣어서 보냄
		Map<String, Object> map = new HashMap<>();
		map.put("bdto", bdto);
		map.put("list", list);
		return map;
	}

	//ajax 댓글 입력
	@Override
	public BcommentDto BcommentInsert(BcommentDto cdto) {
		//댓글 1개 저장
		boardMapper.BcommentInsert(cdto);
		System.out.println("BServiceImpl BcommentInsert cno : "+cdto.getCno());
		
		//저장된 댓글 1개 가져오기
		BcommentDto bcommentDto = boardMapper.BcommentselectOne(cdto.getCno());
		System.out.println("BServiceImpl BcommentInsert ccontent : "+bcommentDto.getCcontent());
		return bcommentDto;
	}
	

}
