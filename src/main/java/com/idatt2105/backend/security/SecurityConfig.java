package com.idatt2105.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public BCryptPasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .requestMatchers("/**")
        .permitAll() // Permit access to specific endpoints
        .anyRequest()
        .permitAll()
        .and()
        .csrf()
        .disable();
    return http.build(); // Temporary allow access to all endpoints
  }
}
