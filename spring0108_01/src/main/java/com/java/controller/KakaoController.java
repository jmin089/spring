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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.dto.OAuthToken;
import com.java.dto.KakaoDto;

import jakarta.servlet.http.HttpSession;

@Controller
public class KakaoController {

	@Autowired HttpSession session;
	
	@GetMapping("kakaoLogin")
	public String kakaoLogin() {
		return "kakaoLogin";
	}
	
	
	//카카오로그인
	//http://localhost:8000/kakao/oauth
	@GetMapping("/kakao/oauth")
	//@ResponseBody
	public String oauth(String code) {
		//code값 받기--------------------------------------------------------------------------------------
		System.out.println("kakao code : "+code);
		
		//token키 받기-------------------------------------------------------------------------------------
		String tokenUrl = "https://kauth.kakao.com/oauth/token";
		//headers에 담기
		String content_type = "application/x-www-form-urlencoded;charset=utf-8";
		//body에 담기
		String grant_type = "authorization_code";
		String client_id = "38422562af8962b41f4d3e3973c6eef1";
		String redirect_uri = "http://localhost:8000/kakao/oauth";
		//code = code;
		
		
		// 공공데이터에서 사용 : HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		//url전송 객체 선언
		RestTemplate rt = new RestTemplate();
		
		//HttpHeaders 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", content_type);
		
		//body 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type",grant_type);
		params.add("client_id",client_id);
		params.add("redirect_uri",redirect_uri);
		params.add("code",code);
		
		// 합치는 오브젝트 생성 : HttpHeaders와 MultiValueMap을 합쳐서 전송
		HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<>(params,headers);
		
		//http전송 : post방식, 응답은 response
		ResponseEntity<String> response = rt.exchange(
				tokenUrl, HttpMethod.POST, tokenRequest, String.class);
		System.out.println("토큰키 : "+response);
		System.out.println("토큰키 getBody : "+response.getBody());
		
		//json데이터를 java오브젝트로 변환 : objectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oAuthToken = null;
		
		//json데이터를 java오브젝트로 변경
		try {
			oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (Exception e) {e.printStackTrace();	}
		
		System.out.println("카카오 토큰키 : "+response);
		System.out.println("카카오 토큰키 getBody : "+response.getBody());
		System.out.println("카카오 엑세스 getAccess_token : "+oAuthToken.getAccess_token());
		System.out.println("카카오 엑세스 getExpires_in : "+oAuthToken.getExpires_in());
		
		//사용자 정보가져오기---------------------------------------------------------------------------------
		//HttpHeaders 오브젝트 생성
		HttpHeaders headers_user = new HttpHeaders();
		headers_user.add("Content-type", content_type);
		headers_user.add("Authorization", "Bearer "+oAuthToken.getAccess_token());
		
		// 합치는 오브젝트 생성 : HttpHeaders와 MultiValueMap을 합쳐서 전송
		HttpEntity<MultiValueMap<String, String>> tokenRequest_user = new HttpEntity<>(headers_user);
		String userUrl = "https://kapi.kakao.com/v2/user/me";
		//http전송 : post방식, 응답은 response
		ResponseEntity<String> response_user = rt.exchange(
				userUrl, HttpMethod.POST, tokenRequest_user, String.class);
		System.out.println("사용자 정보 response_user : "+response_user);
		
		//json을 java오브젝트로 변환 : objectMapper----------------------------------------------------------
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoDto kakaoDto = null;
		
		//json데이터를 java오브젝트로 변경
		try {
			kakaoDto = objectMapper2.readValue(response_user.getBody(), KakaoDto.class);
		} catch (Exception e) {e.printStackTrace();	}
		System.out.println("카카오사용자정보 response_user : "+response_user);
		System.out.println("카카오사용자정보 kakaoDto Id : "+kakaoDto.getId());
		System.out.println("카카오사용자정보 kakaoDto Properties Nickname : "+kakaoDto.getProperties().getNickname());
		
		//로그인 시켜줌.
		session.setAttribute("session_id", kakaoDto.getId());
		session.setAttribute("session_name", kakaoDto.getProperties().getNickname());
		
		return "redirect:/";
	}
	
	
}
