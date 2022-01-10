package com.zerobase.fastlms.admin.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zerobase.fastlms.admin.course.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
