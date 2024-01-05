package com.java.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.www.dto.MemberDto;
import com.java.www.service.MService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("member")
public class MController {
	
	@Autowired MService mService;
	@Autowired HttpSession session;
	
	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	
	@GetMapping("logout")
	public String logout() {
		session.invalidate();
		return "member/logout";
	}
	//로그인 확인 ajax 형태
	@PostMapping("ajaxLogin")
	@ResponseBody                  //데이터로 전송
	public String ajaxLogin(MemberDto mdto) {
		System.out.println("MController login id : "+mdto.getId());
		System.out.println("MController login pw : "+mdto.getPw());
		
		//service 연결 - 1또는 0으로 받음 1-성공 0-실패
		int result = mService.login(mdto);
		System.out.println("MController login result(ajax 형태) : "+result);
		
		return  result+"";   //ajax - 데이터를 직접 보냄
	}
	
	//로그인 확인 sjp 형태
	@PostMapping("login")
	public String login(MemberDto mdto, Model model) {
		System.out.println("MController login id : "+mdto.getId());
		System.out.println("MController login pw : "+mdto.getPw());
		
		//service 연결 - 1또는 0으로 받음 1-성공 0-실패
		int result = mService.login(mdto);
		
		//model 전달
		model.addAttribute("result",result);
		System.out.println("MController login result(sjp 형태) : "+result);
		return "member/doLogin";  //jsp - model에 넣어서 보냄
	}

}
