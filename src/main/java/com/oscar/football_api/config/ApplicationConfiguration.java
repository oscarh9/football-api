package com.oscar.football_api.config;

import com.oscar.football_api.config.properties.OpenApiProperties;
import com.oscar.football_api.config.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({SecurityProperties.class, OpenApiProperties.class})
public class ApplicationConfiguration {}
