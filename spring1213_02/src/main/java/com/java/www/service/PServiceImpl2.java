package com.java.www.service;

import org.springframework.stereotype.Service;

@Service // @Component, @Configuration, @Service, @controller, @Repository 
public class PServiceImpl2 implements PService {

	@Override
	public void execute() {
		
		System.out.println("'신규 service' 버전 2을 호출합니다.");

	}

}
