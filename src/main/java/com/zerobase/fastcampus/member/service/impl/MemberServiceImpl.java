package com.zerobase.fastcampus.member.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.zerobase.fastcampus.components.MailComponents;
import com.zerobase.fastcampus.member.entity.Member;
import com.zerobase.fastcampus.member.model.MemberInput;
import com.zerobase.fastcampus.member.repository.MemberRepository;
import com.zerobase.fastcampus.member.service.MemberService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;
	private final MailComponents mailComponents;
	
	/**
	 * 회원 가입
	 */
	
	@Override
	public boolean register(MemberInput parameter) {
		// TODO Auto-generated method stub
		
		
		//findById = id를 가지고 데이터가 존재하는지 확인하는 메소드
		Optional<Member> optionalMember = memberRepository.findById(parameter.getUserId());
		
		/**
		 * 현재 해당 ID와 같은 데이터가 존재한지 확인 후, 맞다면 if안에 있는 내용 실행
		 * 여기선 같은 ID를 이용한 회원가입을 막기 위해 바로 클래스를 빠져나가도록 설계
		 */
		
		if(optionalMember.isPresent()) {
			return false;
		}
		
		String uuid = UUID.randomUUID().toString();
		
		Member member = new Member();
		member.setUserId(parameter.getUserId());
		member.setUserName(parameter.getUserName());
		member.setPhone(parameter.getPhone());
		member.setPassword(parameter.getPassword());
		//현재 시간 입력
		member.setRegDt(LocalDateTime.now());
		//단, primary key가 같은 데이터가 들어올 경우 해당 primary key의 다른 정보를 업데이트(덮어쓰기)함.
		//그래서 primary key(여기에선 ID)가 같은 경우 save를 못하도록 해야함
		
		member.setEmailAuthYn(false);
		//UUID~ : 랜덤 String 반
		member.setEmailAuthKey(uuid);
		memberRepository.save(member);	//현재까지 입력된 데이터 저장
		
		String email = parameter.getUserId();
		String subject = "Zerobase 사이트 가입을 축하드립니다.";
		String text = "<p>Zerobase 사이트 가입을 축하드립니다.</p><p>아래 링크를 클릭하셔서 가입을 완료하세요.</p>"
				+ "<div><a href='http://localhost:8080/member/email-auth?id" + uuid + "'>가입 완료</a></div>";
		//? 뒷부분은 '파라미터'라고함
		//프로토콜://도메인(IP)/주소?쿼리스트림(파라미터)
		//파라미터 : 클라이언트에서 서버로 전송되는 정보들
		
		mailComponents.sendMail(email, subject, text);
		
		
		
		
		return true;
	}

}
