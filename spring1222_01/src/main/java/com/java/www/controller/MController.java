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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("member")
public class MController {
	
	@Autowired MService mService;
	@Autowired HttpSession session;
	
	//로그인 페이지
	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	
	//로그인 확인
	@PostMapping("login")
	public String login(MemberDto mdto, Model model, HttpServletRequest request) {
		int result = 0;
		MemberDto memberDto = mService.login(mdto);
		if(memberDto != null) {
			session.setAttribute("session_id", memberDto.getId());
			session.setAttribute("session_name", memberDto.getName());
			System.out.println("MController login id : "+memberDto.getId());
			result = 1;
		}
		model.addAttribute("result",result);
		return "member/doLogin";
	}
	
	@GetMapping("join01")
	public String join01() {
		return "member/join01";
	}
	
	@PostMapping("join02")
	public String join02() {
		return "member/join02";
	}
	
	//ajax 아이디 체크
	@PostMapping("idCheck")
	@ResponseBody
	public String idCheck(String id) {
		System.out.println("MController idCheck id : "+id);
		String result = "사용가능";
		//db접근
		result = mService.idCheck(id);
		return result;
	}
	
	@PostMapping("mInsert")
	@ResponseBody
	public String mInsert(MemberDto mdto, String f_tell, String m_tell, String l_tell) {
		String phone = f_tell+"-"+m_tell+"-"+l_tell;
		mdto.setPhone(phone);
		String result = mService.mInsert(mdto);
		System.out.println("MController mInsert result : "+result);
		return result;
	}
	
	@PostMapping("join03")
	public String join03() {
		return "member/join03";
	}

}
