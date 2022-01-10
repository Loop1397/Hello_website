package com.zerobase.fastlms.admin.course.service;

import java.util.List;

import com.zerobase.fastlms.admin.course.model.CourseParam;
import com.zerobase.fastlms.admin.course.model.ServiceResult;
import com.zerobase.fastlms.admin.course.model.TakeCourseInput;
import com.zerobase.fastlms.admin.course.model.TakeCourseParam;
import com.zerobase.fastlms.admin.course.dto.CourseDto;
import com.zerobase.fastlms.admin.course.dto.TakeCourseDto;
import com.zerobase.fastlms.admin.course.model.CourseInput;

public interface TakeCourseService {
	
	/**
	 * 수강 목록
	 * @param parameter
	 * @return
	 */
	List<TakeCourseDto> list(TakeCourseParam parameter);

	/**
	 * 수강내용 상태 변경
	 * @param id
	 * @param status
	 * @return
	 */
	ServiceResult updateStatus(long id, String status);
}
