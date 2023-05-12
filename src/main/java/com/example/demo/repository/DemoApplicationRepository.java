package com.example.demo.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class DemoApplicationRepository {
  @Autowired
  private JdbcTemplate jdbc;

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @PersistenceContext
  private EntityManager entityManager;

  public void get() {
    // List<Map<String, Object>> list = jdbc.queryForList("SHOW TABLES FROM serio");
    // List<UserEntity> list = jdbcTemplate.query("SELECT * FROM user", new BeanPropertyRowMapper<UserEntity>(UserEntity.class));
    List<UserEntity> list = jdbc.query("SELECT * FROM user WHERE id = ?", new BeanPropertyRowMapper<UserEntity>(UserEntity.class), 3);
    // List<UserEntity> list = entityManager.createQuery("SELECT u FROM UserEntity u", UserEntity.class).getResultList();
    list.forEach(System.out::println);
  }

  public void insert(int id, String name) {
    int num = jdbc.update("INSERT INTO user VALUES (?, ?)", id, name);
    System.out.println(("db updated" + num));
  }
}
