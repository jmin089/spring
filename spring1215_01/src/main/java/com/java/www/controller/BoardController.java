package com.java.www.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.www.dto.BoardDto;
import com.java.www.service.BService;
import com.java.www.service.BServiceImpl;

@Controller
public class BoardController {
	
	@Autowired
	BService bservice;

	@RequestMapping("bList")
	public String bList(Model model) {
		
		ArrayList<BoardDto> list = bservice.selectAll();
		model.addAttribute("list",list);
		return "bList";
	}
	
}
