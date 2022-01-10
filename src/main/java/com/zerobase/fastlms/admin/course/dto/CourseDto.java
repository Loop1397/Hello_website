package com.zerobase.fastlms.admin.course.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.zerobase.fastlms.admin.course.entity.Course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CourseDto {
	long id;
	long categoryId;
	String imagePath;
	String keyword;
	String subject;
	String summary;
	String contents;
	long price;
	long salePrice;
	LocalDate saleEndDt;
	LocalDateTime regDt;
	LocalDateTime udtDt;
	
	//추가 컬럼
	long totalCount;
	long seq;
		
	public static CourseDto of(Course course) {
		return CourseDto.builder()
				.id(course.getId())
				.imagePath(course.getImagePath())
				.categoryId(course.getCategoryId())
				.keyword(course.getKeyword())
				.subject(course.getSubject())
				.summary(course.getSummary())
				.contents(course.getContents())
				.price(course.getPrice())
				.salePrice(course.getSalePrice())
				.saleEndDt(course.getSaleEndDt())
				.regDt(course.getRegDt())
				.udtDt(course.getUdtDt())
				.build();
	}
}
