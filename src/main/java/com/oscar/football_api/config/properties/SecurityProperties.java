package com.oscar.football_api.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "security-properties")
public class SecurityProperties {

  private Admin admin = new Admin();

  @Data
  public static class Admin {
    private String username;
    private String password;
  }
}
