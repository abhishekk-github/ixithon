package com.example.ixithon.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TravellerInvite implements Parcelable {

  public static class TravelStatus implements Parcelable {
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

    @Override
    public int describeContents() {
      return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
      dest.writeLong(this.ACCEPTED);
      dest.writeLong(this.PENDING);
      dest.writeLong(this.DENIED);
      dest.writeLong(this.value);
    }

    protected TravelStatus(Parcel in) {
      this.ACCEPTED = in.readLong();
      this.PENDING = in.readLong();
      this.DENIED = in.readLong();
      this.value = in.readLong();
    }

    public static final Creator<TravelStatus> CREATOR = new Creator<TravelStatus>() {
      @Override
      public TravelStatus createFromParcel(Parcel source) {
        return new TravelStatus(source);
      }

      @Override
      public TravelStatus[] newArray(int size) {
        return new TravelStatus[size];
      }
    };
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

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeLong(this.planID);
    dest.writeString(this.travellerName);
    dest.writeString(this.travellerID);
    dest.writeParcelable(this.status, flags);
  }

  public TravellerInvite() {
  }

  protected TravellerInvite(Parcel in) {
    this.planID = in.readLong();
    this.travellerName = in.readString();
    this.travellerID = in.readString();
    this.status = in.readParcelable(TravelStatus.class.getClassLoader());
  }

  public static final Parcelable.Creator<TravellerInvite> CREATOR = new Parcelable.Creator<TravellerInvite>() {
    @Override
    public TravellerInvite createFromParcel(Parcel source) {
      return new TravellerInvite(source);
    }

    @Override
    public TravellerInvite[] newArray(int size) {
      return new TravellerInvite[size];
    }
  };
}
