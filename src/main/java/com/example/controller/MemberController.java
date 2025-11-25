package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.client.MemberClient;
import com.example.config.SessionUtil;
import com.example.domain.Member;
import com.example.domain.dto.MemberLoginReq;
import com.example.domain.dto.RegistMemberReq;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
	private final MemberClient memberClient;
	private final SessionUtil sessionUtil;

	/*
	 * 회원 등록
	 */
	@PostMapping("/user/regist")
	public String registMember(RegistMemberReq request) {
		try {
			ResponseEntity apiResponse = memberClient.registMember(request);
			// 회원가입 성공
			if (apiResponse.getStatusCode() == HttpStatus.OK) {
				return "redirect:/";
			}

			// 실패 → 회원가입 페이지로 다시 보내기 (또는 에러 페이지)
			return "redirect:/user/regist-page?error=true";

		} catch (Exception e) {
			log.error("회원가입 오류: ", e);
			return "redirect:/user/regist-page?error=true";
		}
	}

	/*
	 * 로그인
	 */
	@PostMapping("/user/login")
	public String login(MemberLoginReq request, HttpServletRequest servletRequset) {
		try {
			ResponseEntity<Member> apiResponse = memberClient.login(request);
			if (apiResponse.getStatusCode() == HttpStatus.OK) {
				Member loginMember = apiResponse.getBody();
				sessionUtil.createSession(servletRequset, loginMember);
				return "redirect:/";
			}
			return "redirect:/user/login?error=true";
		} catch (Exception e) {
			log.error("로그인 오류: ", e);
			return "redirect:/user/login?error=true";
		}
	}

	/*
	 * 로그아웃
	 */
	@GetMapping("/logout")
	public String logoutByBtn(HttpServletRequest request) {
		log.info("logoutByBtn 메서드 실행");
		sessionUtil.expireSession(request);
		return "redirect:/";
	}
}
