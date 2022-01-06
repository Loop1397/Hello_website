package com.zerobase.fastlms.member.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//데이터베이스에 1:1로 매핑되는 Entity클래스(DB의 테이블)
//Entity클래스는 보통 레파지토리(인터페이스)가 같이 따라 옴
//application.properties의 설정에 따라 이곳에 변수를 만들면 DB의 테이블도 자동으로 업데이트 될 수도 있음
/**
 * @Data는 lombok의 어노테이션(세터, 게터 만들어줌)
 * @Builder, @NoArgsConstructor, @AllArgsConstructor는 생성자와 관련된 lombok의 어노테이션 
 * @author nochan-u
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Member {
	//Attribute key
	@Id
	private String userId;
	
	private String userName;
	private String phone;
	private String password;
	//아이디 생성 일자
	private LocalDateTime regDt;	

	//이메일 인증 했는지 안했는지 저장
	private boolean emailAuthYn;
	//이메일 인증 키 저장
	private String emailAuthKey;
	//이메일 인증 한 일자 저장
	private LocalDateTime emailAuthDt;
	
	//패스워드 초기화 시 사용할 키
	private String resetPasswordKey;
	//패스워드 초기화 가능 시간 저장 용 변수
	private LocalDateTime resetPasswordLimitDt;

	//관리자 여부 지정 or 회원에 따른 역할 지정
	//준회원, 정회원, 특별회원, 관리자 등등. (ROLE_SEMI_USER, ROLE_USER, ROLE_SPECIAL_USER, ROLE_ADMIN...)
	private boolean adminYn;
}
