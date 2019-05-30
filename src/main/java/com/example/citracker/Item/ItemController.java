package com.example.citracker.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ItemController {

  @Autowired
  private ItemRepository itemRepo;

  @GetMapping("manage/items")
  //TODO why is it string
  public String itemList(Model m){
    List<Item> itemList = itemRepo.findAllItems();
    m.addAttribute("itemlist",itemList);

    return "templates/index";
  }


}
