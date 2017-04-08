package com.example.ixithon.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 8/4/17.
 */

public class InspiredCity {

  private String imageUrl;
  private String name;
  private String cityName;
  private String countryName;
  private String url;
  private String stateName;
  private String currency;
  private int price;
  private String cityId;

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public String getCountryName() {
    return countryName;
  }

  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getStateName() {
    return stateName;
  }

  public void setStateName(String stateName) {
    this.stateName = stateName;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getCityId() {
    return cityId;
  }

  public void setCityId(String cityId) {
    this.cityId = cityId;
  }

  public static List<InspiredCity> getInspiredCityListFromServer(String JSON){


    List<InspiredCity> mCityList = new ArrayList<>();

    try {
      JSONObject object = new JSONObject(JSON);
      JSONObject date = object.getJSONObject("data");
      JSONArray array =  date.getJSONArray("flight");
      for(int i =0 ; i< array.length() ; i++) {
        InspiredCity city = new InspiredCity();
        JSONObject elementJson = array.getJSONObject(i);
        city.setCityName(elementJson.getString("cityName"));
        city.setUrl(elementJson.getString("url"));
        city.setImageUrl(elementJson.getString("image"));
        city.setPrice(elementJson.getInt("price"));
        city.setCityId(elementJson.getString("cityId"));
        city.setCountryName(elementJson.getString("countryName"));
        city.setCurrency(elementJson.getString("currency"));
        city.setStateName(elementJson.getString("stateName"));
        city.setName(elementJson.getString("name"));
        mCityList.add(city);
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return mCityList;
  }
}
