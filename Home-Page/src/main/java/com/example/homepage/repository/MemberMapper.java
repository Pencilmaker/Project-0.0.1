package com.example.homepage.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.homepage.model.member.JoinForm;
import com.example.homepage.model.member.Member;

@Mapper
public interface MemberMapper {
	
    void saveMember(JoinForm joinForm);
    
    Member findMember(String member_id);
    
}
