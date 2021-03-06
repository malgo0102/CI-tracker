package com.example.citracker;

import com.example.citracker.Item.Item;
import com.example.citracker.Item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

  @Autowired
  private ItemRepository itemRepo;

  @GetMapping("/")
  public String listItems(Model m){
    List<Item> itemList = itemRepo.findAllItems();
    m.addAttribute("itemlist",itemList);

    return "index";
  }

}
