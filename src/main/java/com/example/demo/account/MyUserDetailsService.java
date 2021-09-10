package com.example.demo.account;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

  
  @Autowired
  UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> result = userRepository.findByUsername(username);

    if (!result.isPresent()) {
      throw new UsernameNotFoundException("用户名不存在");
    }

    User user = result.get();
    user.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoles()));
    return user;
  }

}
