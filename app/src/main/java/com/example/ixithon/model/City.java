package com.example.ixithon.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 8/4/17.
 */

public class City {
  private String cityName;
  private String url;
  private String ct;
  private String address;
  private String ID;
  private String EID;
  private String state;
  private String country;
  private Double lat,lng;
  private Integer xid;

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getCt() {
    return ct;
  }

  public void setCt(String ct) {
    this.ct = ct;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getID() {
    return ID;
  }

  public void setID(String ID) {
    this.ID = ID;
  }

  public String getEID() {
    return EID;
  }

  public void setEID(String EID) {
    this.EID = EID;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Double getLat() {
    return lat;
  }

  public void setLat(Double lat) {
    this.lat = lat;
  }

  public Double getLng() {
    return lng;
  }

  public void setLng(Double lng) {
    this.lng = lng;
  }

  public Integer getXid() {
    return xid;
  }

  public void setXid(Integer xid) {
    this.xid = xid;
  }

   public static List<City> getCityListFromServer(String JSON){
     List<City> mCityList = new ArrayList<>();

     try {
       JSONArray array =  new JSONArray(JSON);
       for(int i =0 ; i< array.length() ; i++) {
         City city = new City();
         JSONObject elementJson = array.getJSONObject(i);
         city.setCityName(elementJson.getString("text"));
         city.setUrl(elementJson.getString("url"));
         city.setCt(elementJson.getString("ct"));
         city.setID(elementJson.getString("_id"));
         city.setState(elementJson.getString("st"));
         city.setCountry(elementJson.getString("co"));
         city.setEID(elementJson.getString("eid"));
         city.setLat(elementJson.getDouble("lat"));
         city.setLat(elementJson.getDouble("lon"));
         city.setXid(elementJson.getInt("xid"));
         mCityList.add(city);
       }
     } catch (JSONException e) {
       e.printStackTrace();
     }
     return mCityList;
   }
}
