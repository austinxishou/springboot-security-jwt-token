package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jayway.jsonpath.JsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTests {
  @Autowired
  MockMvc mockMvc;

  @Test
  void testWelcome() throws Exception {
    // 未授权 401
    // 权限不足 403
    // 授权过期 401
    MvcResult userResult = mockMvc.perform(post("/user/login").param("username", "austindev").param("password", "123456"))
    .andDo(print()).andExpect(status().isOk()).andReturn();
    String userToken = JsonPath.read(userResult.getResponse().getContentAsString(), "$.token");

    MvcResult adminResult = mockMvc.perform(post("/user/login").param("username", "admin").param("password", "123456"))
    .andDo(print()).andExpect(status().isOk()).andReturn();
    String adminToken = JsonPath.read(adminResult.getResponse().getContentAsString(), "$.token");

    mockMvc.perform(get("/welcome")).andDo(print()).andExpect(status().isOk());
    
    mockMvc.perform(get("/user/info")).andDo(print()).andExpect(status().is(401));
    mockMvc.perform(get("/user/info").header("Authorization", "Bearer " + userToken)).andDo(print()).andExpect(status().isOk());
    mockMvc.perform(get("/admin/info").header("Authorization", "Bearer " + userToken)).andDo(print()).andExpect(status().is(403));

    mockMvc.perform(get("/user/info").header("Authorization", "Bearer " + adminToken)).andDo(print()).andExpect(status().isOk());
    mockMvc.perform(get("/admin/info").header("Authorization", "Bearer " + adminToken)).andDo(print()).andExpect(status().isOk());
  }
}
