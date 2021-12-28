package com.zerobase.fastcampus.member.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zerobase.fastcampus.member.entity.Member;
import com.zerobase.fastcampus.member.model.MemberInput;
import com.zerobase.fastcampus.member.repository.MemberRepository;
import com.zerobase.fastcampus.member.service.MemberService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
		model.addAttribute("result", result);
		
		return "member/register_complete";
	}
}
