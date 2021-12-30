package com.zerobase.fastcampus.member.controller;

//import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.zerobase.fastcampus.member.entity.Member;
import com.zerobase.fastcampus.member.model.MemberInput;
//import com.zerobase.fastcampus.member.repository.MemberRepository;
import com.zerobase.fastcampus.member.service.MemberService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 멤버 관련 컨트롤러
 * @author nochan-u
 *
 */


@RequiredArgsConstructor	//생성자를 자동으로 만들어주는 어노테이션
@Controller
public class MemberController {
	
	//생성자 객체를 통해서 데이터를 주입 및 처리 
	private final MemberService memberService;

	
	//페이지 로딩 시
	//@RequestMapping(value = "/member/register", method = RequestMethod.GET)	
	@GetMapping("/member/register")
	public String register() {
		
		// System.out.println("request get");
		
		return "member/register";
	}
	
	// request WEB -> SERVER
	// response SERVER -> WEB
	
	//버튼으로 정보 전달 후
	//@RequestMapping(value= "/member/register", method = RequestMethod.POST) 
	@PostMapping("/member/register")
	public String registerSubmit(Model model, HttpServletRequest request, HttpServletResponse response
			, MemberInput parameter) {
		
		
		boolean result = memberService.register(parameter);

		//Model : 클라이언트에 데이터를 내리기 위한 인터페이스
		//결과(result)값에 따라 thymeleaf문법을 이용해 페이지에 출력되는 내용 변경
//		if(result) {
//			model.addAttribute("result", "회원가입에 성공했습니다");
//		} else {
//			model.addAttribute("result", "회원가입에 실패했습니다");
//		}
		model.addAttribute("result", result);
		
		return "member/register_complete";
	}
	
	@GetMapping("/member/email_auth")
	public String emailAuth(Model model, HttpServletRequest request) {
		
		//파라미터(클라이언트에서 서버로 전송되는 정보들) 받기
		//본 코드에선 주소의 email_auth뒤에 ?id=~~라 쳤을 때 ~~부분을 String uuid에 저장함
		String uuid = request.getParameter("id");
		System.out.println(uuid);
		
		boolean result = memberService.emailAuth(uuid);
		model.addAttribute("result", result);
		
		return "member/email_auth";
		
	}
	
}
