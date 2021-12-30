package com.zerobase.fastcampus.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zerobase.fastcampus.member.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

	Optional<Member> findByEmailAuthKey(String emailAuthKey);
	
}
