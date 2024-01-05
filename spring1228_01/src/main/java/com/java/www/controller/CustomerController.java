package com.java.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("customer")
public class CustomerController {
	
	@GetMapping("notice")
	public String notice() {
		return "customer/notice";
	}
	
	@GetMapping("notice_view")
	public String notice_view() {
		return "customer/notice_view";
	}
	
	@GetMapping("notice_write")
	public String notice_write() {
		return "customer/notice_write";
	}

}
