package com.idatt2105.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/** Configuration for sending emails. */
@Configuration
public class MailConfig {
  private final Environment env;

  public MailConfig(Environment env) {
    this.env = env;
  }

  @Bean
  public JavaMailSender javaMailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost(env.getProperty("smtp.host"));
    mailSender.setPort(587);
    mailSender.setUsername(env.getProperty("smtp.email"));
    mailSender.setPassword(env.getProperty("smtp.password"));

    mailSender.setProtocol("smtp");

    return mailSender;
  }
}
