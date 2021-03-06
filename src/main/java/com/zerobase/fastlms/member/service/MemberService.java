package com.zerobase.fastlms.member.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.zerobase.fastlms.admin.course.model.ServiceResult;
import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.member.entity.Member;
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

	/**
	 * 회원 목록 확인/리턴(관리자에서만 사용 가능)
	 * @return
	 */
	List<MemberDto> list(MemberParam parameter);

	/**
	 * 회원 상세 정보를 가져오는 메소드
	 * @param userId
	 * @return
	 */
	MemberDto detail(String userId);
	
	/**
	 * 회원 상태 변경
	 */
	boolean updateStatus(String userId, String userStatus);
	
	/**
	 * 회원 비밀번호 초기화
	 */
	boolean updatePassword(String userId, String password);
	
	/**
	 * 회원 정보 페이지 내 비밀번호 변경 기능 
	 * @param parameter
	 * @return
	 */
	ServiceResult updateMemberPassword(MemberInput parameter);

	/**
	 * 회원 정보 업데이트(수정)
	 * @param parameter
	 * @return
	 */
	ServiceResult updateMember(MemberInput parameter);

	/**
	 * 회원을 탈퇴시켜주는 메소드
	 * @param userId
	 * @return
	 */
	ServiceResult withdraw(String userId, String password);
}
