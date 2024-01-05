package com.java.service;

public interface EmailService {

	//이메일 발송 - text
	String emailSend(String name, String email);

	//이메일 발송 - html
	String emailSend2(String name, String email);

}
