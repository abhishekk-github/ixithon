package com.example.ixithon.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.ixithon.util.AppUtil;

import java.util.ArrayList;

public class Plan implements Parcelable {
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

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeLong(this.planID);
    dest.writeString(this.startPoint);
    dest.writeString(this.destinationPoint);
    dest.writeList(this.travellerInvites);
    dest.writeList(this.userDecision);
    dest.writeString(this.userID);
  }

  protected Plan(Parcel in) {
    this.planID = in.readLong();
    this.startPoint = in.readString();
    this.destinationPoint = in.readString();
    this.travellerInvites = new ArrayList<TravellerInvite>();
    in.readList(this.travellerInvites, TravellerInvite.class.getClassLoader());
    this.userDecision = new ArrayList<UserDecision>();
    in.readList(this.userDecision, UserDecision.class.getClassLoader());
    this.userID = in.readString();
  }

  public static final Parcelable.Creator<Plan> CREATOR = new Parcelable.Creator<Plan>() {
    @Override
    public Plan createFromParcel(Parcel source) {
      return new Plan(source);
    }

    @Override
    public Plan[] newArray(int size) {
      return new Plan[size];
    }
  };
}
