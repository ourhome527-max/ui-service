package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.client.ArticleClient;
import com.example.client.MemberClient;
import com.example.config.SessionUtil;
import com.example.domain.dto.RegistArticleReq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ArticleViewController {
	private final ArticleClient articleClient;

	@GetMapping("/article/regist")
	public String getRegistArticlePage() {
		return "article/regist-article";
	}

	// 게시글 등록
	@PostMapping("/article/regist")
	public String registArticle(RegistArticleReq request) {
		try {
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
