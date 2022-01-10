package com.zerobase.fastlms.admin.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.zerobase.fastlms.admin.dto.CategoryDto;

import java.util.List;

@Mapper
public interface CategoryMapper {
	
	List<CategoryDto> select(CategoryDto parameter);
	
}
