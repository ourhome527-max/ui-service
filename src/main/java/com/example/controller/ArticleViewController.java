package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.client.ArticleClient;
import com.example.config.SessionUtil;
import com.example.domain.Member;
import com.example.domain.dto.RegistArticleReq;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ArticleViewController {
	private final ArticleClient articleClient;
	private final SessionUtil sessionUtil;

	@GetMapping("/article/regist")
	public String getRegistArticlePage() {
		return "article/regist-article";
	}

	// 게시글 등록
	@PostMapping("/article/regist")
	public String registArticle(RegistArticleReq request, HttpServletRequest httpRequest) {
		try {
			Member sessionMember = (Member)sessionUtil.getSession(httpRequest);
			if (sessionMember == null) {
				return "redirect:/user/login";
			}
			request.setWriterId(sessionMember.getMemberId());

			// 3. Article-Service로 요청 전송
			ResponseEntity<Void> response = articleClient.registArticle(request);

			if (response.getStatusCode() == HttpStatus.OK) {
				return "redirect:/";
			}

			return "redirect:/article/regist?error=true";

		} catch (Exception e) {
			log.error("게시글 등록 오류", e);
			return "redirect:/article/regist?error=true";
		}
	}
}
