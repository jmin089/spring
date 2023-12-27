package com.java.www.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // @Component, @Configuration, @Service, @controller, @Repository
public class UploadConfig implements WebMvcConfigurer{
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/upload/**").addResourceLocations("file:///c:/upload/");
		//upload 형태로 들어오면 c:/upload/에서 찾아라
		//다른 파일로 하고 싶으면 한줄 추가
		//.css.js.jpg.png 는 static에서 만든다.
	}

}