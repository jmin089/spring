package com.java.www.controller;

import java.sql.Timestamp;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.java.www.dto.BoardDto;
import com.java.www.dto.MemberDto;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MController {

	// index -> memberInsert(get) -> doMemberInsert(post)  ------------------------------------
	@GetMapping("member/memberInsert")
		public String memberInsert(Model model) {
			return "member/memberInsert";
		}
	@PostMapping("member/doMemberInsert")
	public String doMemberInsert(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String gender = request.getParameter("gender");
		String[] hobbys = request.getParameterValues("hobby");
		System.out.printf("%s,%s,%s,%s,%s,%s",id,pw,name,phone,gender,Arrays.toString(hobbys));
		return "member/memberView";
	}
	// ------------------------------------  index -> memberInsert(get) -> doMemberInsert(post)
	
	// index -> login(get) -> doLogin(post)  ------------------------------------
		@GetMapping("member/login")
			public String login(Model model) {
				return "member/login";
			}
		@PostMapping("member/doLogin")
		//@RequestParam 이름만 동일하면 알아서 형변환 해줌.
		public String doLogin(String id, String pw,	@RequestParam(defaultValue = "1") int bno, Model model) {
			System.out.println("MController id : "+id);
			System.out.println("MController pw : "+pw);
			System.out.println("MController bno : "+bno);
			return "/index";
		}
		
	// ------------------------------------  index -> login(get) -> doLogin(post)
	
	@RequestMapping("member/memberUpdate")
	public String memberUpdate(Model model) {
		//id를 전달
		//String id = "admin";
		//model.addAttribute("id",id);
		
		//현재 날짜와 시간을 Timestamp파일에 저장
		Timestamp mdate = new Timestamp(System.currentTimeMillis());
		MemberDto mdto = new MemberDto("aaa", "1111", "홍길동", "010-1234-1234", "male", "game, golf", mdate);
		
		mdto.setMdate(null);
		model.addAttribute("mdto",mdto);
		
		/*	이전 방법
		HttpServletRequest request;
		request.setAttribute("id", id);	*/
	
		return "member/memberUpdate";
		
		
		/*	ModelAndView 사용법 ------------------------------------------
		
		public ModelAndView memberInsert() {
		ModelAndView mv = new ModelAndView();
		//id를 전달
		String id = "admin";
		// spring 방법
		
		//현재 날짜와 시간을 Timestamp파일에 저장
		Timestamp mdate = new Timestamp(System.currentTimeMillis());
		MemberDto mdto = new MemberDto("bbb", "1111", "유관순", "010-1234-1234", "male", "game, golf", mdate);
		
		mdto.setMdate(null);
		//model.addAttribute("id",id);
		mv.addObject("mdto",mdto);
		mv.setViewName("member/memberInsert");
		return mv;
		------------------------------------------ ModelAndView 사용법	*/
	}
	
}
