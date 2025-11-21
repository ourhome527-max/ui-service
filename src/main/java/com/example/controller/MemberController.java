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
	public ResponseEntity registMember(RegistMemberReq request) {
		try {
			ResponseEntity apiResponse = memberClient.registMember(request);
			if (apiResponse.getStatusCode() == HttpStatus.OK) {
				return ResponseEntity.ok(null);
			} else {
				return ResponseEntity.status(apiResponse.getStatusCode()).build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
	}
}
