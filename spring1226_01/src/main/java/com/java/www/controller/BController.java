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

import com.java.www.dto.BcommentDto;
import com.java.www.dto.BoardDto;
import com.java.www.service.BService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("customer")
public class BController {
	
	@Autowired BService bService;
	@Autowired HttpSession session;
	
	//공지사항 전체가져오기
	@GetMapping("notice")
	public String notice(Model model) {
		//page 가지고 와야함.
		//service 연결 - 리턴값 list
		List<BoardDto> list = bService.selectAll();
		
		//model 전송
		model.addAttribute("list",list);
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
	
	//ajax 댓글 입력
	@PostMapping("BcommentInsert")
	@ResponseBody
	public BcommentDto BcommentInsert(BcommentDto cdto) {
		System.out.println("BServiceImpl BcommentInsert cpw : "+cdto.getCpw());
		System.out.println("BServiceImpl BcommentInsert ccontent : "+cdto.getCcontent());
		System.out.println("BServiceImpl BcommentInsert bno : "+cdto.getBno());
		cdto.setId((String)session.getAttribute("session_id"));
		
		//db에 저장된 댓글 1개 가져오기 - cno, cdate가 포함되어 있음.
		BcommentDto bcommentDto = bService.BcommentInsert(cdto);
		return bcommentDto;
	}

}
