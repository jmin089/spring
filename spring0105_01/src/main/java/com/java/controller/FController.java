package com.java.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
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

import jakarta.servlet.http.HttpSession;

@Controller
public class FController {
	
	@Autowired BService bService;
	@Autowired HttpSession session;
	
	@GetMapping({"/", "main"})
	public String main() {
		return "main";
	}
	
	//전체 게시글 가져오기
	@GetMapping("blist")
	public String blist(Model model) {
		List<BoardDto> list = bService.selectAll();
		model.addAttribute("list",list);
		return "blist";
	}
	
	//1개 게시글 가져오기
	@GetMapping("bview")
	public String bview(@RequestParam(defaultValue = "1")int bno, Model model) {
		BoardDto bdto = bService.selectOne(bno);
		model.addAttribute("bdto",bdto);
		return "bview";
	}
	
	@GetMapping("bwrite")
	public String bwrite() {
		return "bwrite";
	}
	
	//게시글 쓰기 저장
	@PostMapping("bwrite")
	public String bwrite(BoardDto bdto, @RequestPart MultipartFile file, Model model) throws Exception {
		//파일 서버로 전송하는 부분
		if(!file.isEmpty()) {
			String oriFileName = file.getOriginalFilename();
			long time = System.currentTimeMillis();
			String uploadFileName = time +"_"+ oriFileName;   //파일 이름 변경
			String uploadUrl = "c:/upload/"; 
			File f = new File(uploadUrl+uploadFileName);      //파일 등록
			file.transferTo(f);                               //파일 서버로 전송
			bdto.setBfile(uploadFileName);                    //dto bfile 이름저장
		}else {
			bdto.setBfile("");                                //dto bfile 이름저장
		}
		//service 연결 - 글쓰기 저장
		bService.bwrite(bdto);
		model.addAttribute("result", "success-bwrite");
		return "result";
	}

	//summernote에서 ajax이미지 전송
	@PostMapping("uploadImage")
	@ResponseBody
	public String uploadImage(@RequestPart MultipartFile file) throws Exception {
		String urlName = "";
		//파일 서버로 전송하는 부분
		if(!file.isEmpty()) {
			String oriFileName = file.getOriginalFilename();
			long time = System.currentTimeMillis();
			String uploadFileName = time +"_"+ oriFileName;   //파일 이름 변경
			String uploadUrl = "c:/upload/"; 
			File f = new File(uploadUrl+uploadFileName);      //파일 등록
			file.transferTo(f);                               //파일 서버로 전송
			urlName = "/upload/"+uploadFileName;
		}
		return urlName;
	}
	
	//다음지도보기
	@GetMapping("map")
	public String map() {
		return "map";
	}
	
	//--------------------------------------------------------------------------------영화정보 : 일별 박스오피스
		
	@PostMapping("screenInfo")
	@ResponseBody
	public String screenInfo(String movie) throws Exception {
		System.out.println("FController screenInfo movie : "+movie);
		String key = "4abac8e838c9aa5d92eb4072e9e0fb9d";
		//오늘날짜
		System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(System.currentTimeMillis());
		System.out.println("FController screenInfo today : "+today);
		
		//영화정보 소스 추가 시작-------------------------------------------
		StringBuilder urlBuilder = new StringBuilder("https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("key","UTF-8") + "="+key); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("targetDt","UTF-8") + "=" + URLEncoder.encode("20240104", "UTF-8")); /*페이지번호*/
        URL url = new URL(urlBuilder.toString());
        //URL url = new URL("https://apis.data.go.kr/B551011/PhotoGalleryService1/galleryList1?serviceKey=0WN5%2BHmRuG0HbewojnWiSwiy8tpdabObcnijXjocSeAz%2B7x%2FzShBZ8URBaiRmN8yZ1ZKoUDMPIskxGDNBwswlA%3D%3D&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&arrange=A&_type=json");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder(); //String을 계속 더하면 String변수를 계속 새롭게 만듬.
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);  //json데이터를 sb에 1줄씩 저장 
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
        //-------------------------------------------영화정보 소스 추가 끝
		return sb.toString();
		
	}
		
	
}
