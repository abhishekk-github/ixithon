package com.example.ixithon.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public final class TravelModeModel {
  private String code;
  private double price;
  private long time;
  private String carrierName;
  private String duration;

  public String getDuration() {
    return duration;
  }

  public void setDuration(String duration) {
    this.duration = duration;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }

  public String getCarrierName() {
    return carrierName;
  }

  public void setCarrierName(String carrierName) {
    this.carrierName = carrierName;
  }

  public static List<TravelModeModel> getListOfModeFromServer(String JSON, String type) {

    List<TravelModeModel> mModeList = new ArrayList<>();

    try {
      JSONObject object = new JSONObject(JSON);
      JSONObject date = object.getJSONObject("data");
      JSONArray array = date.getJSONArray("routes");
      for (int i = 0; i < array.length(); i++) {
        TravelModeModel mode = new TravelModeModel();
        JSONObject elementJson = array.getJSONObject(i);
        if (elementJson.getString("firstModeTypesCss").trim().equals(type.trim())) {
          JSONObject firstStepsJson = elementJson.getJSONObject("firstStep");
          JSONArray carriersArray = firstStepsJson.getJSONArray("carriers");
          for (int j =0 ; j< carriersArray.length(); j++) {
            JSONObject object1 = carriersArray.getJSONObject(i);
            mode.setPrice(object1.getDouble("price"));
            mode.setTime(object1.getLong("time"));
            mode.setCarrierName(object1.getString("carrierName"));
            mode.setDuration(object1.getString("code"));
            mModeList.add(mode);
          }
        }
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return mModeList;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }
}