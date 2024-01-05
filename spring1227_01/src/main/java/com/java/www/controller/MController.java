package com.java.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.www.dto.MemberDto;
import com.java.www.dto.MemberDto2;
import com.java.www.service.EmailService;
import com.java.www.service.MService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("member")
public class MController {
	
	@Autowired MService mService;
	@Autowired EmailService emailService;
	@Autowired HttpSession session;
	
	@GetMapping("id")
	public String id() {
		return "member/id";
	}
	//아이디 찾기 페이지
	@GetMapping("idsearch")
	public String idsearch() {
		return "member/idsearch";
	}
	
	//아이디 찾기
	@PostMapping("a_idsearch")
	@ResponseBody
	public String a_idsearch(String name, String email) {
		System.out.println("MController idsearch name : "+name);
		System.out.println("MController idsearch email : "+email);
		MemberDto2 mdto2 = mService.a_idsearch(name,email);
		String result = "";
		String tempId = "";  //임시아이디
		
		//찾은 아이디 뒤 두자리에 **로 변경하기.
		if(mdto2 != null) {
			tempId =  mdto2.getId().substring(0,mdto2.getId().length()-2);   //id가 abcde 이면 abc**로 나타남.
			tempId += "**";
			System.out.println("MController idsearch tempId : "+tempId);
			result = tempId;
		}else {
			result = "fail";
		}
		return result;
	}
	
	//비밀번호 찾기
	@PostMapping("pwsearch")
	@ResponseBody
	public String pwsearch(String id, String email) {
		System.out.println("MController pwsearch id : "+id);
		System.out.println("MController pwsearch email : "+email);
		String result = mService.pwsearch(id,email);
		return "success";
	}
	
	@GetMapping("step01")
	public String step01() {
		return "member/step01";
	}
	
	@GetMapping("step02")
	public String step02() {
		return "member/step02";
	}
	
	@GetMapping("step03")
	public String step03() {
		return "member/step03";
	}
	
	//임시비밀번호
	@PostMapping("pwChk")
	@ResponseBody
	public String pwChk(String pwcode) {
		System.out.println("MController pwChk pwcode : "+pwcode);
		String pw = (String)session.getAttribute("email_pwcode");
		String result = "fail";
		if(pw.equals(pwcode)) {
			result = "success";
		}
		return result;
	}
	
	//email 전송
	@PostMapping("email")
	@ResponseBody
	public String email(String email) {
		System.out.println("MController email email : "+email);
		
		//service 연결
		String result = emailService.mailSend(email);
		
		return result;
	}
	
	 //로그인 화면
	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	
	//로그아웃 화면
	@GetMapping("logout")
	public String logout() {
		session.invalidate();
		return "member/logout";
	}
	
	//로그인 확인 ajax 형태
	@PostMapping("ajaxLogin")
	@ResponseBody                  //데이터로 전송
	public String ajaxLogin(MemberDto2 mdto2) {
		System.out.println("MController login id : "+mdto2.getId());
		System.out.println("MController login pw : "+mdto2.getPw());
		
		//service 연결 - 1또는 0으로 받음 1-성공 0-실패
		int result = mService.login(mdto2);
		System.out.println("MController login result(ajax 형태) : "+result);
		
		return  result+"";   //ajax - 데이터를 직접 보냄
	}
	
	//로그인 확인 sjp 형태
	@PostMapping("login")
	public String login(MemberDto2 mdto2, Model model) {
		System.out.println("MController login id : "+mdto2.getId());
		System.out.println("MController login pw : "+mdto2.getPw());
		
		//service 연결 - 1또는 0으로 받음 1-성공 0-실패
		int result = mService.login(mdto2);
		
		//model 전달
		model.addAttribute("result",result);
		System.out.println("MController login result(sjp 형태) : "+result);
		return "member/doLogin";  //jsp - model에 넣어서 보냄
	}
	
	//아이디 중복확인
	@PostMapping("idCheck")
	@ResponseBody                  //데이터로 전송
	public String idCheck(String id) {
		System.out.println("MController idCheck id : "+id);
		String result = "사용가능";
		//service 연결
		result = mService.idCheck(id);
		System.out.println("MController idCheck result : "+result);
		
		return  result;
	}
	
	//회원가입
	@PostMapping("step04")
	public String step04(MemberDto2 mdto2) {
		System.out.println("MController step04 phone : "+mdto2.getPhone());
		return "member/step04";
	}

}
