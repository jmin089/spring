package com.java.www.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.java.www.dto.BoardDto;
import com.java.www.service.BService;

@Controller
@RequestMapping("board")
public class BoardController {
	
	@Autowired
	BService bService;
	
	//게시글 검색
	@GetMapping("search")
	public String search(@RequestParam(defaultValue = "1")int page, @RequestParam(required = false)String category,
			@RequestParam(required = false)String searchWord, Model model) {
		System.out.println("BoardController search category : "+category);
		System.out.println("BoardController search searchWord : "+searchWord);
		
		//db에서 가져오기 
		Map<String, Object> map = bService.selectSearch(page, category,	searchWord); //model 저장 model.addAttribute("map",map);
		System.out.println("BoardController search countAll : "+(int)map.get("countAll"));
		model.addAttribute("map",map);
		return "board/bList";
	}
	
	//게시글 전체 가져오기
	@GetMapping("bList")
	public String bList(@RequestParam(defaultValue = "1")int page, @RequestParam(required = false)String category,
			@RequestParam(required = false)String searchWord, Model model) {
		//db에서 가져오기
		Map<String, Object> map = bService.selectAll(page, category, searchWord);
		//model 저장
		model.addAttribute("map",map);
		System.out.println("BoardController bList countAll : "+(int)map.get("countAll"));
		return "board/bList";
	}
	
	//게시글 1개 가져오기
	@GetMapping("bView")
	public String bView(@RequestParam(defaultValue = "1") int bno, Model model) {
		//db에서 가져오기
		Map<String, Object> map = bService.selectOne(bno);
		//model 저장
		model.addAttribute("map",map);
		return "board/bView";
	}
	
	//글쓰기 화면
	@GetMapping("bInsert")
	public String bInsert() {
		return "board/bInsert";
	}
	
	//글쓰기 저장 - 오버로딩
	@PostMapping("bInsert")
	public String bInsert(BoardDto bdto, @RequestPart MultipartFile files, Model model) throws Exception {
		//MultipartFile files - input의 type="file" name="files" 에 있는 name을 가져옴.
		
		//isEmpty : 파일 첨부를 했냐 안했냐 물어볼때 사용
		if(!files.isEmpty()) {                         //파일첨부가 있으면
		String orgName = files.getOriginalFilename();  //파일첨부의 파일이름
		System.out.println("BoardController 파일이름 : "+orgName);
		long time = System.currentTimeMillis();
		String newName = time+"_"+orgName;             //중복방지를 위해 새로운 이름으로 변경
		String upload = "C:/upload/";                  //파일업로드 위치
		File f = new File(upload+newName);
		files.transferTo(f);                           //파일을 저장위치에 저장시킴.
		bdto.setBfile(newName);                        //파일이름을 BoardDto에 저장시킴.
		}else {                                        //파일첨부가 없으면
			bdto.setBfile("");
			System.out.println("파일첨부가 없습니다.");
		}
		//db로 전송
		bService.bInsert(bdto);
		return "board/doBInsert";
	}
	
	//다중업로드 화면보기
	@GetMapping("bInsert2")
	public String bInsert2() {
		return "board/bInsert2";
	}
	
	//다중업로드 저장
	@PostMapping("bInsert2")
	public String bInsert2(BoardDto bdto, List<MultipartFile> files, Model model) throws Exception {
		//MultipartFile files - input의 type="file" name="files" 에 있는 name을 가져옴. 
		//복수개 일때는 List<MultipartFile> files로 받음.
		//List<MultipartFile> files = ArrayList<MultipartFile> list;
		//for(int i=0;i<files.size();i++) {} / for(MultipartFile file:files) {} 둘중 하나 사용 같은말임.
		
		String orgName = "";
		String newName = "";
		String mergeName = "";
		int i = 0;
		for(MultipartFile file:files) {
			//파일첨부하기.
			orgName = file.getOriginalFilename();          //파일첨부의 파일이름
			System.out.println("BoardController 파일이름 : "+orgName);
			long time = System.currentTimeMillis();
			newName += time+"_"+orgName;                   //중복방지를 위해 새로운 이름으로 변경
			String upload = "C:/upload/";                  //파일업로드 위치
			File f = new File(upload+newName);
			file.transferTo(f);                           //파일을 저장위치에 저장시킴.
			
			//파일이름저장하기.
			if(i==0) mergeName += time+"_"+orgName;           
			else mergeName += ","+time+"_"+orgName; 
			i++;
		}
		bdto.setBfile(mergeName);                        //파일이름을 BoardDto에 저장시킴.
		System.out.println("BoardController 최종 파일이름 : "+mergeName);
		
		//db연결 - 내용저장
		bService.bInsert(bdto);
		
		return "board/bInsert2";
	}
	
