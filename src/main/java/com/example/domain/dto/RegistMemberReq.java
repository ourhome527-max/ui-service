package com.example.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegistMemberReq {
	private String userId;
	private String userPwd;
	private String userName;
}
