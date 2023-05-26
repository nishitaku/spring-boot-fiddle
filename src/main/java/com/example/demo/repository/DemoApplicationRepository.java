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

  public UserEntity get(int id) {
    // List<Map<String, Object>> list = jdbc.queryForList("SHOW TABLES FROM serio");
    // List<UserEntity> list = jdbcTemplate.query("SELECT * FROM user", new BeanPropertyRowMapper<UserEntity>(UserEntity.class));
    List<UserEntity> list = jdbc.query("SELECT * FROM demo_user WHERE id = ?", new BeanPropertyRowMapper<UserEntity>(UserEntity.class), id);
    // List<UserEntity> list = entityManager.createQuery("SELECT u FROM UserEntity u", UserEntity.class).getResultList();
    list.forEach(System.out::println);
    return list.get(0);
  }

  public void insert(int id, String name) {
    int num = jdbc.update("INSERT INTO demo_user VALUES (?, ?)", id, name);
    System.out.println(("db inserted" + num));
  }

  public void delete(int id) {
    int num = jdbc.update("DELETE FROM demo_user WHERE id = ?", id);
    System.out.println(("db deleted" + num));
  }
}
