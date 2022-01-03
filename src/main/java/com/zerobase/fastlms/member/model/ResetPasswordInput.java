package com.zerobase.fastlms.member.model;

//lombok : getter, setter를 자동으로 만들어주는 라이브러리
import lombok.Data;
import lombok.ToString;

//정보를 받기 위한 모뎀
@ToString
@Data
public class ResetPasswordInput {
	
	private String userId;
	private String userName;
	
	//파라미터 받아오기 위한 id변수
	private String id;
	private String password;
}
