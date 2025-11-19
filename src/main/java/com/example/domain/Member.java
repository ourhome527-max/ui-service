package com.example.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Member {
	private String userId;
	private String userPwd; // (DB의 `password` 컬럼과 매핑됨)
	private String userName;

	public Member(String userId, String userPwd, String userName) {
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
	}
}