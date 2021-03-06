package com.example.citracker.Item;

import com.example.citracker.User.User;

import java.sql.Time;
import java.time.LocalDate;

public class Item {
  private int id;
  private String ciId;
  private String name;
  private LocalDate registrationDate;
  private LocalDate calibrationDate;
  //TODO LocalDate plusMonths()
  private int calibrationInterval;
  private LocalDate nextCalibrationDate;
  private String picture;
  private String description;
  private String notes;
  //TODO owner as user?
  private String owner;
  private User user;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCiId() { return ciId; }

  public void setCiId(String ciId) { this.ciId = ciId; }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(LocalDate registrationDate) {
    this.registrationDate = registrationDate;
  }

  public LocalDate getCalibrationDate() {
    return calibrationDate;
  }

  public void setCalibrationDate(LocalDate calibrationDate) {
    this.calibrationDate = calibrationDate;
  }

  public int getCalibrationInterval() {
    return calibrationInterval;
  }

  public void setCalibrationInterval(int calibrationInterval) {
    this.calibrationInterval = calibrationInterval;
  }

  public LocalDate getNextCalibrationDate() {
    return nextCalibrationDate;
  }

  public void setNextCalibrationDate(LocalDate nextCalibrationDate) {
    this.nextCalibrationDate = nextCalibrationDate;
  }

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }



}
