package com.example.ixithon.model;

import java.util.ArrayList;

public class UserDecision {
  private long planID;
  private String userID;
  private String travelMode;
  private String hotel;
  private ArrayList<VisitLocation> visitLocations;

  public long getPlanID() {
    return planID;
  }

  public void setPlanID(long planID) {
    this.planID = planID;
  }

  public String getTravelMode() {
    return travelMode;
  }

  public void setTravelMode(String travelMode) {
    this.travelMode = travelMode;
  }

  public String getHotel() {
    return hotel;
  }

  public void setHotel(String hotel) {
    this.hotel = hotel;
  }

  public ArrayList<VisitLocation> getVisitLocations() {
    return visitLocations;
  }

  public void setVisitLocations(ArrayList<VisitLocation> visitLocations) {
    this.visitLocations = visitLocations;
  }

  public String getUserID() {
    return userID;
  }

  public void setUserID(String userID) {
    this.userID = userID;
  }
}
