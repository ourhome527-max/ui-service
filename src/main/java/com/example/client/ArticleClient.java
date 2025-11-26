package com.example.client;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.domain.dto.RegistArticleReq;

@FeignClient(name = "article-api", url = "http://article-service:8082/api/article")
public interface ArticleClient {

	@GetMapping(value = "/article-list")
	public List<Map<String, String>> getArticleList();

	@PostMapping("/article/regist")
	public ResponseEntity<Void> registArticle(@RequestBody RegistArticleReq request);
}
