package com.zerobase.fastlms.admin.course.controller;


import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.zerobase.fastlms.admin.course.model.ServiceResult;
import com.zerobase.fastlms.admin.course.model.TakeCourseInput;
import com.zerobase.fastlms.admin.course.service.CourseService;
import com.zerobase.fastlms.admin.service.CategoryService;
import com.zerobase.fastlms.common.model.ResponseResult;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ApiCourseController extends BaseController {

	private final CourseService courseService;
	private final CategoryService categoryService;
	
	
	//수강 신청
	@PostMapping("/api/course/req.api")
	public ResponseEntity<?> courseReq(Model model 
			, @RequestBody TakeCourseInput parameter
			, Principal principal) {
		
		parameter.setUserId(principal.getName());
		
		ServiceResult result = courseService.req(parameter);
		
		if(!result.isResult()) {
			ResponseResult responseResult = new ResponseResult(false, result.getMessage());
			
			return ResponseEntity.ok().body(responseResult);
		}
		
		ResponseResult responseResult = new ResponseResult(true);
		
		return ResponseEntity.ok().body(responseResult);
	}
	
	
}
