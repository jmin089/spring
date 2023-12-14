package com.java.www.controller;

import java.sql.Timestamp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.www.dto.MemberDto;

@Controller
public class MController {
	
	@RequestMapping("member/memberInsert")
	public String memberInsert(Model model) {
		Timestamp mdate = new Timestamp(System.currentTimeMillis());
		MemberDto mdto = new MemberDto("abc", "1111", "박카스", "010-1234-1235", "famale", "run", mdate);
		model.addAttribute("mdto", mdto);
		return "member/memberInsert";
	}
	
}
