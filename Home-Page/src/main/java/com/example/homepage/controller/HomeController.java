package com.example.homepage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.homepage.model.member.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	@GetMapping("complete")
	private String home(@SessionAttribute(value = "loginMember", required = false) Member loginMember) {
		log.info("성공");
		return "member/logincomplete";
	}
	
//	@GetMapping("/")
//	private String main(@SessionAttribute(value = "loginMember", required = false) Member loginMember) {
//		return "/";
//	}
}
