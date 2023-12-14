package com.java.www.service;

import org.springframework.stereotype.Component;

@Component //객체선언이 자동 (프로그램 시작할때 @Component 붙여있는거 모두 읽어옴)
public class PServiceImpl implements PService {

	@Override
	public void execute() {
		
		System.out.println("1B 연필로 그림을 그립니다.");

	}

}
