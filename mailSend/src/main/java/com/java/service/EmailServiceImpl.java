package com.java.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMessage.RecipientType;
import jakarta.servlet.http.HttpSession;

@Service
public class EmailServiceImpl implements EmailService {
	
	//이메일발송 객체
	@Autowired JavaMailSender mailSender;
	@Autowired HttpSession session;
	

	//이메일 발송 - html
	@Override
	@Async     //비동기 처리
	public String emailSend2(String name, String email) {
		System.out.println("EmailServiceImpl emailSend email : "+email);
		//인증코드 생성
		String pwcode = getCreateKey();
		
		//이메일 전송
		MimeMessage message = mailSender.createMimeMessage();
		try {
			message.setSubject("[인증번호] 홍길동님 회원가입 이메일 인증코드 발송","utf-8"); //제목
			String htmlData="<table align='center' style='margin:0 0 0 40px;border:1px #D9D9D9 solid;'>\r\n"
					+ "			<tr>\r\n"
					+ "				<td style='width:618px;height:220px;padding:0;margin:0;vertical-align:top;font-size:0;line-height:0;background:#F9F9F9;'>\r\n"
					+ "					<p style='width:620px;margin:30px 0 0 0;padding:0;text-align:center;'><img src='https://cfm.kt.com/images/v2/layout/gnb-ktlogo.png' alt='비밀번호 찾기를 요청하셨습니다.'/></p>\r\n"
					+ "					<p style='width:620px;margin:10px 0 0 0;padding:0;text-align:center;color:#888888;font-size:12px;line-height:1;'>아래의 인증코드는 회원가입에 필요한 인증코드입니다.</p>\r\n"
					+ "					<p style='width:620px;margin:28px 0 0 0;padding:0;text-align:center;color:#666666;font-size:12px;line-height:1;'><strong>회원가입 인증코드 : <span style='color:#F7703C;line-height:1;'>"+pwcode+"</span></strong></p>\r\n"
					+ "					<p style='width:620px;margin:30px 0 0 0;padding:0;text-align:center;color:#888888;font-size:12px;line-height:1.4;'>회원가입에 필요한 인증코드를 발송해 드렸습니다.<br/>인증코드를 인증번호 입력에 넣고 인증번호 확인 버튼을 클릭해주세요.</p>\r\n"
					+ "				</td>\r\n"
					+ "			</tr>\r\n"
					+ "			</table>	";
			message.setText(htmlData,"utf-8","html");                            //내용
			message.setFrom(new InternetAddress("bijou089@naver.com"));          //보낸이
			message.addRecipient(RecipientType.TO, new InternetAddress(email));  //받는이
			mailSender.send(message);                                            //메일발송
			session.setAttribute("email_pwcode", pwcode);
			
		} catch (MessagingException e) {e.printStackTrace();}
		
		//이메일 전송
		System.out.println("(html)이메일 발송완료!!!!");
		return pwcode;
	}

	//이메일 발송 - text
	@Override
	public String emailSend(String name, String email) {
		System.out.println("EmailServiceImpl emailSend email : "+email);
		//인증코드 생성
		String pwcode = getCreateKey();
		
		//이메일 전송
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);  //받는이
		message.setFrom("bijou089@naver.com");  //보낸이
		message.setSubject(name+"님 회원인증번호를 발송합니다.");  //제목
		message.setText("회원가입 인증번호 메일 발송입니다.\n"+
						name+"님의 인증번호 : "+pwcode);
		mailSender.send(message);
		System.out.println("(text)이메일 발송완료!!!!");
		return pwcode;
	}
	
	public String getCreateKey() {
		String pwcode="";
		char[] charSet = new char[] {
				'0','1','2','3','4','5','6','7','8','9',
				'a','b','c','d','e','f','g','h','i','j',
				'k','l','m','n','o','p','q','r','s','t',
				'u','v','w','x','y','z'
		};
		int idx = 0;
		for(int i=0;i<7;i++) {
			idx = (int) (Math.random()*36);
			if(i>=5) {
				pwcode += "*";
			}else {
			pwcode += charSet[idx];
			}
		}
		System.out.println("EmailServiceImpl getCreateKey 비밀번호 인증코드(랜덤) : "+pwcode);
		return pwcode;
	}

}
