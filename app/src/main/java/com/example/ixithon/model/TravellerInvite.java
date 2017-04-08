package com.example.ixithon.model;

public class TravellerInvite {

  public static class TravelStatus {
    public long ACCEPTED = 0;
    public long PENDING = 1;
    public long DENIED = 2;
    private long value;

    public TravelStatus(long value) {
      setValue(value);
    }

    public void setValue(long value) {
      this.value = value;
    }

    public long getValue() {
      return value;
    }
  }

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
}
