package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.dto.KakaoDto;
import com.java.dto.TokenDto;

import jakarta.servlet.http.HttpSession;

@Controller
public class FController {
	
@Autowired HttpSession session;
	
	@GetMapping("/")
	public String main() {
		return "main";
	}
	
	@GetMapping("login")
	public String login() {
		return "login";
	}
	
	@GetMapping("kakao/oauth")
	//@ResponseBody
	public String oauth(String code) {
		//---------------------------------------------------------------------------1. 인가 코드 받아오기(get방식)
		System.out.println("인가 코드 받아오기 : "+code);

		//---------------------------------------------------------------------------------2. 토큰 받기(post방식)
		String tokenURL = "https://kauth.kakao.com/oauth/token";
		
		//header
		String Content_type = "application/x-www-form-urlencoded;charset=utf-8";
		//body
		String grant_type = "authorization_code";
		String client_id = "24a08db200fb154e939f596f923fc142";
		String redirect_uri = "http://localhost:8000/kakao/oauth";
		
		//URL 전송
		RestTemplate rt = new RestTemplate();
		//header 생성하기
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", Content_type);
		//body 생성하기
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", grant_type);
		params.add("client_id", client_id);
		params.add("redirect_uri", redirect_uri);
		params.add("code", code);
		
		//header랑 body 합치기
		HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<>(params, headers);
		
		//URL, 전송방식 : post, 데이터, String타입
		ResponseEntity<String> response = rt.exchange(tokenURL, HttpMethod.POST, tokenRequest, String.class);
		System.out.println("토큰요청값 response : "+response);
		System.out.println("토큰요청값 body : "+response.getBody());  //response{}안 내용이 body
		
		//json데이터를 java객체로 변경
		ObjectMapper objectMapper = new ObjectMapper();
		TokenDto tokenDto = null;
		try {
			tokenDto = objectMapper.readValue(response.getBody(), TokenDto.class);
		} catch (Exception e) {e.printStackTrace();}
		System.out.println("토큰키 값 TokenDto Access_token : "+tokenDto.getAccess_token());
		
		//---------------------------------------------------------3. 토큰를 전송해서 사용자 정보 받아오기(post,get방식)
		String tokenURL_user = "https://kapi.kakao.com/v2/user/me";
		
		//header
		Content_type = "application/x-www-form-urlencoded;charset=utf-8";
		String authorization = "Bearer "+tokenDto.getAccess_token();
		
		//URL 전송
		RestTemplate rt_user = new RestTemplate();
		//header 생성하기
		HttpHeaders headers_user = new HttpHeaders();
		headers_user.add("Content-type", Content_type);
		headers_user.add("Authorization", authorization);
		
		//header랑 body 합치기
		HttpEntity<MultiValueMap<String, String>> tokenRequest_user = new HttpEntity<>(headers_user);
		
		//URL, 전송방식 : post, 데이터, String타입
		ResponseEntity<String> response_user = rt_user.exchange(tokenURL_user, HttpMethod.POST, tokenRequest_user, String.class);
		System.out.println("토큰요청값 response_user : "+response_user);
		System.out.println("토큰요청값 response_user body : "+response_user.getBody());  //response{}안 내용이 body
		
		//json데이터를 java객체로 변환
		ObjectMapper objectMapper_user = new ObjectMapper();
		KakaoDto kakaoDto = null;
		try {
			kakaoDto = objectMapper_user.readValue(response_user.getBody(), KakaoDto.class);
		} catch (Exception e) {e.printStackTrace();}
		System.out.println("토큰키 값 kakaoDto id : "+kakaoDto.getId());
		System.out.println("토큰키 값 kakaoDto name : "+kakaoDto.getProperties().nickname);
		
		String targetUrl = "";
		if(kakaoDto != null) {
			System.out.println("카카오 로그인이 완료되었습니다.");
			session.setAttribute("session_id", kakaoDto.getId());
			session.setAttribute("session_name", kakaoDto.getProperties().nickname);
			targetUrl = "redirect:/";
		}else {
			System.out.println("카카오 로그인 에러입니다.");
			targetUrl = "login";
		}
		
		return targetUrl;
	}
	
	

}
