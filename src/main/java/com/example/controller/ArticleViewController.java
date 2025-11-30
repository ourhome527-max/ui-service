package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.example.client.ArticleClient;
import com.example.config.SessionUtil;
import com.example.domain.Article;
import com.example.domain.Member;
import com.example.domain.dto.ArticleDetailRes;
import com.example.domain.dto.RegistArticleReq;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

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
	// HTML Form에서 넘어오는 데이터는 @ModelAttribute(생략 가능), 파일은 @RequestParam 혹은 인자로 받음
	public String registArticle(RegistArticleReq request,
			@RequestParam(value = "files", required = false) @RequestPart(value = "files", required = false) List<MultipartFile> files,
			HttpServletRequest httpRequest) {
		log.info("게시글 등록 요청: title={}, writerId={}, files={}", request.getTitle(), request.getWriterId(),
				(files != null ? files.size() : 0));
		try {
			Member sessionMember = (Member) sessionUtil.getSession(httpRequest);
			if (sessionMember == null) {
				return "redirect:/user/login";
			}
			request.setWriterId(sessionMember.getMemberId());

			// 변경점: request DTO와 함께 파일 리스트도 Feign Client로 전달
			ResponseEntity<Void> response = articleClient.registArticle(request, files);

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
			ResponseEntity<ArticleDetailRes> response = articleClient.getArticleById(articleId);

			if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
				ArticleDetailRes article = response.getBody();
				// [Log] 3. 실제 받아온 데이터 확인 (제목, 작성자, 파일 목록 등)
				log.info("게시글 데이터 수신 성공 -> ID: {}, 제목: {}, 작성자: {}", article.getId(), article.getTitle(),
						article.getWriterId());

				if (article.getFiles() != null) {
					log.info("첨부파일 개수: {}", article.getFiles().size());
					article.getFiles().forEach(file -> log.info(" - 파일정보: id={}, path={}, name={}", file.getFileId(),
							file.getPath(), file.getOriginalName()));
				} else {
					log.info("첨부파일 없음 (files list is null)");
				}
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
