package com.java.www.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.java.www.dto.EmpDeptDto;
import com.java.www.dto.EmpDto;
import com.java.www.dto.MemBoardDto;
import com.java.www.service.EService;

@Controller
public class FController {
	
	@Autowired EService eService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	//사원정보 가져오기
	@GetMapping("list")
	public String list(Model model) {
		ArrayList<EmpDto> list = eService.selectAll();
		model.addAttribute("list",list);
		return "list";
	}
	
	//사원정보 가져오기2
	@GetMapping("list2")
	public String list2(Model model) {
		ArrayList<EmpDeptDto> list = eService.selectAll2();
		model.addAttribute("list",list);
		return "list2";
	}
	
	//회원 게시글
	@GetMapping("list3")
	public String list3(Model model) {
		ArrayList<MemBoardDto> list = eService.MBSelect();
		model.addAttribute("list",list);
		return "list3";
	}

}
