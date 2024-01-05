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
	
	//로그인 페이지 화면
	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	
	//로그아웃
	@GetMapping("logout")
	public String logout() {
		session.invalidate();
		return "member/logout";
	}
	
	//jsp 로그인 확인
	@PostMapping("login")
	public String login(MemberDto mdto, Model model) {
		int result = 0;
		//Service 연결
		MemberDto memberDto = mService.login(mdto);
		if(memberDto != null) {
			session.setAttribute("session_id", memberDto.getId());
			session.setAttribute("session_name", memberDto.getName());
			System.out.println("MController login id : "+memberDto.getId());
			result = 1;
		}
		//db연결
		model.addAttribute("result",result);
		return "member/doLogin";
	}
	
	//ajax 로그인 확인
	@PostMapping("a_login")
	@ResponseBody
	public String a_login(MemberDto mdto) {
		System.out.println("MController a_login id : "+mdto.getId());
		//Service 연결
		int result = mService.a_login(mdto);
		/*result 타입이 String 
		String result = mService.a_login(mdto);	*/
			
		return result+"";
	}
	
	//회원가입 화면
	@GetMapping("step03")
	public String step03() {
		return "member/step03";
	}
	
	//회원가입 저장
	@PostMapping("step03")
	public String step03(MemberDto mdto, String phone_a, String phone_b, String phone_c, Model model) {
		System.out.println("MController step03 id : "+mdto.getId());
		String phone = phone_a+"-"+phone_b+"-"+phone_c;
		mdto.setPhone(phone);
		mService.memInsert(mdto);
		return "member/step04";
	}

}
