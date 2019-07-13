package com.example.citracker.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
  @Autowired JdbcTemplate jdbc;

  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  private User extractUserFromRowSet(SqlRowSet rs){
    User user = new User();
    user.setId(rs.getInt("id"));
    user.setUsername(rs.getString("username"));
    user.setPassword(rs.getString("password"));
    user.setEmail(rs.getString("email"));
    user.setPhoneNo(rs.getInt("phone_no"));
    user.setFullName(rs.getString("full_name"));

    return user;
  }

  public User findUserById(int id){
    User user = null;
    SqlRowSet rs = jdbc.queryForRowSet("SELECT * FROM users WHERE id = ?", id);

    if (rs.first()){
      user = extractUserFromRowSet(rs);
    }
    return user;
  }

  public User findByUsername(String username) {
    User result = null;
    String query = "SELECT * FROM users WHERE username = ?";
    SqlRowSet rs = jdbc.queryForRowSet(query, username);

    if (rs.first()) {
      result = extractUserFromRowSet(rs);
    }
    return result;
  }

  public List<User> findAllUsers(){
    List<User> userList= new ArrayList<>();
    SqlRowSet rs = jdbc.queryForRowSet("SELECT * FROM users");

    while(rs.next()){
      userList.add(extractUserFromRowSet(rs));
    }
    return userList;
  }

  public void insert(User user){
    jdbc.update("INSERT INTO users(id, username, password, email, phone_no, full_name) " +
        "VALUES(?,?,?,?,?,?)",
        user.getId(), user.getUsername(), passwordEncoder.encode(user.getPassword()),
        user.getEmail(), user.getPhoneNo(), user.getFullName());
  }

  public void update(User user){
    jdbc.update("UPDATE users SET username = ?, password = ?, email = ?, phone_no = ?, " +
            "full_name = ? WHERE id=?",
        //TODO passwordEncoder.encodeuser.(user.getPassword())
        user.getUsername(), passwordEncoder.encode(user.getPassword()), user.getEmail(),
        user.getPhoneNo(), user.getFullName(), user.getId());
  }

  public void delete(int id){
    jdbc.update("DELETE FROM users WHERE id =?", id);
  }

}
