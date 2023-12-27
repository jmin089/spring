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
	
	//로그인 페이지
	@RequestMapping("login")
	public String login() {
		return "member/login";
	}
		
	//로그인 아이디 패스워드
	@PostMapping("login")
	public String login(String id, String pw, Model model) {
		int result = mService.login(id,pw);
		model.addAttribute("result",result);
		return "member/doLogin";
	}
	
	//로그아웃
	@RequestMapping("logout")
	public String logout() {
		session.invalidate();
		return "member/logout";
	}
	
	//회원가입
	@RequestMapping("mInsert")
	public String mInsert() {
		return "member/mInsert";
	}
	
	//회원가입 저장
	@PostMapping("mInsert")
	@ResponseBody
	public String mInsert(MemberDto mdto) {
		System.out.println("MController mInsert id : "+mdto.getId());
		System.out.println("MController mInsert gender : "+mdto.getGender());
		//db 전송
		String result = mService.mInsert(mdto);
		System.out.println("MController mInsert result : "+result);
		return result;
	}
	
	//ajax 로그인 아이디 체크
	@ResponseBody             //리턴을 jsp페이지가 아니라 data로 넘겨줌.
	@PostMapping("idCheck")   //ajax 로그인 아이디 체크
	public String idCheck(String id) {
		System.out.println("MController idCheck id : "+id);
		//db 전송
		String result = mService.idCheck(id);
		return result;
	}

}
