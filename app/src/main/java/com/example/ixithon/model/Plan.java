package com.example.ixithon.model;

import com.example.ixithon.util.AppUtil;

import java.util.ArrayList;

public class Plan {
  private long planID;
  private String startPoint;
  private String destinationPoint;
  private ArrayList<TravellerInvite> travellerInvites;
  private ArrayList<UserDecision> userDecision;
  private String userID;

  public Plan() {
    planID = AppUtil.generateUniqueId();
  }

  public long getPlanID() {
    return planID;
  }

  public void setPlanID(long planID) {
    this.planID = planID;
  }

  public String getStartPoint() {
    return startPoint;
  }

  public void setStartPoint(String startPoint) {
    this.startPoint = startPoint;
  }

  public String getDestinationPoint() {
    return destinationPoint;
  }

  public void setDestinationPoint(String destinationPoint) {
    this.destinationPoint = destinationPoint;
  }

  public ArrayList<TravellerInvite> getTravellerInvites() {
    return travellerInvites;
  }

  public void setTravellerInvites(ArrayList<TravellerInvite> travellerInvites) {
    this.travellerInvites = travellerInvites;
  }

  public ArrayList<UserDecision> getUserDecisions() {
    return userDecision;
  }

  public void setUserDecisions(ArrayList<UserDecision> userDecision) {
    this.userDecision = userDecision;
  }

  public String getUserID() {
    return userID;
  }

  public void setUserID(String userID) {
    this.userID = userID;
  }
}
