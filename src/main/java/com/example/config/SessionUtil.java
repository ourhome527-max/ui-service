package com.example.config;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Component
public class SessionUtil {
	private static final String SESSION_KEY = "loginUser";

	/*
	 * 세션 생성
	 */
	public void createSession(HttpServletRequest request, Object userObject) {
		HttpSession session = request.getSession();
		session.setAttribute(SESSION_KEY, userObject);
	}

	/*
	 * 세션 조회
	 */
	public Object getSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session == null) {
			return null;
		}
		return session.getAttribute(SESSION_KEY);
	}

	/*
	 * 세션 만료
	 */
	public void expireSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
	}
}
