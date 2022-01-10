package com.zerobase.fastlms.admin.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.zerobase.fastlms.member.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Member 엔티티클래스와는 다르게 데이터를 가공하여 가져옴
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MemberDto {
	
	String userId;
	String userName;
	String phone;
	String password;
	LocalDateTime regDt;
	LocalDateTime udtDt;
	
	boolean emailAuthYn;
	LocalDateTime emailAuthDt;
	String emailAuthKey;
	
	String resetPasswordKey;
	LocalDateTime resetPasswordLimitDt;
	
	boolean adminYn;
	String userStatus;
	
	//추가 컬럼
	long totalCount;
	long seq;

	//우편 관련
	private String zipcode;
	private String addr;
	private String addrDetail;
	
	public static MemberDto of(Member member) {
		
		return MemberDto.builder()
				.userId(member.getUserId())
				.userName(member.getUserName())
				.phone(member.getPhone())
//				.password(member.getPassword())
				.udtDt(member.getUdtDt())
				.regDt(member.getRegDt())
				.emailAuthYn(member.isEmailAuthYn())
				.emailAuthDt(member.getEmailAuthDt())
				.emailAuthKey(member.getEmailAuthKey())
				.resetPasswordKey(member.getResetPasswordKey())
				.resetPasswordLimitDt(member.getResetPasswordLimitDt())
				.userStatus(member.getUserStatus())
				.adminYn(member.isAdminYn())
				
				.zipcode(member.getZipcode())
				.addr(member.getAddr())
				.addrDetail(member.getAddrDetail())
				
				.build();
	}
	
	public String getRegDtText() {	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
		return regDt != null ? regDt.format(formatter) : "";		
	}
	
	public String getUdtDtText() {	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
		return udtDt != null ? regDt.format(formatter) : "";		
	}
	
}
