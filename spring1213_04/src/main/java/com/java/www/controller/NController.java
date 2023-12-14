package com.java.www.controller;

import java.sql.Timestamp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.java.www.dto.BoardDto;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("notice")
public class NController {

	// 예전에는 이렇게 많이 사용했는데 요즘에는 아래 방법을 사용함
	@RequestMapping(method=RequestMethod.GET,value = "noticeList") //이게정석임 현재는간편해짐
	public String noticeList() {
		return "notice/noticeList"; //notice폴더 안에 넣어두면 리턴은 꼭 notice/noticeList 이렇게 적어줘야함.
	}

	// index -> noticeInsert(get) -> doNoticeInsert(post) ------------------------------------
	@GetMapping("noticeInsert")
	public String noticeInsert() {
		return "notice/noticeInsert";
	}

	@PostMapping("doNoticeInsert")
	public String doNoticeInsert(HttpServletRequest request, Model model) {
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		String bfile = request.getParameter("bfile");
		System.out.printf("%s,%s,%s\n", btitle, bcontent, bfile);
		System.out.println("MController btitle : " + btitle);
		System.out.println("MController bcontent : " + bcontent);
		System.out.println("MController bfile : " + bfile);
		
		Timestamp bdate = new Timestamp(System.currentTimeMillis());
		model.addAttribute("btitle", btitle);
		model.addAttribute("bcontent", bcontent);
		model.addAttribute("bdate", bdate);
		model.addAttribute("bfile", bfile);

		//이방법은 순서 상관없이 사용해도 가능
		BoardDto bdto = BoardDto.builder().btitle(btitle).bcontent(bcontent).bdate(bdate).bfile(bfile).build();
		model.addAttribute("bdto", bdto);
		return "notice/noticeView";
	}

	// ------------------------------------ index -> noticeInsert(get) -> doNoticeInsert(post)

	/*
	 * @RequestMapping("noticeList") //폴더를 안써도 상관없음(url주소)
	 * public String noticeList() {
	 * return "notice/noticeList"; //폴더안에 있으면 폴더 써줘야함
	 * }
	 */

	/*
	 * @RequestMapping("noticeInsert")
	 * public String noticeInsert() {
	 * return "notice/noticeInsert";
	 * }
	 */

}