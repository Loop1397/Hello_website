package com.zerobase.fastlms.member.model;

//lombok : getter, setter를 자동으로 만들어주는 라이브러리
import lombok.Data;
import lombok.ToString;

//@Data : 정보를 받기 위한 lombok의 어노테이션
@ToString
@Data
public class MemberInput {
	
	private String userId;
	private String userName;
	private String password;
	private String phone;
	
	//회원가입 이메일 제목/내용
	//환수에게 물어보자..
	private String emailSubject = "Zerobase 사이트 가입을 축하드립니다.";
	private String emailText = "Zerobase 사이트 가입을 축하드립니다.</p><p>아래 링크를 클릭하셔서 가입을 완료하세요.";
	
//	public String getUserId() {
//		return userId;
//	}
//	
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
//	
//	public String getUserName() {
//		return userName;
//	}
//
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//	
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	
//	public String getPhone() {
//		return phone;
//	}
//
//	public void setPhone(String phone) {
//		this.phone = phone;
//	}
}
