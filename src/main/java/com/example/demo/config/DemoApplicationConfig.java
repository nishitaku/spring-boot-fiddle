package com.example.demo.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "myconfig")
public class DemoApplicationConfig {

  private String env;

  private String name;

  private Integer age;

  private Map<String, String> langToCountry;
}
