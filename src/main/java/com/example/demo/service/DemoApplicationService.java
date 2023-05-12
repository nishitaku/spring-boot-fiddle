package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.config.DbConfig;
import com.example.demo.config.DemoApplicationConfig;
import com.example.demo.repository.DemoApplicationRepository;

import lombok.Getter;
import lombok.Setter;

@Service
public class DemoApplicationService {

  // @Autowired
  // DemoApplicationConfig demoApplicationConfig;

  String env;

  @Autowired
  DbConfig dbConfig;

  // @Value("${db.env}")
  // String dbEnv;

  // @Value("${myconfig.fuga.foo}")
  // String foo;

  // @Value("${myconfig.hoge-list}")
  // List<String> hogeList;

  // @Value("${myconfig.lang-to-country}")
  // @Getter
  // @Setter
  // private Map<String, String> langToCountry;
  

  // @Value("${myconfig.age}")
  // Integer age;
  
  // default constructor
  @Autowired
  DemoApplicationService(DemoApplicationConfig demoApplicationConfig) {
    System.out.println("DemoApplicationService: constructor");
    this.env = demoApplicationConfig.getEnv();
  }

  @Autowired
  DemoApplicationRepository demoApplicationRepository;

  public String getAnimal() {
    
    // String env = demoApplicationConfig.getEnv();
    System.out.println("config: env=" + env);

    Integer dbPort = dbConfig.getPort();
    System.out.println("db config: port=" + dbPort);

    // System.out.println("db config: env=" + dbEnv);

    // System.out.println("config: age=" + age);

    // System.out.println("config: foo=" + foo);

    // Map langToCountry = demoApplicationConfig.getLangToCountry();
    // System.out.println("config: langToCountry=" + langToCountry);

    // System.out.println("config: langToCountry=" + langToCountry);

    return "dog";
  }

  public void getDb() {
    demoApplicationRepository.get();
    demoApplicationRepository.insert(3, "fugafuga");
  }
}
