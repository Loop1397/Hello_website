package com.zerobase.fastlms.admin.course.model;

import com.zerobase.fastlms.admin.model.MemberParam;

import lombok.Data;

@Data
public class TakeCourseInput extends MemberParam {
	
	long courseId;
	String userId;
	
	long TakeCourseId;
	
}
