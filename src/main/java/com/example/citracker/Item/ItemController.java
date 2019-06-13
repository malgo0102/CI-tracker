package com.example.citracker.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ItemController {

  @Autowired
  private ItemRepository itemRepo;

  //item detailed view
  @GetMapping("/item/{id}")
  public String findItem(@PathVariable(name = "id") int id, Model model) {
    model.addAttribute("item", itemRepo.findItemById(id));

    return "items/view-item";
  }

  @GetMapping("manage/items/add")
  public String create(Model m) {
    m.addAttribute("itemform", new Item());

    return "items/add-item";
  }

  @PostMapping("/manage/items/add")
  public String save(@ModelAttribute Item i) {
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

  @GetMapping ("/manage/items/delete/{id}")
  public String delete(@PathVariable (name = "id") int id, Model m){
    m.addAttribute("item", itemRepo.findItemById(id));

    return "items/delete-item";
  }

  @PostMapping ("/manage/items/delete")
  public String delete(@RequestParam (name = "id") int id){
    itemRepo.delete(id);

    return "redirect:/";
  }

}
