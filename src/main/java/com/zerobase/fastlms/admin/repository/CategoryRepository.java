package com.zerobase.fastlms.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zerobase.fastlms.admin.entity.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Optional<List<Category>> findAllByOrderBySortValueDesc();
	
}
