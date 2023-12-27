package com.java.www.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.www.dto.BoardDto;
import com.java.www.service.BoardService;

@Controller
@RequestMapping("board")
public class BController {
	
	@Autowired
	 BoardService bService;
	
	//전체글
	@RequestMapping("bList")
	public String bList(Model model){
		ArrayList<BoardDto> list = bService.BListAll();
		model.addAttribute("list",list);
		return "board/bList";
	}
	

}
