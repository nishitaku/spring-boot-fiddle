package com.example.demo.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class User {
  @NotNull
  private String name;
  @NotNull
  private String email;
  @NotNull
  private Integer age;

  // public User() {
  //   this.name = "fugafuga";
  // }
}
