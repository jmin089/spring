package com.java.www.service;

import org.springframework.stereotype.Service;

@Service   // @Component, @Configuration, @Service, @controller, @Repository
public class PServiceImpl implements PService {

	@Override
	public void execute() {
		
		System.out.println("'service' 버전 1을 호출합니다.");

	}

}
