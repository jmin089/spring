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
@RequestMapping("member")
public class MController {
	
	@Autowired  //IOC컨테이너에 있는 객체 가져오기(자동객체선언)
	MService mService;
	
	@Autowired
	HttpSession session;
	/*	HttpSession session = request.getSession();
		session.setAttribute("session_id", mdto.getId());	
		spring 전에는 위 방법을 사용했는데 spring에서는 autowired를 사용*/
	
	@RequestMapping("login")
	public String login() {
		return "member/login";
	}
	
	@RequestMapping("logout")
	public String logout() {
		session.invalidate();
		return "member/logout";
	}
	
	@RequestMapping("doLogin")
	public String doLogin(MemberDto mdto, Model model, HttpServletRequest request) {
		int result = 0;
		System.out.println("MController id : "+mdto.getId());
		System.out.println("MController pw : "+mdto.getPw());
		//db연결 - dto가 있는지 확인(null값인지) 리턴값 dto 
		MemberDto memberDto = mService.loginSelect(mdto);  //mdto에는 id, pw 있음.
		if(memberDto!=null) {
			session.setAttribute("session_id", memberDto.getId());
			session.setAttribute("session_name", memberDto.getName());
			System.out.println("MController id 있음 : "+memberDto.getId());
			result = 1;
		}else {
			System.out.println("MController memberDto : null");
		}
		
		//데이터를 jsp페이지로 보냄.   - result가 1이면 성공, 0이면 실패
		model.addAttribute("result",result);
		return "member/doLogin";
	}

}
