package com.zerobase.fastlms.admin.course.service;

import java.util.List;

import com.zerobase.fastlms.admin.course.CourseParam;
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

}
