package com.zerobase.fastcampus.member.model;

//lombok : getter, setter를 자동으로 만들어주는 라이브러리
import lombok.Data;
import lombok.ToString;

//정보를 받기 위한 모뎀
@ToString
@Data
public class MemberInput {
	
	private String userId;
	private String userName;
	private String password;
	private String phone;
	
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
