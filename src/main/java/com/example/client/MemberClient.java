package com.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.domain.Member;
import com.example.domain.dto.UserLoginReq;

@FeignClient(name = "member-api", url = "http://member-service:8081/api/members")
public interface MemberClient {
	@PostMapping("/login")
	public ResponseEntity<Member> login(@RequestBody UserLoginReq request);
}
