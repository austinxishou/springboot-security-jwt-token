package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
  
  @GetMapping("/welcome")
  public ResponseEntity<?> welcome() {

    return ResponseEntity.ok("welcome!");
  }

  @GetMapping("/user/info")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<?> userInfo() {

    return ResponseEntity.ok("Hello, user!");
  }

  @GetMapping("/admin/info")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> adminInfo() {

    return ResponseEntity.ok("Hello, Admin!");
  }
}
