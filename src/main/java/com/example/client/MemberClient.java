package com.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.domain.Member;
import com.example.domain.dto.RegistMemberReq;
import com.example.domain.dto.MemberLoginReq;

@FeignClient(name = "member-api", url = "http://member-service:8081/api/members")
public interface MemberClient {
	
	@PostMapping("/login")
	public ResponseEntity<Member> login(@RequestBody MemberLoginReq request);
	
	@PostMapping("/regist")
	public ResponseEntity registMember(@RequestBody RegistMemberReq request);
}
