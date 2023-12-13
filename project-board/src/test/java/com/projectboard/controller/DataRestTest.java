package com.projectboard.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@Disabled("Spring Data REST 통합테스트는 제외시킴")
@DisplayName("Data REST - API 테스트")
@Transactional
@AutoConfigureMockMvc
@SpringBootTest
public class DataRestTest {

	private MockMvc mvc;

	public DataRestTest(@Autowired MockMvc mvc) {
		this.mvc = mvc;
	}
	
	@Test
	void test() throws Exception {
		mvc.perform(get("/api/articles"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
	}
	
}
