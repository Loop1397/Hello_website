package com.zerobase.fastcampus.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zerobase.fastcampus.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

}
