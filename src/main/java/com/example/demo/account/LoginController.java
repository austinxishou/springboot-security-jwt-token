package com.example.demo.account;

import java.util.Collection;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class LoginController {

  private final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

  @Autowired
  UserDetailsService userDetailsService;
  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  JwtTokenUtil jwtTokenUtil;

  @PostMapping("login")
  public ResponseEntity<?> login(String username, String password) {
    LOGGER.info("用户登录: " + username);
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    if (!passwordEncoder.matches(password, userDetails.getPassword())) {
      throw new BadCredentialsException("密码不正确");
    }
    String token = jwtTokenUtil.generateToken(userDetails);
    return ResponseEntity.ok(Collections.singletonMap("token", token));
  }

  @PostMapping("logout")
  public void logout() {

  }

  @PostMapping("refreshToken")
  public void refreshToken() {

  }
}
