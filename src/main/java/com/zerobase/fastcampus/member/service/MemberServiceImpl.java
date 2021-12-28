package com.zerobase.fastcampus.member.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zerobase.fastcampus.member.entity.Member;
import com.zerobase.fastcampus.member.model.MemberInput;
import com.zerobase.fastcampus.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;
	
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
		
		
		Member member = new Member();
		member.setUserId(parameter.getUserId());
		member.setUserName(parameter.getUserName());
		member.setPhone(parameter.getPhone());
		member.setPassword(parameter.getPassword());
		member.setRegDt(LocalDateTime.now());
		memberRepository.save(member);	//데이터 저장
		//단, primary key가 같은 데이터가 들어올 경우 해당 primary key의 다른 정보를 업데이트(덮어쓰기)함.
		//그래서 primary key(여기에선 ID)가 같은 경우 save를 못하도록 해야함
		
		return false;
	}

}
