package com.zerobase.fastlms.admin.dto;

import java.time.LocalDateTime;

import lombok.Data;

//Member 엔티티클래스와는 다르게 데이터를 가공하여 가져옴
@Data
public class MemberDto {
	
	String userId;
	String userName;
	String phone;
	String password;
	LocalDateTime regDt;
	
	boolean emailAuthYn;
	LocalDateTime emailAuthDt;
	String emailAuthKey;
	
	String resetPasswordKey;
	LocalDateTime resetPasswordLimitDt;
	
	boolean adminYn;
	
	
	//추가 컬럼
	long totalCount;
}
