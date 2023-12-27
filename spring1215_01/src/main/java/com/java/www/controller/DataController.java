package com.java.www.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.plaf.metal.MetalMenuBarUI;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.www.dto.MemberDto;

@Controller
//@RestController  - 들어오면 데이터는 데이터값으로 출력해?
public class DataController {
	
	@ResponseBody
	@RequestMapping("boardBno/{bno}/{bhit}")  //boardBno 뒤쪽에는 bno로 인식하겠다는 뜻
	public String boardBno(@PathVariable int bno, @PathVariable int bhit){//@PathVariable int bno에 {bno}를 넣겠다
		String txt = "DataController 글번호 : "+bno;
		txt += ", 조회수 : "+bhit;
		return txt;
	}
	
	@RequestMapping("mInsert")
	public String mInsert() {
		return "mInsert";
	}
	
	@ResponseBody  //없으면 리턴값이 idCheck.jsp 있으면 데이터
	@RequestMapping("idCheck")
	public Map<String, Object> idCheck(String id) {
		//db검색 select * from member where id=?
		Map<String, Object> map = new HashMap<>();
		ArrayList<MemberDto> list = new ArrayList<>();
		
		if(id.equals("aaa")) {
			map.put("result", "fail");     //사용불가능
		}else {
			map.put("result", "success");  //사용가능
		}
		/* json 포맷 자동 변환 - Map, list
		JSONArray jarr = new JSONArray();
		JSONObject jobj = new JSONObject();
		*/
		list.add(new MemberDto("ccc","2222","베리향","010-1111","male","run.book", new Timestamp(System.currentTimeMillis())));
		
		map.put("name", "홍길동");
		map.put("phone", "010-1111-1111");
		map.put("bno", "1");
		map.put("mdto", new MemberDto("bbb","1111","베리향","010","female","run", new Timestamp(System.currentTimeMillis())));
		
		return map;
	}
	
	@ResponseBody //리턴 값에 들어온 데이터값으로 출력 
	@RequestMapping("bbb")
	public String bbb() {
		String txt = "{'id':'aaa','pw':'1111','name':'홍길동'}";
		return txt;
	}

}
