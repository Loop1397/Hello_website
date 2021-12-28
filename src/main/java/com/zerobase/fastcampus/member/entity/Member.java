package com.zerobase.fastcampus.member.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

//데이터베이스에 1:1로 매핑되는 Entity클래스(DB의 테이블)
//Entity클래스는 보통 레파지토리(인터페이스)가 같이 따라 옴

//@Data는 lombok의 어노테이션(세터, 게터 만들어줌)
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

}
