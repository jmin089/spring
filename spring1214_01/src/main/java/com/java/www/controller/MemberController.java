package com.java.www.controller;

import java.sql.Timestamp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.www.dto.BoardDto;
import com.java.www.dto.MemberDto;


@Controller
@RequestMapping("member")
public class MemberController {

	//---------------------------------------------------------------------- 회원정보보기
	@RequestMapping("mInsert")
	public String mInsert() {
		return "member/memberInsert";
	}
	
	@RequestMapping("doMInsert")
	public String doMInsert(MemberDto memberDto, Model model) {
		//1. HttpServletRequest request로 String id = request.getParameter("id");
		//2. @RequestParam String id
		//3. 변수로 받는 방법
		//4. 객체로 받는 방법 - MemberDto memberDto
		model.addAttribute("memberDto",memberDto);
		System.out.println("MemberController hobby : "+memberDto.getHobby());
		return "member/memberView";
	}
		
	//---------------------------------------------------------------------- 회원정보수정
	@RequestMapping("mUpdate")
	public String mUpdate(MemberDto memberDto, Model model) {
		model.addAttribute("memberDto",memberDto);
		return "member/memberUpdate";
	}
	
	@RequestMapping("mView")
	public String mView(MemberDto memberDto, Model model) {
		model.addAttribute("memberDto",memberDto);
		return "member/memberView";
	}
	
	//---------------------------------------------------------------------- 로그인
	@RequestMapping("login")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("doLogin")
	//형변환은 알아서 해주지만 대신 이름이 동일해야함 동일하지 않으면 안돼!!
	//int - @RequestParam(defaultValue = "0") 입력값이 없으면 0으로 출력해주라~
	//public String doLogin(@RequestParam(required = false) String id, String pw, @RequestParam(defaultValue = "0")int bno, HttpServletRequest request) {
	public String doLogin(MemberDto memberDto, Model model, BoardDto boardDto) {
		System.out.println("FController id : "+memberDto.getId());
		System.out.println("FController pw : "+memberDto.getPw());
		System.out.println("FController bno : "+boardDto.getBno());

		String id = memberDto.getId();
		String pw = memberDto.getPw();
		int bno = boardDto.getBno();
		
		//기본생성자
		MemberDto mdto = new MemberDto();
		//mdto.setId(id);
		System.out.println("FController mdto.getId : "+mdto.getId());
		
		Timestamp mdate = new Timestamp(System.currentTimeMillis());
		
		//전체생성자
		MemberDto mdto2 = new MemberDto(id, pw, "홍길동", "010-1233-1234", "male", "run", mdate);
		System.out.println("FController mdto2.getId : "+mdto2.getId());
		
		//부분생성자
		MemberDto mdto3 = MemberDto.builder().id(id).pw(pw).name("베리향").gender("female").build();
		System.out.println("FController mdto3.name : "+mdto3.getName());
		
		//Model
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		model.addAttribute("bno", bno);
		model.addAttribute("mdto", memberDto);  //객체를 태울수도있고 변수를 태울수도있다
		
		return "doLogin";
	}
}
