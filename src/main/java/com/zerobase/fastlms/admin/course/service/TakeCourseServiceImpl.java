package com.zerobase.fastlms.admin.course.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.zerobase.fastlms.admin.course.model.CourseParam;
import com.zerobase.fastlms.admin.course.model.ServiceResult;
import com.zerobase.fastlms.admin.course.model.TakeCourseInput;
import com.zerobase.fastlms.admin.course.model.TakeCourseParam;
import com.zerobase.fastlms.admin.course.dto.CourseDto;
import com.zerobase.fastlms.admin.course.dto.TakeCourseDto;
import com.zerobase.fastlms.admin.course.entity.Course;
import com.zerobase.fastlms.admin.course.entity.TakeCourse;
import com.zerobase.fastlms.admin.course.mapper.CourseMapper;
import com.zerobase.fastlms.admin.course.mapper.TakeCourseMapper;
import com.zerobase.fastlms.admin.course.model.CourseInput;
import com.zerobase.fastlms.admin.course.repository.CourseRepository;
import com.zerobase.fastlms.admin.course.repository.TakeCourseRepository;
import com.zerobase.fastlms.admin.dto.MemberDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TakeCourseServiceImpl implements TakeCourseService {

	private final TakeCourseRepository takeCourseRepository;
	private final TakeCourseMapper takecourseMapper;
	
	@Override
	public List<TakeCourseDto> list(TakeCourseParam parameter) {
		
		long totalCount = takecourseMapper.selectListCount(parameter);
		List<TakeCourseDto> list = takecourseMapper.selectList(parameter);
		
		if(!CollectionUtils.isEmpty(list)) {
			int i = 0;
			for(TakeCourseDto x : list) {
				x.setTotalCount(totalCount);
				
				x.setSeq(totalCount - parameter.getPageStart() - i);
				i++;
			}
		}
		
		return list; 
	}
	@Override
	public ServiceResult updateStatus(long id, String status) {
		
		Optional<TakeCourse> optionalTakeCourse = takeCourseRepository.findById(id);
		if(!optionalTakeCourse.isPresent()) {
			return new ServiceResult(false, "수강 정보가 존재하지 않습니다.");
		}
		
		TakeCourse takeCourse = optionalTakeCourse.get();
		
		takeCourse.setStatus(status);
		takeCourseRepository.save(takeCourse);
		
		return new ServiceResult(true);
	}

	

}
