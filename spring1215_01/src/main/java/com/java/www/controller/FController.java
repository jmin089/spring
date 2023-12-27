package com.java.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.www.dto.BoardDto;
import com.java.www.dto.MemberDto;

@Controller
public class FController {
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	//board 관련-----------------------------------------------
	@RequestMapping("bInsert")
	public String bInsert() {
		return "bInsert";  //bInsert.jsp -> "bInsert"String으로 리턴
	}
	
	@RequestMapping("bUpdate")
	public String bUpdate(BoardDto bdto, Model model) {
		model.addAttribute("bdto",bdto);
		return "bUpdate";
	}
	
	@RequestMapping("bView")
	public String bView(BoardDto bdto, MemberDto mdto, Model model) {  //BoardDto : bInsert 데이터 받는거 , Model : 데이터 보내는거
		System.out.println("FController bdto id : "+bdto.getId());
		System.out.println("FController mdto id : "+mdto.getId());
		model.addAttribute("bdto",bdto);
		return "bView";
	}
	//-----------------------------------------------board 관련
	
	@RequestMapping("login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("mLogin")
	public String mLogin(@RequestParam String id, @RequestParam String pw, Model model) {
		model.addAttribute("id",id);
		model.addAttribute("pw",pw);
		System.out.println("FController id : "+id);
		System.out.println("FController pw : "+pw);
		return "mLogin";
	}

}
