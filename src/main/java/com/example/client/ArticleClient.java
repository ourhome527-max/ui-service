package com.example.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.domain.Article;
import com.example.domain.dto.RegistArticleReq;

@FeignClient(name = "article-api", url = "http://article-service:8082/api/article")
public interface ArticleClient {

//	@GetMapping(value = "/article-list")
//	public List<Map<String, String>> getArticleList();

	@GetMapping("/article-list")
	ResponseEntity<List<Article>> getArticleList();

	@PostMapping("/regist")
	ResponseEntity<Void> registArticle(@RequestBody RegistArticleReq request);

	@GetMapping("/{articleId}")
    ResponseEntity<Article> getArticleById(@PathVariable("articleId") int articleId);
}