	//다중업로드 리스트
	@GetMapping("bList2")
	public String bList2(@RequestParam(defaultValue = "1")int page, @RequestParam(required = false)String category,
			@RequestParam(required = false)String searchWord, Model model) {
		//db에서 가져오기
		Map<String, Object> map = bService.selectAll(page, category, searchWord);
		//model 저장
		model.addAttribute("map",map);
		System.out.println("BoardController bList2 countAll : "+(int)map.get("countAll"));
		return "board/bList2";
	}
	
	//다중업로드 보기
	@GetMapping("bView2")
	public String bView2(@RequestParam(defaultValue = "1") int bno, Model model) {
		//db에서 가져오기
		Map<String, Object> map = bService.selectOne(bno);
		//model 저장
		model.addAttribute("map",map);
		return "board/bView2";
	}
	
	//게시글 삭제
	@PostMapping("bDelete")
	public String bDelete(@RequestParam(defaultValue = "1")int bno) {
		System.out.println("BoardController bDelete bno : "+bno);
		bService.bDelete(bno);
		return "board/bDelete";
	}
	
	//게시글 수정페이지 보기
	@PostMapping("bUpdate")
	public String bUpdate(@RequestParam(defaultValue = "1")int bno, Model model) {
		System.out.println("BoardController bDelete bno : "+bno);
		Map<String, Object> map = bService.selectOne(bno);  //게시글 1개 가져오기
		model.addAttribute("map",map);
		return "board/bUpdate";
	}
	
	//게시글 수정 저장
	@PostMapping("doBUpdate")
	public String doBUpdate(BoardDto bdto, @RequestPart MultipartFile files) throws Exception {
		//bdto -> bfile, bno 추가되어 있음 (hidden 해준거??)
		System.out.println("BoardController doBUpdate bno : "+bdto.getBno());
		String orgName = "";
		String newName = "";
		if(!files.isEmpty()) {                  //파일업로드가 되어 있으면
			orgName = files.getOriginalFilename();
			long time = System.currentTimeMillis();
			/*	newName = time+"_"+orgName;     둘다 사용가능(같은결과임)
			newName = String.format("%s_%d", orgName,time);		*/
			newName = time+"_"+orgName;
			String upload = "C:/upload/";       //파일저장위치
			File f = new File(upload+newName);  //파일생성
			files.transferTo(f);                //파일전송
			bdto.setBfile(newName);             //bdto파일이름 저장
		}
		//db전송
		bService.doBUpdate(bdto);               //파일업로드가 없으면 기존파일이름 그대로 사용
		return "board/doBUpdate";
	}
	
	//답변달기 페이지 보기
	@PostMapping("bReply")
	public String bReply(@RequestParam(defaultValue = "1")int bno, Model model) {
		System.out.println("BoardController bReply bno : "+bno);
		Map<String, Object> map = bService.selectOne(bno);  //게시글 1개 가져오기
		model.addAttribute("map",map);
		return "board/bReply";
	}
	
	//답변달기 저장
	@PostMapping("doBReply")
	public String doBReply(BoardDto bdto, @RequestPart MultipartFile files, Model model) throws Exception {
		//답변달기 - bgroup, bstep, bindent 값은 bdto에 담겨져 있음.
		
		if(!files.isEmpty()) {                         //파일첨부가 있으면
		String orgName = files.getOriginalFilename();  //파일첨부의 파일이름
		System.out.println("BoardController doBReply 파일이름 : "+orgName);
		long time = System.currentTimeMillis();
		String newName = time+"_"+orgName;             //중복방지를 위해 새로운 이름으로 변경
		String upload = "C:/upload/";                  //파일업로드 위치
		File f = new File(upload+newName);
		files.transferTo(f);                           //파일을 저장위치에 저장시킴.
		bdto.setBfile(newName);                        //파일이름을 BoardDto에 저장시킴.
		}else {                                        //파일첨부가 없으면
			bdto.setBfile("");
			System.out.println("doBReply 파일첨부가 없습니다.");
		}
		//db로 전송
		bService.doBReply(bdto);
		return "board/doBReply";
	}

}
