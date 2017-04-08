package com.example.ixithon.model;

public final class TravelModeModel {
  private String code;
  private long time;
  private String carrierName;
  private String depTime;
  private String arrTime;
  private String destinationCode;
  private String originCode;
  private String daysOfOperation;

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

  public String getDepTime() {
    return depTime;
  }

  public void setDepTime(String depTime) {
    this.depTime = depTime;
  }

  public String getArrTime() {
    return arrTime;
  }

  public void setArrTime(String arrTime) {
    this.arrTime = arrTime;
  }

  public String getDestinationCode() {
    return destinationCode;
  }

  public void setDestinationCode(String destinationCode) {
    this.destinationCode = destinationCode;
  }

  public String getOriginCode() {
    return originCode;
  }

  public void setOriginCode(String originCode) {
    this.originCode = originCode;
  }

  public String getDaysOfOperation() {
    return daysOfOperation;
  }

  public void setDaysOfOperation(String daysOfOperation) {
    this.daysOfOperation = daysOfOperation;
  }

  /*public static List<TravelModeModel> getListOfModeFromServer(String JSON){


    List<TravelModeModel> mModeList = new ArrayList<>();

    try {
      JSONObject object = new JSONObject(JSON);
      JSONObject date = object.getJSONObject("data");
      JSONArray array =  date.getJSONArray("flight");
      for(int i =0 ; i< array.length() ; i++) {
        TravelModeModel mode = new TravelModeModel();
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
  }*/
}