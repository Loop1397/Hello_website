package com.zerobase.fastlms.admin.course.model;

import com.zerobase.fastlms.admin.model.MemberParam;

import lombok.Data;

@Data
public class CourseParam extends MemberParam {
	
	long categoryId;
	//강좌 id
	long id;
	
}
