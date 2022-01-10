package com.zerobase.fastlms.admin.course.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class TakeCourse implements TakeCourseCode {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	long courseId;
	String userId;
	
	//결제 금액
	long payPrice;
	//상태(수강신청, 결제완료, 수강취소)
	String status;
	
	//강좌 신청일
	LocalDateTime regDt;
}
