package com.zerobase.fastlms.admin.course.model;

import com.zerobase.fastlms.admin.model.MemberParam;

import lombok.Data;

@Data
public class TakeCourseParam extends MemberParam {
	long id;
	String status;
	
	String userId;
	
	long takeCourseId;
}
