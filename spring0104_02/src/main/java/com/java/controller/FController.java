package com.java.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.java.dto.BoardDto;
import com.java.service.BService;

@Controller
public class FController {
	
	@Autowired BService bService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	//전체 게시글 가져오기
	@GetMapping("blist")
	public String blist(Model model) {
		List<BoardDto> list = bService.selectAll();
		model.addAttribute("list",list);
		System.out.println("FController blist list : "+list);
		return "blist";
	}
	
	//1개 게시글 가져오기
	@GetMapping("bview")
	public String bview(@RequestParam(defaultValue = "1")int bno, Model model) {
		System.out.println("FController bview bno : "+bno);
		BoardDto bdto = bService.selectOne(bno);
		model.addAttribute("bdto",bdto);
		return "bview";
	}
	
	@GetMapping("bwrite")
	public String bwrite() {
		return "bwrite";
	}
	
	//내용부분에 이미지를 추가시 호출
	@PostMapping("summernoteUpload")
	@ResponseBody
	public String summernoteUpload(@RequestPart MultipartFile file) throws Exception {
		String urllink = "";
		if(!file.isEmpty()) {
			String o_Name = file.getOriginalFilename();
			long time = System.currentTimeMillis();
			String u_Name = time +"_"+ o_Name;
			String Fupload = "c:/upload/";
			
			File f = new File(Fupload + u_Name);
			file.transferTo(f);
			
			urllink = "/upload/" + u_Name;
			System.out.println("FController summernoteUpload urllink : "+urllink);
		}
		return urllink;
	}
	
	//submit버튼 클릭시 파일 업로드 - 게시글저장
	@PostMapping("bwrite")
	public String bwrite(BoardDto bdto, @RequestPart MultipartFile file, Model model) {
		System.out.println("FController bwrite btitle : "+bdto.getBtitle());
		if(!file.isEmpty()) {
			String o_Name = file.getOriginalFilename();
			long time = System.currentTimeMillis();
			String u_Name = time +"_"+ o_Name;
			String Fupload = "c:/upload/";
			
			bdto.setBfile(u_Name);
		}else {
			bdto.setBfile("");
		}
		System.out.println("파일첨부 이름 : "+bdto.getBfile());
		
		bService.bwrite(bdto);
		model.addAttribute("result","write-s");
		return "dobwrite";
	}

}
