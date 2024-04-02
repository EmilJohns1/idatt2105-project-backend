package com.idatt2105.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig {
  private final JwtDecoder jwtDecoder;

  @Autowired
  public ResourceServerConfig(JwtDecoder jwtDecoder) {
    this.jwtDecoder = jwtDecoder;
  }

  @Bean
  @Order(2)
  public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .cors(Customizer.withDefaults())
        // .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
        .authorizeHttpRequests(
            authorize ->
                authorize
                    .requestMatchers("/api/user/register")
                    .permitAll()
                    .requestMatchers("/oauth2/authorize")
                    .permitAll()
                    .requestMatchers("/connect/logout")
                    .permitAll()
                    .requestMatchers("/login")
                    .permitAll()
                    .requestMatchers("/api/quizzes/categories")
                    .permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/quizzes")
                    .permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/quizzes/*")
                    .permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/quizzes/users/*")
                    .permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/quizzes/all/tags")
                    .permitAll()
                    .anyRequest()
                    .authenticated())
        .formLogin(Customizer.withDefaults())
        .oauth2ResourceServer(
            oauth2ResourceServer ->
                oauth2ResourceServer.jwt(
                    jwt ->
                        jwt.decoder(jwtDecoder)
                            .jwtAuthenticationConverter(jwtAuthenticationConverter())));

    return http.build();
  }

  private JwtAuthenticationConverter jwtAuthenticationConverter() {
    JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter =
        new JwtGrantedAuthoritiesConverter();
    grantedAuthoritiesConverter.setAuthoritiesClaimName(
        "roles"); // Customize according to your JWT structure
    grantedAuthoritiesConverter.setAuthorityPrefix(
        ""); // Use this to customize the prefix for authorities (default is "SCOPE_")

    JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
    jwtConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);

    return jwtConverter;
  }
}
