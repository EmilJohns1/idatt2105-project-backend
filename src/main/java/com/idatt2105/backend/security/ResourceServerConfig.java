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
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.web.bind.annotation.RequestMapping;

import com.idatt2105.backend.controller.CommentController;
import com.idatt2105.backend.controller.PasswordResetTokenController;
import com.idatt2105.backend.controller.QuestionController;
import com.idatt2105.backend.controller.QuizController;

/** Configuration for the Resource Server. */
@Configuration
@EnableWebSecurity
public class ResourceServerConfig {
  private final JwtDecoder jwtDecoder;
  private final HttpSessionRequestCache requestCache;

  @Autowired
  public ResourceServerConfig(JwtDecoder jwtDecoder, HttpSessionRequestCache requestCache) {
    this.jwtDecoder = jwtDecoder;
    this.requestCache = requestCache;
  }

  /**
   * Configures the security filter chain for the Resource Server.
   *
   * @param http The HttpSecurity object to configure
   * @return The SecurityFilterChain object
   * @throws Exception If an error occurs
   */
  @Bean
  @Order(2)
  public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .cors(Customizer.withDefaults())
        // .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
        .authorizeHttpRequests(
            authorize -> {
              // This gets the base api path for the quiz controller from the "RequestMapping"
              // annotation.
              String quizApiPath =
                  QuizController.class.getAnnotation(RequestMapping.class).value()[0];
              String commentApiPath =
                  CommentController.class.getAnnotation(RequestMapping.class).value()[0];
              String passwordResetPath =
                  PasswordResetTokenController.class.getAnnotation(RequestMapping.class).value()[0];
              String questionApiPath =
                  QuestionController.class.getAnnotation(RequestMapping.class).value()[0];
              authorize
                  .requestMatchers("/api/user/register")
                  .permitAll()
                  .requestMatchers("/oauth2/authorize")
                  .permitAll()
                  .requestMatchers("/connect/logout")
                  .permitAll()
                  .requestMatchers("/login.html")
                  .permitAll()
                  .requestMatchers("/stylesheet.css")
                  .permitAll()
                  .requestMatchers("/favicon-32x32.png")
                  .permitAll()
                  .requestMatchers(quizApiPath + "/categories")
                  .permitAll()
                  .requestMatchers(HttpMethod.GET, quizApiPath)
                  .permitAll()
                  .requestMatchers(HttpMethod.GET, quizApiPath + "/*")
                  .permitAll()
                  .requestMatchers(HttpMethod.GET, quizApiPath + "/users/*")
                  .permitAll()
                  .requestMatchers(HttpMethod.GET, quizApiPath + "/all/tags")
                  .permitAll()
                  .requestMatchers(HttpMethod.GET, commentApiPath)
                  .permitAll()
                  .requestMatchers(HttpMethod.GET, commentApiPath + "/*")
                  .permitAll()
                  .requestMatchers(HttpMethod.GET, commentApiPath + "/quiz/*")
                  .permitAll()
                  .requestMatchers(HttpMethod.GET, commentApiPath + "/user/*")
                  .permitAll()
                  .requestMatchers("/api/sendEmail")
                  .permitAll()
                  .requestMatchers(HttpMethod.POST, passwordResetPath + "/generate-token")
                  .permitAll()
                  .requestMatchers(HttpMethod.GET, passwordResetPath + "/find-by-token")
                  .permitAll()
                  .requestMatchers(HttpMethod.GET, questionApiPath + "/get/all/*")
                  .permitAll()
                  .requestMatchers(HttpMethod.PUT, "/api/password-reset/reset-password")
                  .permitAll()
                  .requestMatchers("/swagger-ui/index.html")
                  .hasRole("ADMIN")
                  .anyRequest()
                  .authenticated();
            })
        .formLogin(
            custom -> {
              custom
                  .loginPage("/login.html")
                  .usernameParameter("username")
                  .passwordParameter("password")
                  .loginProcessingUrl("/login")
                  .successHandler(
                      (request, response, authentication) -> {
                        System.out.println(authentication.getAuthorities().iterator().next());
                        var cachedRequest = requestCache.getRequest(request, response);
                        String authorizeRequestUrl =
                            (String) request.getSession().getAttribute("ORIGINAL_REQUEST_URL");
                        response.sendRedirect(
                            authorizeRequestUrl != null
                                ? authorizeRequestUrl
                                : cachedRequest.getRedirectUrl());
                      });
            })
        // .formLogin(Customizer.withDefaults())
        .oauth2ResourceServer(
            oauth2ResourceServer ->
                oauth2ResourceServer.jwt(
                    jwt ->
                        jwt.decoder(jwtDecoder)
                            .jwtAuthenticationConverter(jwtAuthenticationConverter())));

    return http.build();
  }

  /**
   * Configures the JwtAuthenticationConverter.
   *
   * @return The JwtAuthenticationConverter
   */
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
