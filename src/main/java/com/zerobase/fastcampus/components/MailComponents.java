package com.zerobase.fastcampus.components;

/**
 * 메일을 보내기 위한 메소드가 들은 클래스
 */

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

//메일을 보내기위해 어노테이션 삽입
@RequiredArgsConstructor
@Component
public class MailComponents {
	
	//메일을 보내기 위해선 메일 서버를 세팅해야함 
	
	//메일 보내는 클래스
	private final JavaMailSender javaMailSender;
	
//	public void sendMailTest() {
//		SimpleMailMessage msg = new SimpleMailMessage();
//		//메일 받는 사람
//		msg.setTo("loop1397@naver.com");
//		//메일 제목
//		msg.setSubject("안녕하세요. 제로베이스 입니다.");
//		//메일 내용
//		msg.setText("반갑습니다.");
//		
//		javaMailSender.send(msg);
//	}
	
	public boolean sendMail(String mail, String subject, String text) {
		
		boolean result = false;
		
		//인터페이스 MimeMessagePreparator
		MimeMessagePreparator msg = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				mimeMessageHelper.setTo(mail);
				mimeMessageHelper.setSubject(subject);
				//true속성을 넣으면 html태그를 사용할 수 있게됨 
				mimeMessageHelper.setText(text, true);
			}
		};
		
		try {
			javaMailSender.send(msg);
			result = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}
}
