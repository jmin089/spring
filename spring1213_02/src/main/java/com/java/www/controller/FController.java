package com.java.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.java.www.service.PService;

@Controller
public class FController {
	
	@Autowired  //필요한 의존 객체의 “타입"에 해당하는 빈을 찾아 주입한다.
	PService pService;
	
	//페이지에 연결하는 방법
	
	@GetMapping("/") //url에 @GetMapping - 노출 @PostMapping - 숨김 @RequestMapping - 모두
	public String index() {
		pService.execute();
		return "index";
	}
	
	@GetMapping("list")
	public String list() {
		return "list";
	}
	
	@GetMapping("mwrite")
	public String mwrite() {
		return "mwrite";
	}
	
	//---------------------------------------------페이지에 연결하는 방법

}
