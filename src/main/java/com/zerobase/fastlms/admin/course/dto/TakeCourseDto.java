package com.zerobase.fastlms.admin.course.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Data;;

@Data
public class TakeCourseDto {
Long id;
	
	long courseId;
	String userId;
	
	//결제 금액
	long payPrice;
	//상태(수강신청, 결제완료, 수강취소)
	String status;
	
	//강좌 신청일
	LocalDateTime regDt;
	
	//JOIN
	String userName;
	String phone;
	String subject;
	
	//추가 컬럼
	long totalCount;
	long seq;

	public String getRegDtText() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
		return regDt != null ? regDt.format(formatter): "";
		
		
	}


}
