package com.example.citracker.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

  @GetMapping ("manage/users/edit/{id}")
  public String update(@PathVariable(name = "id") int id,  Model m){
    m.addAttribute("user", userRepo.findUserById(id));

    return "users/edit-user";
  }

  @PostMapping("manage/users/edit")
  public String update (@ModelAttribute User user){
    userRepo.update(user);

    return "redirect:/manage/users";
  }

  @GetMapping ("/manage/users/delete/{id}")
  public String delete(@PathVariable (name = "id") int id, Model m){
    m.addAttribute("user", userRepo.findUserById(id));

    return "users/delete-user";
  }

  @PostMapping ("/manage/users/delete")
  public String delete(@RequestParam(name = "id") int id){
    userRepo.delete(id);

    return "redirect:/manage/users";
  }




}
