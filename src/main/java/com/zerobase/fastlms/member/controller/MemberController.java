package com.zerobase.fastlms.member.controller;

import java.security.Principal;
import java.util.List;

//import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zerobase.fastlms.admin.course.dto.TakeCourseDto;
import com.zerobase.fastlms.admin.course.model.ServiceResult;
import com.zerobase.fastlms.admin.course.service.TakeCourseService;
import com.zerobase.fastlms.admin.dto.MemberDto;
//import com.zerobase.fastcampus.member.entity.Member;
import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.model.ResetPasswordInput;
//import com.zerobase.fastcampus.member.repository.MemberRepository;
import com.zerobase.fastlms.member.service.MemberService;
import com.zerobase.fastlms.util.PasswordUtils;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	private final TakeCourseService takeCourseService;

	
	//로그인 페이지
	@RequestMapping("/member/login")
	public String login() {
		
		return "member/login";
	}
	
	//비밀번호 찾기 페이지
	@GetMapping("/member/find/password")
	public String findPassword() {
		return "member/find_password";
	}
	
	@PostMapping("/member/find/password")
	public String findPasswordSubmit(
			Model model,
			ResetPasswordInput parameter) {
		
		boolean result = false;
		try {
			//sendResetPassword에서 에러를 던졌을 때 캐치해내기 위함
			result = memberService.sendResetPassword(parameter);
		} catch (Exception e) {
		}
		model.addAttribute("result", result);
		
		//redirect:/ 페이지를 이동함과 동시에 주소(html)도 변경하기 위한 return키워드
//		return "redirect:/";
		
		return "member/find_password_result";
	}
	
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
	
	//패스워드 재설정 페이지
	@GetMapping("/member/reset/password")
	public String resetPassword(Model model, HttpServletRequest request) {
		
		String uuid = request.getParameter("id");
		boolean result = memberService.checkResetPassword(uuid);
		
		model.addAttribute("result", result);
		
		return "member/reset_password";
	}
	
	@PostMapping("member/reset/password")
	public String resetPasswordSubmit(Model model, ResetPasswordInput parameter) {
		
		boolean result = false;
		try {
			result = memberService.resetPassword(parameter.getId(), parameter.getPassword());
		} catch (Exception e) {
		}
		
		model.addAttribute("result", result);
		
		return "member/reset_password_result";
	}
	
	// 회원 정보 출력
	@GetMapping("/member/info")
	public String memberInfo(Model model, Principal principal) {
		
		String userId = principal.getName();
		MemberDto detail = memberService.detail(userId);
		
		model.addAttribute("detail", detail);
		
		return "member/info";
	}
	
	// 회원 정보 출력
	@PostMapping("/member/info")
	public String memberInfoSubmit(Model model, Principal principal, MemberInput parameter) {
		
		String userId = principal.getName();
		parameter.setUserId(userId);
		
		ServiceResult result = memberService.updateMember(parameter) ;
		
		if(!result.isResult()) {
			model.addAttribute("message", result.getMessage());
			return "common/error";
		}
		
		return "redirect:/member/info";
	}
	
	@GetMapping("/member/takecourse")
	public String memberTakeCourse(Model model, Principal principal) {
		
		String userId = principal.getName();
        List<TakeCourseDto> list = takeCourseService.myCourse(userId);
        
        model.addAttribute("list", list);
        
        return "member/takecourse";
	}
	
	//비밀번호 변경 페이지
	@GetMapping("/member/password")
	public String memberPassword(Model model
			, MemberInput parameter
			, Principal principal) {
		
		String userId = principal.getName();
		parameter.setUserId(userId);
		
		ServiceResult result = memberService.updateMemberPassword(parameter);
		if(!result.isResult()) {
			model.addAttribute("message", result.getMessage());
			return "common/error";
		}
		
		return "redirect:/member/info";
	}
	
	@PostMapping("/member/password")
	public String memberPasswordSubmit(Model model, Principal principal) {
		
		String userId = principal.getName();
		MemberDto detail = memberService.detail(userId);
		
		model.addAttribute(detail);
		
		return "member/info";
	}
	
	//회원 탈퇴
	@GetMapping("/member/withdraw")
	public String withDraw(Model model) {
		
		return "member/withdraw";
	}
	
	@PostMapping("/member/withdraw")
	public String withDrawSubmit(Model model
			, MemberInput parameter
			, Principal principal) {
		
		String userId = principal.getName();
		parameter.setUserId(userId);
		
		
		ServiceResult result = memberService.withdraw(userId, parameter.getPassword());
		if(!result.isResult()) {
			model.addAttribute("message", result.getMessage());
			return "common/error";
		}
		
		return "redirect:/member/logout";
	}
	
}