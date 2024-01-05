package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.BoardDto;
import com.java.mapper.BoardMapper;

@Service
public class BServiceImpl implements BService {

	@Autowired BoardMapper boardMapper;
	
	//전체 게시글 가져오기
	@Override
	public List<BoardDto> blist() {
		List<BoardDto> list = boardMapper.blist();
		return list;
	}

	//submit버튼 클릭시 파일 업로드 - 게시글저장
	@Override
	public void write(BoardDto bdto) {
		boardMapper.write(bdto);
	}

	//게시글 1개 가져오기
	@Override
	public BoardDto selectOne(int bno) {
		BoardDto bdto = boardMapper.selectOne(bno);
		return bdto;
	}

}
