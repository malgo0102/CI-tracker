package com.example.citracker.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

  @GetMapping("manage/users/add")
  public String create(Model m) {
    m.addAttribute("userform", new User());

    return "users/add-user";
  }

  @PostMapping("/manage/users/add")
  public String save(@ModelAttribute User u) {
    userRepo.insert(u);

    return "redirect:/manage/users";
  }




}
