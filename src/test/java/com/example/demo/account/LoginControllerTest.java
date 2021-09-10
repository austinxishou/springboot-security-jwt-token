package com.example.demo.account;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

  @Autowired
	private MockMvc mockMvc;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Test
  void testLogin() throws Exception {
    mockMvc.perform(post("/user/login").param("username", "austindev").param("password", "123456")).andDo(print()).andExpect(status().isOk());
  }

  @Test
  void testLogout() {
    // this.mockMvc.perform(get("/greeting").param("name", "Spring Community"))
		// 		.andDo(print()).andExpect(status().isOk())
		// 		.andExpect(jsonPath("$.content").value("Hello, Spring Community!"));
  }

  @Test
  void testPasswordEncode() {
    String password = passwordEncoder.encode("123456");
    System.out.println("password encoded: " + password);
  }
}
