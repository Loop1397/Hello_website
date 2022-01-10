package com.zerobase.fastlms.admin.course.controller;


import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.zerobase.fastlms.admin.course.model.ServiceResult;
import com.zerobase.fastlms.admin.course.model.TakeCourseParam;
import com.zerobase.fastlms.admin.course.dto.TakeCourseDto;
import com.zerobase.fastlms.admin.course.service.TakeCourseService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AdminTakeCourseController extends BaseController {

	private final TakeCourseService takeCourseService;
	
	
	@GetMapping("/admin/takecourse/list")
	public String list(Model model, TakeCourseParam parameter) {
		
		parameter.init();
		
		List<TakeCourseDto> courseList = takeCourseService.list(parameter);
		
		long totalCount = 0;
		
		if(!CollectionUtils.isEmpty(courseList)) {
			totalCount = courseList.get(0).getTotalCount();
		}
		
		String queryString = parameter.getQueryString();
		String pagerHtml = super.getPagerHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);
		
		model.addAttribute("list", courseList);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pager", pagerHtml);
		
		return "admin/takecourse/list";
	}
	
	@PostMapping("/admin/takecourse/status")
	public String status(Model model, TakeCourseParam parameter) {
		
		ServiceResult result = takeCourseService.updateStatus(parameter.getId(), parameter.getStatus());
		if(!result.isResult()) {
			model.addAttribute("message", result.getMessage());
			return "common/error";
		}

		model.addAttribute("message", result.getMessage());

		return "admin/takecourse/list";
	}
	
}
