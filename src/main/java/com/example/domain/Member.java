package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	private String memberId;
	private String memberPwd; // (DB의 `password` 컬럼과 매핑됨)
	private String memberName;

}