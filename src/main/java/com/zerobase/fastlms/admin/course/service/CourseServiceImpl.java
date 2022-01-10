package com.zerobase.fastlms.admin.course.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.zerobase.fastlms.admin.course.CourseParam;
import com.zerobase.fastlms.admin.course.dto.CourseDto;
import com.zerobase.fastlms.admin.course.entity.Course;
import com.zerobase.fastlms.admin.course.mapper.CourseMapper;
import com.zerobase.fastlms.admin.course.model.CourseInput;
import com.zerobase.fastlms.admin.course.repository.CourseRepository;
import com.zerobase.fastlms.admin.dto.MemberDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

	private final CourseRepository courseRepository;
	private final CourseMapper courseMapper;

	//String으로 들어온 값을 LocalDate로 변환하여 출력해주는 메소드
	private LocalDate getLocalDate(String value) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
			return LocalDate.parse(value, formatter);
		} catch (Exception e) {
			
		}
		
		return null;
	}
	
	
	@Override
	public boolean add(CourseInput parameter) {
		
		LocalDate saleEndDt = getLocalDate(parameter.getSaleEndDtText());
		
		Course course = Course.builder()
				.categoryId(parameter.getCategoryId())
				.subject(parameter.getSubject())
				.keyword(parameter.getKeyword())
				.summary(parameter.getSummary())
				.contents(parameter.getContents())
				.price(parameter.getPrice())
				.salePrice(parameter.getSalePrice())
				.saleEndDt(saleEndDt)				//종료일 문자열
				.regDt(LocalDateTime.now())
				.build();
		courseRepository.save(course);
		
		
		return true;
	}

	@Override
	public boolean set(CourseInput parameter) {
		
		LocalDate saleEndDt = getLocalDate(parameter.getSaleEndDtText());
		
		Optional<Course> optionalCourse =  courseRepository.findById(parameter.getId());
		if (!optionalCourse.isPresent()) {
			//수정할 데이터가 없음
			return false;
		}
		
		Course course = optionalCourse.get();
		course.setCategoryId(parameter.getCategoryId());
		course.setSubject(parameter.getSubject());
		course.setKeyword(parameter.getKeyword());
		course.setSummary(parameter.getSummary());
		course.setContents(parameter.getContents());
		course.setPrice(parameter.getPrice());
		course.setSalePrice(parameter.getSalePrice());
		course.setSaleEndDt(saleEndDt);
		course.setUdtDt(LocalDateTime.now());
		courseRepository.save(course);		
		
		return true;
	}

	@Override
	public List<CourseDto> list(CourseParam parameter) {
		long totalCount = courseMapper.selectListCount(parameter);
		List<CourseDto> list = courseMapper.selectList(parameter);
		
		if(!CollectionUtils.isEmpty(list)) {
			int i = 0;
			for(CourseDto x : list) {
				x.setTotalCount(totalCount);
				
				x.setSeq(totalCount - parameter.getPageStart() - i);
				i++;
			}
		}
		
		return list; 
	}

	@Override
	public CourseDto getById(long id) {
		
		return courseRepository.findById(id).map(CourseDto::of).orElse(null);
	}


	@Override
	public boolean del(String idList) {
		
		if (idList !=  null && idList.length() > 0) {
			
			String[] ids = idList.split(",");
			for(String x : ids) {
				long id = 0L;
				//정수형으로 날아온 id값에 맞는 강의 내용 삭제
				try {
					id = Long.parseLong(x);
				} catch (Exception e) {
					
				}
				
				if(id > 0) {
					courseRepository.deleteById(id);
				}	
			}
		}
		
		return true;
	}


	@Override
	public List<CourseDto> frontList(CourseParam parameter) {
		
		if (parameter.getCategoryId() < 1) {
			List<Course> courseList = courseRepository.findAll();
			return CourseDto.of(courseList);		
		}
		
		return courseRepository.findByCategoryId(parameter.getCategoryId()).map(CourseDto::of).orElse(null);
		//아래 5줄과 같은 내용
		
//		Optional<List<Course>> optionalCourses = courseRepository.findByCategoryId(parameter.getCategoryId());
//		if(optionalCourses.isPresent()) {
//			return CourseDto.of(optionalCourses.get());
//		}
//		return null;
	}


	@Override
	public CourseDto frontDetail(long id) {
		
		Optional<Course> optionalCourse = courseRepository.findById(id);
		if(optionalCourse.isPresent()) {
			return CourseDto.of(optionalCourse.get());
		}
		
		
		
		return null;
	}


}
