package com.zerobase.fastlms.admin.course.model;

import lombok.Data;

@Data
public class CourseInput {

	long id;
	long categoryId;
	String subject;
	String keyword;
	String summary;
	String contents;
	long price;
	long salePrice;
	String saleEndDtText;
	
	//삭제를 위한 속성
	String idList;

	public void setFilename(String saveFilename) {
		// TODO Auto-generated method stub
		
	}
}
