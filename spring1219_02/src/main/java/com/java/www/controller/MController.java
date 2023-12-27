package com.java.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.www.dto.MemberDto;
import com.java.www.service.MService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MController {
	
	@Autowired MService mService;
	@Autowired HttpSession session;
	
	@RequestMapping("login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("doLogin")
	public String doLogin(MemberDto mdto, Model model, HttpServletRequest request) {
		int result = 0;
		System.out.println("BController id : "+mdto.getId());
		MemberDto memberDto = mService.login(mdto);
		if(memberDto != null) {
			session.setAttribute("session_id", memberDto.getId());
			session.setAttribute("session_name", memberDto.getName());
			result = 1;
		}
		model.addAttribute("result",result);
		System.out.println("BController result : "+result);
		return "doLogin";
	}

}
