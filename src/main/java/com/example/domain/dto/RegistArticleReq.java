package com.example.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegistArticleReq {
	private String title;
	private String content;
	private String writerId;
	private String category;
}