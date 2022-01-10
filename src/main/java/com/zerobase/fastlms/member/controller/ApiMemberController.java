package com.zerobase.fastlms.member.controller;

import java.security.Principal;
import java.util.List;

//import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;

import com.zerobase.fastlms.admin.course.dto.TakeCourseDto;
import com.zerobase.fastlms.admin.course.model.ServiceResult;
import com.zerobase.fastlms.admin.course.model.TakeCourseInput;
import com.zerobase.fastlms.admin.course.service.TakeCourseService;
import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.common.model.ResponseResult;
//import com.zerobase.fastcampus.member.entity.Member;
import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.model.ResetPasswordInput;
//import com.zerobase.fastcampus.member.repository.MemberRepository;
import com.zerobase.fastlms.member.service.MemberService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 멤버 관련 컨트롤러
 * @author nochan-u
 *
 */


@RequiredArgsConstructor	//생성자를 자동으로 만들어주는 어노테이션
@Controller
public class ApiMemberController {
	
	private final TakeCourseService takeCourseService;


	@PostMapping("/api/member/course/cancel.api")
	public ResponseEntity<?> cencelCourse(Model model
			, @RequestBody TakeCourseInput parameter, Principal principal) {
		
		String userId = principal.getName();	
		
		//내 강좌인지 확인 후 강좌 취소
		TakeCourseDto detail = takeCourseService.detail(parameter.getTakeCourseId());
		if(detail == null) {
			ResponseResult responseResult = new ResponseResult(false, "수강 신청 정보가 존재하지 않습니다.");
			return ResponseEntity.ok().body(responseResult);
		}
		
		if(userId == null || !userId.equals(detail.getUserId())) {
			ResponseResult responseResult = new ResponseResult(false, "본인의 수강 신청 정보만 취소할 수 있습니다.");
			return ResponseEntity.ok().body(responseResult);	
		}
		
		ServiceResult result = takeCourseService.cancel(parameter.getTakeCourseId());	
		if(!result.isResult()) {
			ResponseResult responseResult = new ResponseResult(false, result.getMessage());
			return ResponseEntity.ok().body(responseResult);
		}

		ResponseResult responseResult = new ResponseResult(true, result.getMessage());
		return ResponseEntity.ok().body(responseResult);
	}
}