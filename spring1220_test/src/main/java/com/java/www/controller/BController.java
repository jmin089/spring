package com.java.www.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.java.www.dto.BoardDto;
import com.java.www.service.BService;


@Controller
public class BController {
	
	@Autowired BService bService;
	
	//전체글 가져오기
	@RequestMapping("bList")
	public String bList(Model model) {
		ArrayList<BoardDto> list = bService.selectAll();
		model.addAttribute("list",list);
		return "bList";
	}
	
	//게시글 1개 가져오기 : 현재글, 이전글, 다음글
	@RequestMapping("bView")
	public String bView(@RequestParam(defaultValue = "1") int bno, Model model) {
		Map<String, Object> map = bService.selectOne(bno);
		model.addAttribute("map",map);
		return "bView";
	}
	
	//게시글 쓰기 화면
	@RequestMapping("bInsert")
	public String bInsert() {
		return "bInsert";
	}
	//게시글 쓰기 저장
	@PostMapping("bInsert")
	public String bInsert(BoardDto bdto, @RequestPart MultipartFile files, Model model) throws Exception {
		String oName = "";
		String nName = "";
		if(!files.isEmpty()) {
			oName = files.getOriginalFilename();
			long time = System.currentTimeMillis();
			nName = time+"_"+oName;
			String upload = "C:/upload/";
			File f = new File(upload+nName);
			files.transferTo(f);
			bdto.setBfile(nName);
		}else {
			bdto.setBfile("");
			System.out.println("파일이 없음");
		}
		bService.bInsert(bdto);	
		return "doBInsert";
	}
	
	//게시글 삭제하기
	@RequestMapping("bDelete")
	public String bDelete(@RequestParam(defaultValue = "1") int bno) {
		bService.bDelete(bno);
		return "bDelete";
	}

	//게시글 수정 화면
	@RequestMapping("bUpdate")
	public String bUpdate(@RequestParam(defaultValue = "1") int bno, Model model) {
		Map<String, Object> map = bService.selectOne(bno);
		model.addAttribute("map",map);
		return "bUpdate";
	}
	
	//게시글 수정 저장
	@PostMapping("doBUpdate")
	public String doBUpdate(BoardDto bdto, @RequestPart MultipartFile files) throws Exception {
		String oName = "";
		String nName = "";
		if(!files.isEmpty()) {
			oName = files.getOriginalFilename();
			long time = System.currentTimeMillis();
			nName = time+"_"+oName;
			String upload = "C:/upload/";
			File f = new File(upload+nName);
			files.transferTo(f);
			bdto.setBfile(nName);
		}
		
		bService.doBUpdate(bdto);
		return "doBUpdate";
	}

}
