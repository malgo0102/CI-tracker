package com.example.citracker.Item;

public class ItemForm {
  private int id;
  private String name;
  private String registrationDate;
  private String calibrationDate;
  private int calibrationInterval;
  private String nextCalibrationDate;
  private String picture;
  private String description;
  private String notes;
  private String owner;
  private int userId;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(String registrationDate) {
    this.registrationDate = registrationDate;
  }

  public String getCalibrationDate() {
    return calibrationDate;
  }

  public void setCalibrationDate(String calibrationDate) {
    this.calibrationDate = calibrationDate;
  }

  public int getCalibrationInterval() {
    return calibrationInterval;
  }

  public void setCalibrationInterval(int calibrationInterval) {
    this.calibrationInterval = calibrationInterval;
  }

  public String getNextCalibrationDate() {
    return nextCalibrationDate;
  }

  public void setNextCalibrationDate(String nextCalibrationDate) {
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

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }
}
