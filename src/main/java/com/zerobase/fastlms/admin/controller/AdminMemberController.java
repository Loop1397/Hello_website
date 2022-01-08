package com.zerobase.fastlms.admin.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.admin.model.MemberInput;
import com.zerobase.fastlms.member.entity.Member;
import com.zerobase.fastlms.member.service.MemberService;
import com.zerobase.fastlms.util.PageUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AdminMemberController {

	/**
	 * final이기 때문에 반드시 초기화를 해줘야함.
	 * 초기화를 위해 생성자가 필요한데, 그것을 @RequiredArgsConstructor에서 해줌
	 */
	private final MemberService memberService;
	
	//회원 목록 가져오기
	@GetMapping("/admin/member/list")
	public String list(Model model, MemberParam parameter) {
		
		parameter.init();
		
		List<MemberDto> members = memberService.list(parameter);
		
		long totalCount = 0;
		
		//member가 0명 이상일 때 멤버의 수(members.size())를 값으로 넣음
		if(members != null && members.size() > 0) {
			totalCount = members.get(0).getTotalCount();
		}
		String queryString = parameter.getQueryString();
		PageUtil pageUtil = new PageUtil(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);
		
		model.addAttribute("list", members);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pager", pageUtil.pager());
		
		
		return "admin/member/list";
	}
	
	@GetMapping("/admin/member/detail")
	public String detail(Model model, MemberParam parameter) {
		
		parameter.init();
		
		MemberDto member = memberService.detail(parameter.getUserId());
		model.addAttribute("member", member);
		
		return "admin/member/detail";
	}
	
	@PostMapping("/admin/member/status")
	public String status(Model model, MemberInput parameter) {
		
		boolean result = memberService.updateStatus(parameter.getUserId(), parameter.getUserStatus());
		
		//redirect: - 직접 주소를 이동시키는 방법
		return "redirect:/admin/member/detail?userId=" + parameter.getUserId();
	}
	
	@PostMapping("/admin/member/password")
	public String password(Model model, MemberInput parameter) {
		
		boolean result = memberService.updatePassword(parameter.getUserId(), parameter.getPassword());
		
		//redirect: - 직접 주소를 이동시키는 방법
		return "redirect:/admin/member/detail?userId=" + parameter.getUserId();
	}
	
}
