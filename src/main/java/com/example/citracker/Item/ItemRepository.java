package com.example.citracker.Item;

import com.example.citracker.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Repository
public class ItemRepository {
  @Autowired
  private JdbcTemplate jdbc;
  @Autowired
  private UserRepository userRepo;

  private Item extractItemFromRowSet(SqlRowSet rs){
    Item item = new Item();
    item.setId(rs.getInt("id"));
    item.setName(rs.getString("name"));
    Date registrationDate = rs.getDate("registration_date");
    item.setRegistrationDate(registrationDate == null ? null : registrationDate.toLocalDate());
    Date calDate = rs.getDate("calibration_date");
    item.setCalibrationDate(calDate == null ? null : calDate.toLocalDate());
    item.setCalibrationInterval(rs.getInt("calibration_interval"));
    Date nextDate = rs.getDate("next_calibration_date");
    item.setNextCalibrationDate(nextDate == null ? null : nextDate.toLocalDate());
    item.setPicture(rs.getString("picture"));
    item.setDescription(rs.getString("description"));
    item.setNotes(rs.getString("notes"));
    item.setOwner(rs.getString("owner"));
    item.setUser(userRepo.findUserById(rs.getInt("item_creator_id")));

    return item;
  }

  public Item findItemById(int id){
    //TODO difference? with items items = new items();
    Item item = null;
    SqlRowSet rs = jdbc.queryForRowSet("SELECT * FROM items WHERE id = ?", id);

    if (rs.first()){
      item = extractItemFromRowSet(rs);
    }
    return item;
  }

  public List<Item> findAllItems(){
    List<Item> itemList = new ArrayList<>();
    SqlRowSet rs = jdbc.queryForRowSet("SELECT * FROM items");

    while (rs.next()){
      itemList.add(extractItemFromRowSet(rs));
    }
    return itemList;
  }

  public void insert(Item item){
    jdbc.update("INSERT INTO items" +
        "(id, name, registration_date, calibration_date, calibration_interval, " +
        "next_calibration_date, picture, description, notes, owner, item_creator_id) " +
        "VALUES(?,?,?,?,?,?,?,?,?,?,?)",
      item.getId(),item.getName(),item.getRegistrationDate(),item.getCalibrationDate(),
        item.getCalibrationInterval(), item.getNextCalibrationDate(), item.getPicture(),
        item.getDescription(), item.getNotes(),item.getOwner(), item.getUser().getId());
  }

  public void update (Item item){
    jdbc.update("UPDATE items SET id = ?, name = ?, registration_date =?, calibration_date = ?, " +
            "calibration_interval = ?, next_calibration_date = ?, picture = ?, description = ?, " +
            "notes = ?, owner = ?, item_creator_id = ? WHERE id=?;",
        item.getId(),item.getName(), item.getRegistrationDate(), item.getCalibrationDate(),
        item.getCalibrationInterval(), item.getNextCalibrationDate(), item.getPicture(),
        item.getDescription(), item.getNotes(),item.getOwner(), item.getUser().getId(), item.getId());
  }
  public void delete(int id){
    jdbc.update("DELETE FROM items WHERE id = ?", id);
  }

  public Item convertFormToItem(ItemForm itemData) {
    Item item = new Item();

    item.setId(itemData.getId());
    item.setName(itemData.getName());
     LocalDate registrationDate;
     registrationDate = LocalDate.parse(itemData.getRegistrationDate());
    item.setRegistrationDate(registrationDate);
    if(itemData.getCalibrationDate()== ("")){
      item.setCalibrationDate(null);
    }
    else{
      LocalDate calibrationDate;
      calibrationDate = LocalDate.parse(itemData.getCalibrationDate());
      item.setCalibrationDate(calibrationDate);
    }
    item.setCalibrationInterval(item.getCalibrationInterval());
    if(itemData.getNextCalibrationDate()== ("")){
      item.setNextCalibrationDate(null);
    }
    else{
      LocalDate nextCalibrationDate;
      nextCalibrationDate = LocalDate.parse(itemData.getNextCalibrationDate());
      item.setNextCalibrationDate(nextCalibrationDate);
    }
    item.setPicture(itemData.getPicture());
    item.setDescription(itemData.getDescription());
    item.setNotes(itemData.getNotes());
    item.setOwner(itemData.getOwner());
    item.setUser(userRepo.findUserById(itemData.getUserId()));

    return item;
  }

  public List<Item> searchText(String searchWord){
    List<Item> searchedList = new ArrayList<>();
    SqlRowSet rs = jdbc.queryForRowSet("SELECT * FROM items WHERE MATCH(id, name, " +
        "registration_date, calibration_date," +
        "item_creator_id) AGAINST('searchWord' IN BOOLEAN MODE)",searchWord);

    while (rs.next()){
      searchedList.add(extractItemFromRowSet(rs));
    }
    return searchedList;
  }


//  public List<Item> searchForItem(String searchWord){
////convert Item to FormItem
//    Collection<String> list = new LinkedList<String>();
//    List<Item> itemList = findAllItems();
//    for (Item item : itemList){
//      list.add(item.getId()+"");
//      list.add(item.getName());
//      list.add(item.getRegistrationDate().toString());
//      list.add(item.getCalibrationDate().toString());
//      list.add(item.getCalibrationInterval()+"");
//      list.add(item.getNextCalibrationDate().toString());
//      list.add(item.getPicture());
//      list.add(item.getDescription());
//      list.add(item.getNotes());
//      list.add(item.getOwner());
//      list.add(item.getUser().getFullName());
//      list.add(item.getUser().getUsername());
//    }
//    boolean result = list.contains(searchWord);
//    if(result == true){
//      return itemList;
//    }
//    else{
//      return null;
//    }
//  }

}



