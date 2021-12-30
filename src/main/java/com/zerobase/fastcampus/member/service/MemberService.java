package com.zerobase.fastcampus.member.service;

import com.zerobase.fastcampus.member.model.MemberInput;

public interface MemberService {
	
	boolean register(MemberInput parameter);
	
	/**
	 * uuid에 해당하는 계정을 활성화 함.
	 * @param uuid
	 * @return
	 */
	
	boolean emailAuth(String uuid);
	
}
