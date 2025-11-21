package com.example.controller;

import java.lang.reflect.Member;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.client.MemberClient;
import com.example.domain.dto.RegistMemberReq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
	private final MemberClient memberClient;

	@PostMapping("/user/regist")
	public String registMember(RegistMemberReq request) {
		try {
			ResponseEntity apiResponse = memberClient.registMember(request);
			// 회원가입 성공
			if (apiResponse.getStatusCode() == HttpStatus.OK) {
				return "redirect:/index.html"; // ⭐ index.html로 이동
			}

			// 실패 → 회원가입 페이지로 다시 보내기 (또는 에러 페이지)
			return "redirect:/user/regist?error=true";

		} catch (Exception e) {
			log.error("회원가입 오류: ", e);
			return "redirect:/user/regist?error=true";
		}
	}
}
