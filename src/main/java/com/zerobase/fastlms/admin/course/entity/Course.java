package com.zerobase.fastlms.admin.course.entity;

import java.time.LocalDate;
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
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	long categoryId;
	
	String imagePath;
	String keyword;
	String subject;
	
	@Column(length = 1000)
	String summary;
	
	@Lob
	String contents;
	long price;
	long salePrice;
	LocalDate saleEndDt;
	
	//강좌 등록일(추가날짜)
	LocalDateTime regDt;
	//수정일
	LocalDateTime udtDt;
}
