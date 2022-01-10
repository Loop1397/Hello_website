package com.zerobase.fastlms.admin.course.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.zerobase.fastlms.admin.course.model.CourseParam;
import com.zerobase.fastlms.admin.course.dto.CourseDto;
import com.zerobase.fastlms.admin.course.model.CourseInput;
import com.zerobase.fastlms.admin.course.service.CourseService;
import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.service.CategoryService;
import com.zerobase.fastlms.util.PageUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AdminCourseController extends BaseController {

	private final CourseService courseService;
	private final CategoryService categoryService;
	
	
	@GetMapping("/admin/course/list")
	public String list(Model model, CourseParam parameter) {
		
		parameter.init();
		
		List<CourseDto> courseList = courseService.list(parameter);
		
		long totalCount = 0;
		
		if(!CollectionUtils.isEmpty(courseList)) {
			totalCount = courseList.get(0).getTotalCount();
		}
		
		String queryString = parameter.getQueryString();
		String pagerHtml = super.getPagerHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);
		
		model.addAttribute("list", courseList);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pager", pagerHtml);
		
		return "admin/course/list";
	}
	
	//배열처럼 여러개의 주소를 한개의 매핑 메소드에 할당 할 수 있음
	@GetMapping(value = {"/admin/course/add", "/admin/course/edit"})
	public String add(Model model, HttpServletRequest request
			,CourseInput parameter) {
		
		//강좌의 카테고리 정보를 내려줘야함
		model.addAttribute("category", categoryService.list());
		
		/**
		 * request.getRequestURI().contains("") 
		 * : 브라우저에서 받아온 주소에 ""안에 들어있는 내용이 있는지 확인 후, 해당 결과값을 boolean타입으로 리턴하는 메소드
		 * : 여기에선 true를 받을 시 강좌 수정, false를 받을 시 강좌 추가로 구현해둠 
		 */
		boolean editMode = request.getRequestURI().contains("/edit");
		CourseDto detail = new CourseDto();
		
		//isEditMode=true일 때, 즉 강좌 수정 모드
		if(editMode) {
			
			long id = parameter.getId();
			
			CourseDto existCourse = courseService.getById(id);
			if(existCourse == null) {
				//수정해야할 강좌가 없음. 에러 처리
				model.addAttribute("message", "강좌 정보가 존재하지 않습니다.");
				return "common/error";
				
			}
			detail = existCourse;
		}
		
		model.addAttribute("editMode", editMode);
		model.addAttribute("detail", detail);
		return "admin/course/add";
	}
	
	@PostMapping(value= {"/admin/course/add", "/admin/course/edit"})
	public String addSubmit(Model model, HttpServletRequest request
			,CourseInput parameter){
		
		boolean editMode = request.getRequestURI().contains("/edit");
		
		if(editMode) {
			
			long id = parameter.getId();
			
			CourseDto existCourse = courseService.getById(id);
			if(existCourse == null) {
				//수정해야할 강좌가 없음. 에러 처리
				model.addAttribute("message", "강좌 정보가 존재하지 않습니다.");
				return "common/error";
				
			}
			
			boolean result = courseService.set(parameter);
			
		} else {
			boolean result = courseService.add(parameter);
		}
		
		return "redirect:/admin/course/list";
	}
	
	@PostMapping(value= {"/admin/course/delete"})
	public String del(Model model, HttpServletRequest request
			,CourseInput parameter){
		
		boolean result = courseService.del(parameter.getIdList());
		
		return "redirect:/admin/course/list";
	}
	
}
