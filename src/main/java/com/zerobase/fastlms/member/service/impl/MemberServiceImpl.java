package com.zerobase.fastlms.member.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.zerobase.fastlms.components.MailComponents;
import com.zerobase.fastlms.member.entity.Member;
import com.zerobase.fastlms.member.exception.MemberNotEmailAuthException;
import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.model.ResetPasswordInput;
import com.zerobase.fastlms.member.repository.MemberRepository;
import com.zerobase.fastlms.member.service.MemberService;

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
		
		//패스워드 암호화해서 저장(BCrypt)
		String encPassword = BCrypt.hashpw(parameter.getPassword(), BCrypt.gensalt());
		
		String uuid = UUID.randomUUID().toString();
		
		
		/**
		 * Member member = new Member();
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
		 */
		
		//Builder어노테이션을 이용해서 상단의 코드를 가독성있게 구현 가능
		Member member = Member.builder()
				.userId(parameter.getUserId())
				.userName(parameter.getUserName())
				.phone(parameter.getPhone())
				.password(encPassword)
				.regDt(LocalDateTime.now())
				.emailAuthYn(false)
				.emailAuthKey(uuid)
				.build();
		memberRepository.save(member);
		
		String email = parameter.getUserId();
		String subject = "Zerobase 사이트 가입을 축하드립니다.";
		String text = "<p>Zerobase 사이트 가입을 축하드립니다.</p><p>아래 링크를 클릭하셔서 가입을 완료하세요.</p>"
				+ "<div><a target='_blank' href='http://localhost:8080/member/email_auth?id=" + uuid + "'>가입 완료</a></div>";
		//? 뒷부분은 '파라미터'라고함
		//프로토콜://도메인(IP)/주소?쿼리스트림(파라미터)
		//파라미터 : 클라이언트에서 서버로 전송되는 정보들
		
		//이메일 관련된 부분 관리자페이지에서 바꿀 수 있도록 해보자
		
		mailComponents.sendMail(email, subject, text);
		
		
		
		
		return true;
	}

	@Override
	public boolean emailAuth(String uuid) {
		
		Optional<Member> optionalMember = memberRepository.findByEmailAuthKey(uuid);
		if(!optionalMember.isPresent()) {
			return false;
		}
		
		Member member = optionalMember.get();
		member.setEmailAuthYn(true);
		member.setEmailAuthDt(LocalDateTime.now());
		memberRepository.save(member);
		
		
		return true;
	}

	@Override
	public boolean sendResetPassword(ResetPasswordInput parameter) {
		
		Optional<Member> optionalMember = memberRepository.findByUserIdAndUserName(parameter.getUserId(), parameter.getUserName());
		if(!optionalMember.isPresent()) {
			throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
		}
		
		Member member = optionalMember.get();
		
		String uuid = UUID.randomUUID().toString();
		
		member.setResetPasswordKey(uuid);
		//하루 뒤 까지 유효
		member.setResetPasswordLimitDt(LocalDateTime.now().plusDays(1));
		memberRepository.save(member);
		
		String email = parameter.getUserId();
		String subject = "[Zerobase] 비밀번호 초기화 메일 입니다.";
		String text = "<p>Zerobase 비밀벙호 초기화 메일 입니다.</p>"
				+ "<p>아래 링크를 클릭하셔서 비밀번호를 초기화 해주세요.</p>"
				+ "<div><a target='_blank' href='http://localhost:8080/member/reset/password?id=" + uuid + "'>비밀번호 초기화 링크</a></div>";
		mailComponents.sendMail(email, subject, text);
		
		return true;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Member> optionalMember = memberRepository.findById(username);
		if(!optionalMember.isPresent()) {
			throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
		}
		
		Member member = optionalMember.get();
		//멤버가 이메일 인증을 하지 않았을 시 에러 던지기
		if(!member.isEmailAuthYn()) {
			throw new MemberNotEmailAuthException("이메일을 활성화 한 이후에 로그인해주세요.");
		}
		
		ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		return new User(member.getUserId(), member.getPassword(), grantedAuthorities);
		
	}

	//입력받은 password로 패스워드 초기화
	@Override
	public boolean resetPassword(String uuid, String password) {
		
		Optional<Member> optionalMember = memberRepository.findByResetPasswordKey(uuid);
		if(!optionalMember.isPresent()) {
			throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
		}
		
		Member member = optionalMember.get();
		
		//초기화 날짜가 유효한지 체크
		if(member.getResetPasswordLimitDt() == null) {
			throw new RuntimeException("유효한 날짜가 아닙니다.");
		}
		
		//초기화 날짜가 지나지 않았는지 체크
		if(member.getResetPasswordLimitDt().isBefore(LocalDateTime.now())) {
			throw new RuntimeException("유효한 날짜가 아닙니다.");
		}
		
		
		//바뀐 비밀번호 암호화해서 저장 후 비밀번호 초기화에 사용 된 임시 값 모두 삭제
		String encPassword = BCrypt.hashpw(password, BCrypt.gensalt());
		member.setPassword(encPassword);
		member.setResetPasswordKey("");
		member.setResetPasswordLimitDt(null);
		memberRepository.save(member);
		
		
		return true;
	}

	//입력받은 uuid값이 유효한지 확인
	@Override
	public boolean checkResetPassword(String uuid) {
		Optional<Member> optionalMember = memberRepository.findByResetPasswordKey(uuid);
		if(!optionalMember.isPresent()) {
			return false;
		}
		
		Member member = optionalMember.get();
		
		//초기화 날짜가 유효한지 체크
		if(member.getResetPasswordLimitDt() == null) {
			throw new RuntimeException("유효한 날짜가 아닙니다.");
		}
		
		//초기화 날짜가 지나지 않았는지 체크
		if(member.getResetPasswordLimitDt().isBefore(LocalDateTime.now())) {
			throw new RuntimeException("유효한 날짜가 아닙니다.");
		}
		
		
		return true;
	}

	

	
	
}
