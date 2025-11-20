package com.example.client;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "article-api", url = "http://article-service:8082/api/article")
public interface ArticleClient {
	
	@GetMapping(value = "/article-list")
	public List<Map<String, String>> getArticleList();
}
