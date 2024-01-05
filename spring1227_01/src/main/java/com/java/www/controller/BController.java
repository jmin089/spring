package com.java.www.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.www.dto.BCommentDto;
import com.java.www.dto.BoardDto;
import com.java.www.service.BService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("customer")
public class BController {
	
	@Autowired BService bService;
	@Autowired HttpSession session;
	
	//게시글 검색
	@GetMapping("search")
	public String search(@RequestParam(defaultValue = "1")int page, @RequestParam(required = false)String category,
			@RequestParam(required = false)String searchWord, Model model) {
		System.out.println("BController search category : "+category);
		System.out.println("BController search searchWord : "+searchWord);
		
		Map<String, Object> map = bService.selectSearch(page, category, searchWord);
		System.out.println("BController search countAll : "+(int)map.get("countAll"));
		model.addAttribute("map",map);
		return "customer/notice";
	}
	
	//공지사항 전체가져오기
	@GetMapping("notice")
	public String notice(@RequestParam(defaultValue = "1")int page, @RequestParam(required = false)String category,
			@RequestParam(required = false)String searchWord, Model model) {
		//page 가지고 와야함.
		//service 연결 - 리턴값 list
		Map<String, Object> map = bService.selectAll(page, category, searchWord);
		System.out.println("BController notice countAll : "+(int)map.get("countAll"));
		model.addAttribute("map",map);
		return "customer/notice";
	}
	
	//공지사항 게시글 1개 가져오기
	@GetMapping("notice_view")
	public String notice_view(@RequestParam(defaultValue = "1") int bno, Model model) {
		System.out.println("BController notice_view bno : "+bno);
		//service 연결 - 리턴값 dto
		Map<String, Object> map = bService.selectOne(bno);
		//model 전송
		model.addAttribute("map",map);
		return "customer/notice_view";
	}
	
	//댓글 1개 저장 후 댓글 1개 가져오기
	@PostMapping("BCommentInsert")
	@ResponseBody                      //ajax 데이터 전송
	public BCommentDto BCommentInsert(BCommentDto cdto) {
		System.out.println("BController BCommentInsert bno : "+cdto.getBno());
		//service 연결 - 리턴값 dto
		//저장시간, cno를 받아와야함.
		BCommentDto bCommentDto = bService.BCommentInsert(cdto);
		return bCommentDto;
	}
	
	//댓글 1개 삭제
	@PostMapping("BCommentDelete")
	@ResponseBody                      //ajax 데이터 전송
	public String BCommentDelete(int cno) {
		System.out.println("BController BCommentDelete cno : "+cno);
		//service 연결 - 댓글 삭제
		String result = bService.BCommentDelete(cno);
		return "삭제처리";
	}
	
	//댓글 수정 저장
	@PostMapping("BCommentUpdate")
	@ResponseBody                      //ajax 데이터 전송
	public BCommentDto BCommentUpdate(BCommentDto cdto) {
		System.out.println("BController BCommentUpdate cno : "+cdto.getCno());
		//service 연결
		BCommentDto bCommentDto = bService.BCommentUpdate(cdto);
		return bCommentDto;
	}
	
}
