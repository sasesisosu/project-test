package com.projectboard.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@DisplayName("View Controller - 인증")
@WebMvcTest
public class AuthControllerTest {
	
	private final MockMvc mvc;

	@Autowired
	public AuthControllerTest(MockMvc mvc) {
		this.mvc = mvc;
	}

	@DisplayName("[view][GET] 로그인 페이지 정상 호출")
	@Test
	public void givenNothing_whenTryToLogin_thenReturnLoginView() throws Exception {
		// Given

		// When & Then
		mvc.perform(get("/login"))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML));

	}
}
