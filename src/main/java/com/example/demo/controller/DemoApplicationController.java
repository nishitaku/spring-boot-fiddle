package com.example.demo.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.util.StringUtils;

import com.example.demo.entity.UserEntity;
import com.example.demo.model.User;
import com.example.demo.service.DemoApplicationService;

@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class DemoApplicationController {

  private static final Logger logger = LoggerFactory.getLogger(DemoApplicationController.class);

  // default constructor
  DemoApplicationController() {
    System.out.println("DemoApplicationController: constructor");
  }

  // @ModelAttribute
  // User setUpUser() {
  //   return new User();
  // }

  @GetMapping("/")
	public String helloWorld() {
		return "index";
	}

  @GetMapping("/form")
  private String readForm(User user) {
    // System.out.println("GET /form " + setUpUser());
    System.out.println("GET /form " + user);
    // user.setName("hogehoge");
    return "form";
  }

  @Autowired
  private DemoApplicationService demoApplicationService;

  @PostMapping("/form")
  private String confirm(@ModelAttribute User user) {
    // System.out.println("POST /form " + setUpUser());
    user.setAge(100);
    return "confirm";
  }

  @RequestMapping("/api")
  public String api(@Validated User user, Model model, BindingResult result) {
    System.out.println("GET /api " + user);

    if(result.hasErrors()) {
      for (ObjectError error : result.getAllErrors()) {
        System.out.println(error.getDefaultMessage());
    }
      return "error";
    }


    String animal = demoApplicationService. getAnimal();
    System.out.println(animal);

    model.addAttribute("hogehoge", animal);
    return "form";
  }

  String data = "datadata";

  @RequestMapping("xml-test")
  public String xmlTest(ModelMap model) {
    logger.info("xmlTest called");

    // validtionSample();

    model.addAttribute("imgsrc", "http://hogehoge");
    model.addAttribute("fugafuga", "fuga");
    model.addAttribute("list", new ArrayList<String>(){
      {
          add("Apple");
          add("Orange");
          add("Melon");
      }
    });
    model.addAttribute("messageId", "content.message");
    model.addAttribute("arg", "end");
    // model.addAttribute("data", data);

    return "xml-test";
  }

  @GetMapping("user")
  public String getUsers(String userId, ModelMap model) {
    UserEntity user = demoApplicationService.getUser(Integer.parseInt(userId));
    model.addAttribute("name", user.getName());
    return "user";
  }

  @PostMapping("user")
  public String postUser(String userId, String name) {
    demoApplicationService.addUser(Integer.parseInt(userId), name);
    return "ok";
  }

  @DeleteMapping("user")
  public String deleteUser(String userId) {
    demoApplicationService.deleteUser(Integer.parseInt(userId));
    return "ok";
  }

  private void validtionSample() {
    String str = null;
    System.out.println("********** isNotBlank: " + isNotBlank(str));
    System.out.println("********** isNotEmpty: " + isNotEmpty(str));
    System.out.println("********** hasLength: " + StringUtils.hasLength(str));
  }

  private boolean isBlank(final String str) {
    if(str == null || str.length() == 0) {
      return true;
    }
    for(int i = 0; i < str.length(); i++) {
      if(!Character.isWhitespace(str.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  private boolean isNotBlank(final String str) {
    return !isBlank(str);
  }

  private boolean isEmpty(final String str) {
    return str == null || str.length() == 0;
  }

  private boolean isNotEmpty(final String str) {
    return !isEmpty(str);
  }
}
