package com.java.www.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.java.www.dto.BoardDto;
import com.java.www.service.BService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class BController {
	
	@Autowired BService bService;
	
	@GetMapping("bList")
	public String bList(Model model) {
		ArrayList<BoardDto> list = bService.selectAll();
		model.addAttribute("list",list);
		return "bList";
	}
	
	@GetMapping("bView")
	public String bView(@RequestParam(defaultValue = "1") int bno, Model model) {
		Map<String, Object> map = bService.selectOne(bno);
		model.addAttribute("map",map);
		return "bView";
	}

}
