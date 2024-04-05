package com.idatt2105.backend.security;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** Configuration for the Security. */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
  /**
   * Configures the security filter chain for the Resource Server.
   *
   * @param http The HttpSecurity object to configure
   * @return The SecurityFilterChain object
   * @throws Exception If an error occurs
   */
  @Bean
  @Order(3)
  public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .cors(Customizer.withDefaults())
        .requiresChannel(requiresChannel -> requiresChannel.anyRequest().requiresSecure())
        .formLogin(Customizer.withDefaults());
    return http.build();
  }

  /**
   * Configures the password encoder.
   *
   * @return (BCryptPasswordEncoder) The password encoder
   */
  @Bean
  public BCryptPasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * Configures the CORS settings.
   *
   * @return (WebMvcConfigurer) The CORS settings
   */
  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/**")
            .allowedOrigins("https://localhost:5173")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
            .allowedHeaders("*");
      }
    };
  }

  /**
   * Configures the servlet container.
   *
   * @return (ServletWebServerFactory) The servlet container
   */
  @Bean
  public ServletWebServerFactory servletContainer() {
    // Configure the factory however you need
    TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
    tomcat.addAdditionalTomcatConnectors(redirectConnector());
    return tomcat;
  }

  /**
   * Redirects HTTP requests to HTTPS.
   *
   * @return (Connector) The connector that redirects HTTP requests to HTTPS.
   */
  private Connector redirectConnector() {
    Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
    connector.setScheme("http");
    connector.setPort(8080); // The port you want to redirect from
    connector.setSecure(false);
    connector.setRedirectPort(8443); // The port you want to redirect to (HTTPS port)
    ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
    return connector;
  }

  /**
   * Configures the session registry.
   *
   * @return (SessionRegistry) The session registry
   */
  @Bean
  public SessionRegistry sessionRegistry() {
    return new SessionRegistryImpl();
  }

  /**
   * Configures the HttpSessionEventPublisher.
   *
   * @return (HttpSessionEventPublisher) The HttpSessionEventPublisher
   */
  @Bean
  public HttpSessionEventPublisher httpSessionEventPublisher() {
    return new HttpSessionEventPublisher();
  }

  /**
   * Configures the request cache.
   *
   * @return (HttpSessionRequestCache) The request cache
   */
  @Bean
  public HttpSessionRequestCache requestCache() {
    return new HttpSessionRequestCache();
  }
}
