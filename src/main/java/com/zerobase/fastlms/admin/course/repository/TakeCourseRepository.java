package com.zerobase.fastlms.admin.course.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zerobase.fastlms.admin.course.entity.TakeCourse;

public interface TakeCourseRepository extends JpaRepository<TakeCourse, Long> {

	
	
	long countByCourseIdAndUserIdAndStatusIn(long courseId, String userId, Collection<String> statusList);
	
}
