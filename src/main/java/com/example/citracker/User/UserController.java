package com.example.citracker.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

  @Autowired UserRepository userRepo;

  @GetMapping("/manage/users")
  public String listUsers(Model m){
    List<User> userList = userRepo.findAllUsers();
    m.addAttribute("userlist",userList);

    return "users/view-users";
  }




}
