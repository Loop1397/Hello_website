package com.zerobase.fastlms.admin.service;


import java.util.List;

import org.springframework.stereotype.Service;
import com.zerobase.fastlms.admin.dto.CategoryDto;
import com.zerobase.fastlms.admin.model.CategoryInput;

@Service
public interface CategoryService {
	
	/**
	 * 카테고리 리스트 불러오기
	 * @return
	 */
	List<CategoryDto> list();
	
	/**
	 *  카테고리 신규 추가
	 * @param categoryName
	 * @return
	 */
	boolean add(String categoryName);
	
	/**
	 * 카테고리 업데이트
	 * @param parameter
	 * @return
	 */
	boolean update(CategoryInput parameter);
	
	/**
	 * 카테고리 삭제
	 */
	boolean del(long id);
}
