package com.example.citracker.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ItemController {

  @Autowired
  private ItemRepository itemRepo;

  @GetMapping("manage/items/add")
  public String create(Model m) {
    m.addAttribute("itemform", new Item());

    return "items/add-item";
  }

  @PostMapping("/manage/items/add")
  public String saveItem(@ModelAttribute Item i) {
    i.setRegistrationDate(LocalDate.now());
    itemRepo.insert(i);

    return "redirect:/";
  }

  @GetMapping("/manage/items/edit/{id}")
  public String update(@PathVariable(name = "id") int id, Model m){
    m.addAttribute("itemform", itemRepo.findItemById(id));

    return "items/edit-item";
  }

  @PostMapping("/manage/items/edit")
  public String update(@ModelAttribute Item i){
    itemRepo.update(i);

    return "redirect:/";

  }

}
