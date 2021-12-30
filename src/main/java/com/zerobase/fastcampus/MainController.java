package com.zerobase.fastcampus;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zerobase.fastcampus.components.MailComponents;

import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.RestController;


//MainPage 클래스 만든 목적 = 매핑을 하기 위함.
//주소(논리적인 인터넷 주소)와 물리적인 파일(프로그래밍을 해야하니까)을 매핑

//하나의 주소에 대해 어디서 매핑? 누가 매핑?
//메소드

//annotation : 어떠한 딱지..?
//Controller : 주소를 매핑하면 특정한 클래스.
@Controller
@RequiredArgsConstructor
public class MainController {

	private final MailComponents mailComponents;
	
	//매핑
	@RequestMapping("/")	//기본 주소(localhost:8080/)에 매핑했음.
	public String index() {
		
		String email = "loop1397@naver.com";
		String subject = "안녕하세요. 제로베이스 입니다.";
		String text = "<p>안녕하세요.</p><p>반갑습니다.</p>";
		
		mailComponents.sendMail(email, subject, text);
		
		//templates의 index.html 출력 
		return "index";
	}
	
	// 스프링 -> MVC (View -> 템플릿엔진 화면에 내용을 출력(html))
	// .NET -> MVC (View -> 출력)
	// python django -> MTV(Template -> 화면출력)
	// 백엔드과정 -> View(화면에 보여지는 부분) -> 프론트엔드과정
	// View가 HTML형태로 바인딩 --> 웹페이지
	// View가 json형태로 바인딩 --> API
	
	// request : 웹에서 서버로 보내기 위해 사용되는 객체
	// response: 서버에서 웹으로 보내기 위해 사용되는 객체
	// 두가지 객체에 대한 인터페이스가 아래(hello)에 들어가있음
	
	@RequestMapping("/hello")	//localhost:8080/hello에 매핑했음.
	public void hello(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html;charset=UTF-8"); //객체의 charset선
		
		PrintWriter printWriter = response.getWriter();
		
		String msg = "<html>" + 
				"<head>" +
				"<meta charset = \"UTF-8\">" +
				"</head>" +
				"<body>" +
				"<p>hello</p> <p>fastcampus website!</p>" +
				"<p>안녕하세요!</p>" +
				"</body>" + 
				"</html>";
		
		printWriter.write(msg);
		printWriter.close();
		
		//RestController를 쓰면 문자열을 return해도 괜찮지만, Controller를 쓰면 문자열 return이 불가능(오류).
		//return msg;
	}
	
	
}
