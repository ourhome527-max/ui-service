//package com.example.controller;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.example.client.MemberClient;
//import com.example.domain.Member;
//import com.example.domain.dto.UserLoginReq;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
///**
// * ⭐️ BFF(Backend-for-Frontend) 역할: 사용자 브라우저의 로그인 요청을 받아, MemberClient를 통해
// * Member Service로 전달하고 결과를 처리합니다.
// */
//@Controller
//@RequiredArgsConstructor
//@RequestMapping("/user") // 브라우저에서 요청하는 /user/login 경로
//@Slf4j
//public class LoginController {
//
//	private final MemberClient memberClient;
//
//	@PostMapping("/login")
//	public ResponseEntity<Member> login(@RequestBody UserLoginReq request) {
//		log.info("UI Service: Attempting login for user: {}", request.getUserId());
//
//		try {
//			// ⭐️ 1. OpenFeign을 통해 Member Service의 API 호출
//			ResponseEntity<Member> apiResponse = memberClient.login(request);
//
//			// ⭐️ 2. 응답이 200 OK일 경우
//			if (apiResponse.getStatusCode() == HttpStatus.OK) {
//				Member loginMember = apiResponse.getBody();
//				log.info("UI Service: Login success, User: {}", loginMember.getUserName());
//
//				// 3. 브라우저에 Member 객체와 함께 200 OK 반환
//				return ResponseEntity.ok(loginMember);
//			} else {
//				// 4. (예: 401 Unauthorized나 400 Bad Request가 뜬 경우)
//				log.warn("UI Service: Login failed with status: {}", apiResponse.getStatusCode());
//				return ResponseEntity.status(apiResponse.getStatusCode()).build();
//			}
//
//		} catch (Exception e) {
//			// 5. Member Service와의 통신 실패, 연결 거부 등
//			log.error("UI Service: Communication error with Member Service: {}", e.getMessage());
//			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
//		}
//	}
//}