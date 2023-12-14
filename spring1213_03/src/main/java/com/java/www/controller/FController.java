package com.java.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  //jsp페이지를 열어주라
public class FController {
	
	@GetMapping("/")    //localhost8000 : /
	public String index() {
		return "index";
	}
}
