package com.catalog.products.config;

import org.javers.spring.auditable.AuthorProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JaversConfiguration {

  @Bean
  public AuthorProvider authorProvider() {
    return Author.getInstance();
  }
}
