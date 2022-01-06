package com.zerobase.fastlms.admin.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.model.MemberParam;

import java.util.List;

@Mapper
public interface MemberMapper {
	
	long selectListCount(MemberParam parameter);
	
	/**
	 * Member 리스트를 가져오는 인터페이스
	 * MemberMapper.xml에 매핑되어있음
	 * @param parameter
	 * @return
	 */
	List<MemberDto> selectList(MemberParam parameter);
	
}
