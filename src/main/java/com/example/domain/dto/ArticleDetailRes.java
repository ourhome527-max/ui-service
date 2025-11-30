package com.example.domain.dto;

import java.util.Date;
import java.util.List;

import com.example.domain.FileMeta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticleDetailRes {
	private int id;
	private String title;
	private String content;
	private String writerId;
	private String category;
	private Date regAt;
	private Date modAt;
	private List<FileMeta> files;
}
