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
	private String phone;
	private String password;
	
	private String newPasword;
	
	private String zipcode;
	private String addr;
	private String addrDetail;
	
	
	/**
	 * 회원가입 이메일 제목/내용
	 * 데이터가 바뀔 때 마다 서버에 저장하기 위해 static 사용
	 * private static의 영향인진 몰라도 lombok의 @Data를 통한 게터, 세터가 생성되지 않던데 이 부분은 나중에 찾아봅시다
	 */
	private static String emailSubject = "Zerobase 사이트 가입을 축하드립니다.";
	private static String emailText = "Zerobase 사이트 가입을 축하드립니다.</p><p>아래 링크를 클릭하셔서 가입을 완료하세요.";
	
	public void setEmailSubject(String parameter) {
		this.emailSubject = parameter;
		
	}

	public String getEmailSubject() {
		return this.emailSubject;
	}
	
	public void setEmailText(String parameter) {
		this.emailText = parameter;
		
	}

	public String getEmailText() {
		return this.emailText;
	}
	
}
