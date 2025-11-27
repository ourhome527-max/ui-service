package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.client.ArticleClient;
import com.example.config.SessionUtil;
import com.example.domain.Article;
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
			Member sessionMember = (Member) sessionUtil.getSession(httpRequest);
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

	@GetMapping("/article/detail/{articleId}")
	public String getArticleDetail(@PathVariable("articleId") int articleId, Model model) {
		try {
			ResponseEntity<Article> response = articleClient.getArticleById(articleId);

			if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
				model.addAttribute("article", response.getBody());
				return "article/detail-article";
			} else {
				return "redirect:/?error=notfound";
			}

		} catch (Exception e) {
			log.error("게시글 상세 조회 오류: {}", e.getMessage());
			return "redirect:/?error=true";
		}
	}

}
