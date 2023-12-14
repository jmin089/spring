package com.java.www.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.java.www.service.PService;
import com.java.www.service.PServiceImpl;
import com.java.www.service.PServiceImpl2;

@Configuration  //설정파일이다! 자동으로 객체선언
public class AppConfig {
	
	@Bean
	public PService pconfig() {
		return new PServiceImpl2();
	}

}
