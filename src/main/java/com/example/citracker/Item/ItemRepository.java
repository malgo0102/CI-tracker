package com.example.citracker.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemRepository {
  @Autowired
  private JdbcTemplate jdbc;
  //@Autowired
  //private UserRepository userRepo;

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
    item.setItemCreator(rs.getString("item_creator_id"));

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
        item.getDescription(), item.getNotes(),item.getOwner(), item.getItemCreator());
  }

  public void update (Item item){
    jdbc.update("UPDATE items SET registration_date = ?, calibration_date = ?, " +
            "calibration_interval = ?, next_calibration_date = ?, picture = ?, description = ?, " +
            "notes = ?, owner = ?, item_creator_id = ? WHERE id=?,",
        item.getId(),item.getName(),item.getRegistrationDate(),item.getCalibrationDate(),
        item.getCalibrationInterval(), item.getNextCalibrationDate(), item.getPicture(),
        item.getDescription(), item.getNotes(),item.getOwner(), item.getItemCreator());
  }
  public void delete(int id){
    jdbc.update("DELETE FROM items WHERE id = ?", id);
  }
}
