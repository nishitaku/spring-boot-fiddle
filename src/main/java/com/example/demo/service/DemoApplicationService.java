package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.config.DbConfig;
import com.example.demo.config.DemoApplicationConfig;
import com.example.demo.repository.DemoApplicationRepository;

@Service
public class DemoApplicationService {

  String env;

  @Autowired
  DbConfig dbConfig;

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

    return "dog";
  }

  public void getUser(int id) {
    demoApplicationRepository.get(id);
  }

  public void addUser(int id, String name) {
    demoApplicationRepository.insert(id, name);
  }

  public void deleteUser(int id) {
    demoApplicationRepository.delete(id);
  }
}
