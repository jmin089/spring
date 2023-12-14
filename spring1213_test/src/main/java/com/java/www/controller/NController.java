package com.java.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("notice")
public class NController {
	
	@RequestMapping("noticeList")
	public String noticeList() {
		return "notice/noticeList";
	}
	@RequestMapping("noticeInsert")
	public String noticeInsert() {
		return "notice/noticeInsert";
	}
	@RequestMapping("noticeView")
	public String noticeView() {
		return "notice/noticeView";
	}

}
