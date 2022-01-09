package com.zerobase.fastlms.admin.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable=false)
	Long id;
	
	String categoryName;
	//카테고리 순서
	int sortValue;
	
	//카테고리 사용 가능 여부
	boolean usingYn;
}
