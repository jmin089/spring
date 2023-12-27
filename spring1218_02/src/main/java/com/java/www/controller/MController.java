package com.java.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.www.dto.MemberDto;
import com.java.www.service.MemService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("member")
public class MController {
	
	@Autowired
	MemService memService;
	
	@Autowired
	HttpSession seeeion;
		
	@RequestMapping("login")
	public String login() {
		return "member/login";
	}
	
	@RequestMapping("doLogin")
	public String doLogin(MemberDto mdto, Model model, HttpServletRequest request) {
		int result = 0;
		System.out.println("MController id : "+mdto.getId());
		MemberDto memberDto = memService.loginSelect(mdto);
		if(memberDto!=null) {
			System.out.println("MController mdto id : "+mdto.getId());
			result = 1;
		}else {
			System.out.println("MController memberDto id : unll");
		}
		
		model.addAttribute("result",result);
		return "member/doLogin";
	}

}
