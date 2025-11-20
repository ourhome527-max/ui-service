package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.client.ArticleClient;

import java.util.Map;
import java.util.Collections;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ViewController {
	private final ArticleClient articleClient;

	/**
	 * 메인 페이지 (게시글 목록 조회 및 렌더링)
	 */
	@GetMapping("/")
	public String home(Model model) {
		log.info("home 메서드 실행 - 게시글 목록 조회 시작");
		List<Map<String, String>> articleList;

		try {
			// ⭐️ 2. Feign Client를 통해 article-service 호출
			// (GET http://article-service:8082/api/article/article-list)
			articleList = articleClient.getArticleList();
			log.info("게시글 목록 조회 성공: {}개", articleList != null ? articleList.size() : 0);

		} catch (Exception e) {
			// ⭐️ 3. 통신 오류 처리 (서비스가 죽었거나 연결 실패 시)
			log.error("Article Service 연결 실패: {}", e.getMessage());
			articleList = Collections.emptyList(); // 빈 리스트로 처리하여 페이지가 깨지지 않게 함
			model.addAttribute("error", "게시글을 불러올 수 없습니다.");
		}

		// ⭐️ 4. 조회한 목록을 Model에 담아 index.html로 전달
		// (index.html에서는 th:each="article : ${articleList}"로 사용)
		model.addAttribute("articleList", articleList);

		return "index"; // templates/index.html 렌더링
	}

	@GetMapping("/user")
	public String getLoginPage() {
		log.info("getLoginPage 메서드 실행");
		return "login-user";
	}
}
