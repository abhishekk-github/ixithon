package com.example.ixithon.model;

public class TravellerInvite {
  private long planID;
  private String travellerName;
  private String travellerID;
  private TravelStatus status;

  public long getPlanID() {
    return planID;
  }

  public void setPlanID(long planID) {
    this.planID = planID;
  }

  public String getTravellerName() {
    return travellerName;
  }

  public void setTravellerName(String travellerName) {
    this.travellerName = travellerName;
  }

  public String getTravellerID() {
    return travellerID;
  }

  public void setTravellerID(String travellerID) {
    this.travellerID = travellerID;
  }

  public TravelStatus getStatus() {
    return status;
  }

  public void setStatus(TravelStatus status) {
    this.status = status;
  }

  enum TravelStatus{
    ACCEPTED,
    PENDING,
    DENIED;
  }
}
