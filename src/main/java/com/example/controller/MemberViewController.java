package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.client.ArticleClient;
import com.example.domain.Article;

import java.util.Map;
import java.util.Collections;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberViewController {
	private final ArticleClient articleClient;

	/**
	 * 메인 페이지 (게시글 목록 조회 및 렌더링)
	 */
	@GetMapping("/")
	public String home(Model model) {
		log.info("home 메서드 실행 - 게시글 목록 조회 시작");

		List<Article> articleList = Collections.emptyList();

		try {
			// 📌 Feign Client 호출 (ResponseEntity로 받음)
			ResponseEntity<List<Article>> response = articleClient.getArticleList();

			if (response.getStatusCode().is2xxSuccessful()) {
				articleList = response.getBody();
				log.info("게시글 목록 조회 성공: {}개", articleList != null ? articleList.size() : 0);
			} else {
				log.warn("게시글 목록 조회 실패 - Status: {}", response.getStatusCode());
				model.addAttribute("error", "게시글 조회 실패 (상태 코드 오류)");
			}

		} catch (Exception e) {
			// 통신 오류
			log.error("Article Service 연결 실패: {}", e.getMessage());
			model.addAttribute("error", "게시글을 불러올 수 없습니다.");
		}

		// Model에 담기
		model.addAttribute("articleList", articleList);

		return "index"; // templates/index.html 렌더링
	}

	@GetMapping("/user")
	public String getLoginPage() {
		log.info("getLoginPage 메서드 실행");
		return "login-user";
	}

	@GetMapping("/user/regist-page")
	public String getRegistPage() {
		log.info("getRegistPage 메서드 실행");
		return "regist-user";
	}
}
