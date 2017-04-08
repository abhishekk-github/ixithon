package com.example.ixithon.model;

/**
 * Created by abhishek on 8/4/17.
 */

import org.json.JSONException;
import org.json.JSONObject;

public class CityDescription {
  private String id;
  private String Name;
  private String countryName;
  private String description;
  private int xid;
  private String keyImageUrl;
  private String stateName;
  private String whyToVisit;
  private Double lat;
  private Double lng;

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCountryName() {
    return countryName;
  }

  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getXid() {
    return xid;
  }

  public void setXid(int xid) {
    this.xid = xid;
  }

  public String getKeyImageUrl() {
    return keyImageUrl;
  }

  public void setKeyImageUrl(String keyImageUrl) {
    this.keyImageUrl = keyImageUrl;
  }

  public String getStateName() {
    return stateName;
  }

  public void setStateName(String stateName) {
    this.stateName = stateName;
  }

  public String getWhyToVisit() {
    return whyToVisit;
  }

  public void setWhyToVisit(String whyToVisit) {
    this.whyToVisit = whyToVisit;
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

  public static CityDescription getCityDescriptionFromServer(String JSON){
    CityDescription cityDescription = new CityDescription();

    try {
      JSONObject object =  new JSONObject(JSON);
      JSONObject elementJson =  object.getJSONObject("data");
      cityDescription.setCountryName(elementJson.getString("countryName"));
      cityDescription.setDescription(elementJson.getString("description"));
      cityDescription.setXid(elementJson.getInt("xid"));
      cityDescription.setKeyImageUrl(elementJson.getString("keyImageUrl"));
      cityDescription.setId(elementJson.getString("id"));
      cityDescription.setStateName(elementJson.getString("stateName"));
      cityDescription.setName(elementJson.getString("name"));
      cityDescription.setLat(elementJson.getDouble("latitude"));
      cityDescription.setLat(elementJson.getDouble("longitude"));
      cityDescription.setWhyToVisit(elementJson.getString("whyToVisit"));

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return cityDescription;
  }
}
