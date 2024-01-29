package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.dto.KakaoDto;
import com.java.dto.LogoutDto;
import com.java.dto.TokenDto;

import jakarta.servlet.http.HttpSession;

@Controller
public class FContrller {
	
	@Autowired HttpSession session;
	
	@GetMapping("/")
	public String main() {
		return "main";
	}
	
	@GetMapping("login")
	public String login() {
		return "login";
	}
	
	//카카오 로그아웃
	@GetMapping("logout")
	public String logout(Model model) {
		//access_token 가져오기
		System.out.println("logout session_token"+session.getAttribute("session_token"));
		
		//logout 토큰키 전송
		String logoutUrl = "https://kapi.kakao.com/v1/user/logout";
		
		//header에 넣어야함.
		String Content_type = "application/x-www-form-urlencoded;charset=utf-8";
		String authorization = "Bearer "+session.getAttribute("session_token");
		
		//URL 전송
		RestTemplate rt = new RestTemplate();
		//header 생성하기
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", Content_type);
		headers.add("Authorization", authorization);
		
		//header+body 합치기
		HttpEntity<MultiValueMap<String, String>> logoutRequest = new HttpEntity<>(headers);
		
		//URL, 전송방식 : post, 데이터, String타입
		ResponseEntity<String> response = rt.exchange(logoutUrl, HttpMethod.POST,logoutRequest,String.class);
		System.out.println("로그아웃 요청값 response : "+response);
		System.out.println("로그아웃 요청값 body : "+response.getBody());  //response{}안 내용이 body
		
		//json데이터를 java객체로 변경
		ObjectMapper objectMapper = new ObjectMapper();
		LogoutDto logoutDto = null;  //long Long의 차이가 있음. Long은 객체
		try {
			logoutDto = objectMapper.readValue(response.getBody(), LogoutDto.class);
		} catch (Exception e) {	e.printStackTrace();}
		System.out.println("로그아웃 id : "+logoutDto.getId());
		
		//model로 결과값 전송
		model.addAttribute("result",logoutDto.getId());
		
		//session 종료
		session.invalidate();
		
		return "logout";
	}
	
	//카카오 로그인
	@GetMapping("kakao/oauth")
	//@ResponseBody - 처음에는 데이터를 받아야해서 선언해줌.
	public String oauth(String code) {
		//1.코드값 리턴(인가코드)(get)----------------------------------------------------------------------------
		System.out.println("FContrller kakao oauth code : "+code);
		
		//2.토큰키 받기(post)-----------------------------------------------------------------------------------
		String tokenUrl = "https://kauth.kakao.com/oauth/token";
		
		//header에 넣어야함.
		String Content_type = "application/x-www-form-urlencoded;charset=utf-8";
		//body에 넣어야함.
		String grant_type = "authorization_code";
		String client_id = "24a08db200fb154e939f596f923fc142";
		String redirect_uri = "http://localhost:8000/kakao/oauth";
		//code = code;  // code는 위에서 받았기에 해줘도 안해줘도 상관은 없음 
		
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
		
		//header+body 합치기
		HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<>(params,headers);
		
		//URL, 전송방식 : post, 데이터, String타입
		ResponseEntity<String> response = rt.exchange(tokenUrl, HttpMethod.POST,tokenRequest,String.class);
		System.out.println("토큰요청값 response : "+response);
		System.out.println("토큰요청값 body : "+response.getBody());  //response{}안 내용이 body
		
		//json데이터를 java객체로 변경
		ObjectMapper objectMapper = new ObjectMapper();
		TokenDto tokenDto = null;
		try {
			tokenDto = objectMapper.readValue(response.getBody(), TokenDto.class);
		} catch (Exception e) {	e.printStackTrace();}
		System.out.println("토큰키 값 TokenDto Access_token : "+tokenDto.getAccess_token());
		
		//3.토큰키를 전송해서 회원정보값 받기(get,post)--------------------------------------------------------------
		String tokenUrl_user = "https://kapi.kakao.com/v2/user/me";
		//header에 넣어야함.
		Content_type = "application/x-www-form-urlencoded;charset=utf-8";
		String authorization = "Bearer "+tokenDto.getAccess_token();
		
		//URL 전송
		RestTemplate rt_user = new RestTemplate();
		//header 생성하기
		HttpHeaders headers_user = new HttpHeaders();
		headers_user.add("Content-type", Content_type);
		headers_user.add("Authorization", authorization);
		
		//header+body 합치기
		HttpEntity<MultiValueMap<String, String>> tokenRequest_user = new HttpEntity<>(headers_user);
		
		//URL, 전송방식 : post, 데이터, String타입
		ResponseEntity<String> response_user = rt_user.exchange(tokenUrl_user, HttpMethod.POST,tokenRequest_user,String.class);
		System.out.println("사용자정보요청값 response_user : "+response_user);
		System.out.println("사용자정보요청값 body : "+response_user.getBody());  //response{}안 내용이 body
		
		//json데이터를 java객체로 변환
		ObjectMapper objectMapper_user = new ObjectMapper();
		//tokenDto객체
		KakaoDto kakaoDto = null;
		try {
			kakaoDto = objectMapper_user.readValue(response_user.getBody(), KakaoDto.class);
		} catch (Exception e) {	e.printStackTrace();}
		System.out.println("토큰키 값 kakaoDto id : "+kakaoDto.getId());
		System.out.println("토큰키 값 kakaoDto Properties nickname : "+kakaoDto.getProperties().nickname);
		
		String targetUrl = "";
		if(kakaoDto != null) {
			System.out.println("카카오 로그인이 완료되었습니다.");
			session.setAttribute("session_id", kakaoDto.getId());
			session.setAttribute("session_name", kakaoDto.getProperties().nickname);
			session.setAttribute("session_token", tokenDto.getAccess_token());
			targetUrl = "redirect:/";
		}else {
			System.out.println("카카오 로그인 에러입니다.");
			targetUrl = "login";
		}
		
		return targetUrl;
	}

}
