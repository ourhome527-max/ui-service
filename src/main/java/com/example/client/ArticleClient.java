package com.example.client;

import java.util.List;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.example.config.FeignConfig;
import com.example.domain.Article;
import com.example.domain.dto.ArticleDetailRes;
import com.example.domain.dto.RegistArticleReq;

@FeignClient(name = "article-api", url = "http://article-service:8082/api/article")
public interface ArticleClient {

//	@GetMapping(value = "/article-list")
//	public List<Map<String, String>> getArticleList();

	@GetMapping("/article-list")
	ResponseEntity<List<Article>> getArticleList();

	@PostMapping(value = "/regist", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	ResponseEntity<Void> registArticle(@RequestPart RegistArticleReq request,
			@RequestPart(value = "files", required = false) List<MultipartFile> files);

	@GetMapping("/{articleId}")
	ResponseEntity<ArticleDetailRes> getArticleById(@PathVariable("articleId") int articleId);
}
