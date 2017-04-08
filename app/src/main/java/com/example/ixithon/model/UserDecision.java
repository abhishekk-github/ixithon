package com.example.ixithon.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class UserDecision implements Parcelable {
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

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeLong(this.planID);
    dest.writeString(this.userID);
    dest.writeString(this.travelMode);
    dest.writeString(this.hotel);
    dest.writeList(this.visitLocations);
  }

  public UserDecision() {
  }

  protected UserDecision(Parcel in) {
    this.planID = in.readLong();
    this.userID = in.readString();
    this.travelMode = in.readString();
    this.hotel = in.readString();
    this.visitLocations = new ArrayList<VisitLocation>();
    in.readList(this.visitLocations, VisitLocation.class.getClassLoader());
  }

  public static final Parcelable.Creator<UserDecision> CREATOR = new Parcelable.Creator<UserDecision>() {
    @Override
    public UserDecision createFromParcel(Parcel source) {
      return new UserDecision(source);
    }

    @Override
    public UserDecision[] newArray(int size) {
      return new UserDecision[size];
    }
  };
}
