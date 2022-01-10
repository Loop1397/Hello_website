package com.zerobase.fastlms.admin.course.service;

import java.util.List;

import com.zerobase.fastlms.admin.course.model.CourseParam;
import com.zerobase.fastlms.admin.course.model.ServiceResult;
import com.zerobase.fastlms.admin.course.model.TakeCourseInput;
import com.zerobase.fastlms.admin.course.dto.CourseDto;
import com.zerobase.fastlms.admin.course.model.CourseInput;

public interface CourseService {
	
	/**
	 * 강좌 등록
	 */
	boolean add(CourseInput parameter);

	/**
	 * 강좌 목록
	 * @param parameter
	 * @return
	 */
	List<CourseDto> list(CourseParam parameter);

	/**
	 * 강좌 상세 정보
	 * @param id
	 * @return
	 */
	CourseDto getById(long id);

	/**
	 * 강좌 정보 수정
	 * @param parameter
	 * @return
	 */
	boolean set(CourseInput parameter);

	/**
	 * 강좌 내용 삭제
	 * @param idList
	 * @return
	 */
	boolean del(String idList);

	/**
	 * 프론트에 보여줄 강좌 목록
	 */
	List<CourseDto> frontList(CourseParam parameter);

	/**
	 * 프론트에 보여줄 강좌 상세
	 * @param id
	 * @return
	 */
	CourseDto frontDetail(long id);

	/**
	 * 수강 신청
	 * @param parameter
	 * @return
	 */
	ServiceResult req(TakeCourseInput parameter);

	/**
	 * 모든 강좌 정보 가져오기
	 * @return
	 */
	List<CourseDto> listAll();

}
