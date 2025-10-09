package com.oscar.football_api.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "open-api-properties")
public class OpenApiProperties {
  private Info info = new Info();

  @Data
  public static class Info {
    private String title;
    private String description;
    private String version;
  }
}
