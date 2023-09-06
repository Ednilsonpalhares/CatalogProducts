package com.catalog.products.config;

import lombok.Setter;
import org.javers.spring.auditable.AuthorProvider;

@Setter
public class Author implements AuthorProvider {

  private static Author instance;
  private String identifier;

  private Author() {
    this.identifier = "unknown";
  }

  private static class SingletonHelper {
    private static final Author INSTANCE = new Author();
  }
  public static Author getInstance() {
    return SingletonHelper.INSTANCE;
  }

  @Override
  public String provide() {
    return identifier;
  }
}
