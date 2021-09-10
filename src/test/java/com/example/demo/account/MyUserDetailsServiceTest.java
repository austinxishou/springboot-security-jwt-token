package com.example.demo.account;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@SpringBootTest
public class MyUserDetailsServiceTest {
  @Autowired
  UserDetailsService userDetailsService;

  @Test
  void testLoadUserByUsername() {
    UserDetails userDetails = userDetailsService.loadUserByUsername("admin");
    Assertions.assertThat((AuthorityUtils.authorityListToSet(userDetails.getAuthorities()))).contains("ROLE_ADMIN");
  }
}
