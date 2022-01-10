package com.zerobase.fastlms.admin.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zerobase.fastlms.admin.course.model.CourseParam;
import com.zerobase.fastlms.admin.course.dto.CourseDto;

@Mapper
public interface CourseMapper {

	long selectListCount(CourseParam parameter);
	List<CourseDto> selectList(CourseParam parameter);
	
}
