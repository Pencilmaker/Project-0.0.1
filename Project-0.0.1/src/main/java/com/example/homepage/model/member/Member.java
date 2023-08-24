package com.example.homepage.model.member;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Member {
	private String member_id;
	private String member_password;
	private String member_name;
	private String member_nickname;
	private String member_birth;
	
	private String member_gender;
	
	private String member_email;
	private String question;
	private String answer;
	private LocalDateTime create_date;
	private LocalDateTime logined_time;
}

