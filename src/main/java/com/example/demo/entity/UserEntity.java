package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
// @Entity
public class UserEntity {
  // @Id
  private Integer id;
  private String name;
}
