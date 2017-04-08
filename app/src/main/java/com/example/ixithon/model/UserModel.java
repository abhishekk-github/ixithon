package com.example.ixithon.model;

/**
 * Created by abhishek on 8/4/17.
 */

public class UserModel {
  private String userID;
  private String password;

  public boolean isSelected() {
    return selected;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
  }

  boolean selected = false;

  public String getUserID() {
    return userID;
  }

  public void setUserID(String userID) {
    this.userID = userID;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
