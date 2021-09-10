package com.example.demo.account;

import java.util.Collections;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;

@SpringBootTest
public class JwtTokenUtilTest {
  @Autowired
  JwtTokenUtil jwtTokenUtil;

  @Test
  void testGenerateToken() {
    User user = new User("austindev", "123456", Collections.emptyList());
    String token = jwtTokenUtil.generateToken(user);
    Assertions.assertThat(jwtTokenUtil.validateToken(token, user)).isTrue();
    Assertions.assertThat(jwtTokenUtil.isTokenExpired(token)).isFalse();
  }
}
