package com.example.citracker.Item;

import com.example.citracker.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.List;

@Controller
public class ItemController {

  @Autowired
  private ItemRepository itemRepo;
  @Autowired
  private UserRepository userRepo;

  //item detailed view
  @GetMapping("/item/{id}")
  public String findItem(@PathVariable(name = "id") int id, Model m) {
    m.addAttribute("item", itemRepo.findItemById(id));

    return "items/view-item";
  }

  //add item
  @GetMapping("manage/items/add")
  public String create(Model m) {
    m.addAttribute("userlist", userRepo.findAllUsers());
    m.addAttribute("itemform", new ItemForm());

    return "items/add-item";
  }

  @PostMapping("/manage/items/add")
  public String save(@ModelAttribute ItemForm itemForm) {
    Item item = new Item();
    item.setRegistrationDate(LocalDate.now());
    item = itemRepo.convertFormToItem(itemForm);
    itemRepo.insert(item);

    return "redirect:/";
  }

  @GetMapping("/manage/items/edit/{id}")
  public String update(@PathVariable(name = "id") int id, Model m){
    Item item = itemRepo.findItemById(id);
//    ItemForm itemForm = itemRepo.convertItemToForm(item);
    ItemForm itemForm = new ItemForm();

    itemForm.setId(id);
    itemForm.setName(item.getName());
    itemForm.setRegistrationDate(item.getRegistrationDate().toString());
    if(item.getCalibrationDate()== null){
      itemForm.setCalibrationDate("");
    }
    else{
      itemForm.setCalibrationDate(item.getCalibrationDate().toString());
    }
    itemForm.setCalibrationInterval(item.getCalibrationInterval());
    if(item.getNextCalibrationDate()== null){
      itemForm.setNextCalibrationDate("");
    }
    else{
      itemForm.setNextCalibrationDate(item.getNextCalibrationDate().toString());
    }
    itemForm.setPicture(item.getPicture());
    itemForm.setDescription(item.getDescription());
    itemForm.setNotes(item.getNotes());
    itemForm.setOwner(item.getOwner());
    itemForm.setUserId(item.getUser().getId());

    m.addAttribute("itemform",itemForm);
    m.addAttribute("userlist", userRepo.findAllUsers());

    return "items/edit-item";
  }

  @PostMapping("/manage/items/edit")
  public String update(@ModelAttribute ItemForm itemForm){
    Item item = itemRepo.convertFormToItem(itemForm);
    itemRepo.update(item);

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

  @GetMapping("/search")
  public String searchResult(@RequestParam (name="searchWord") String searchWord, Model m){
    List<Item> resultList = itemRepo.searchText(searchWord);
    m.addAttribute("resultlist",resultList);

    return "items/searched-item";
  }
}
