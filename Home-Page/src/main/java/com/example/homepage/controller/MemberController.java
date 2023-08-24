package com.example.homepage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.homepage.model.login.Login;
import com.example.homepage.model.member.JoinForm;
import com.example.homepage.model.member.Member;
import com.example.homepage.repository.MemberMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
//@RequestMapping("member")
@Controller
public class MemberController {
	
	private final MemberMapper memberMapper;
	
	@GetMapping("login")
	public String loginForm(Model model) {
		log.info("login.html 실행");
		
		// 로그인 껍데기
		model.addAttribute("login", new Login());
		
		return "member/login";
	}
	
	@PostMapping("login")
	public String login(@Validated @ModelAttribute("loginForm") Login login,
			HttpServletResponse response, HttpServletRequest request) {
		
		log.info("입력한 값: {}", login);
		Member member = memberMapper.findMember(login.getMember_id());
		if (member == null || !login.getMember_password().equals(member.getMember_password())) {
			return "member/loginfailed";
		}
		
		// Request 객체에서 Session 객체를 꺼내온다.
		HttpSession session = request.getSession();
		// Session 에 'loginMember' 라는 이름으로 Member 객체를 저장한다.
		session.setAttribute("loginMember", member);
		
		return "member/logincomplete";
	}
	
	@GetMapping("join")
	public String joinForm(Model model) {
		log.info("join.html 실행");
		
		// 회원가입 껍데기
		model.addAttribute("join", new Member());
		
		return "member/join";
	}
	
	@PostMapping("join")
	public String join(@Validated @ModelAttribute("join") JoinForm joinForm) {
		
		log.info("입력한 값: {}", joinForm);
		
		Member member = memberMapper.findMember(joinForm.getMember_id());
		
		if (member != null) {
			log.info("이미 가입된 아이디");
			return "member/join";
		}
		
		memberMapper.saveMember(joinForm);
		
		log.info("{}의 정보로 가입 완료", joinForm);
		
		return "redirect:/";
	}
	
	// 로그아웃
	@GetMapping("logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		return "redirect:/";
	}
	
}
