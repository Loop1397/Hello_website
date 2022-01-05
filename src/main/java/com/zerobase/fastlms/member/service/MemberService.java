package com.zerobase.fastlms.member.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.model.ResetPasswordInput;

public interface MemberService extends UserDetailsService {
	
	boolean register(MemberInput parameter);
	
	/**
	 * uuid에 해당하는 계정을 활성화 함.
	 * @param uuid
	 * @return
	 */
	
	boolean emailAuth(String uuid);

	/**
	 * 입력한 이메일로 비밀번호 초기화 정보를 전송하는 메소드 
	 * @param parameter
	 * @return
	 */
	boolean sendResetPassword(ResetPasswordInput parameter);

	/**
	 * 입력받은 uuid에 대해서 password로 초기화 함
	 * @param id
	 * @param password
	 * @return
	 */
	boolean resetPassword(String uuid, String password);

	/**
	 * 입력받은 uuid값이 유효한지 확인
	 * @param uuid
	 * @return
	 */
	boolean checkResetPassword(String uuid);


	
}
