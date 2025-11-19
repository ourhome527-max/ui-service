package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ViewController {

	@GetMapping("/user/login-user")
	public String getLoginPage() {
		log.info("getLoginPage 메서드 실행");
		return "login-user";
	}
}
