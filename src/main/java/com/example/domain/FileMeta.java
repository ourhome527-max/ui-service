package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FileMeta {
	private int fileId;
	private int articleId; 
	private String originalName;
	private String savedName;
	private String path;
	private Long size;
	private String contentType;
	private String createdAt;
}