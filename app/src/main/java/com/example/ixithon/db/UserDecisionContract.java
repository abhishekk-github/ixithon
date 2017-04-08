package com.example.ixithon.db;

import android.provider.BaseColumns;

public final class UserDecisionContract {

  private UserDecisionContract() {
  }

  public static class UserDecisionEntry implements BaseColumns {
    public static final String TABLE_NAME = "USER_DECISIONS";

    public static final String COLUMN_PLAN_ID = "PLAN_ID";
    public static final String COLUMN_USER_ID = "USER_ID";
    public static final String COLUMN_TRAVEL_MODE_CHOICE = "TRAVEL_MODE_CHOICE";
    public static final String COLUMN_HOTEL_CHOICE = "HOTEL_CHOICE";
    public static final String COLUMN_VISIT_LOCATIONS = "VISIT_LOCATIONS";

    public static final String CREATE_TABLE =
        "CREATE TABLE " + TABLE_NAME + " (" +
            _ID + " INTEGER PRIMARY KEY AUTO INCREMENT," +
            COLUMN_USER_ID + " TEXT," +
            COLUMN_TRAVEL_MODE_CHOICE + " TEXT," +
            COLUMN_HOTEL_CHOICE + " TEXT," +
            COLUMN_VISIT_LOCATIONS + " TEXT" +
            COLUMN_PLAN_ID + " INTEGER," +
            " FOREIGN KEY (" + COLUMN_PLAN_ID + ") REFERENCES " +
            PlanListContract.PlanListEntry.TABLE_NAME + "(" + PlanListContract.PlanListEntry._ID + "))";

    public static String INSERT_DATA = "INSERT INTO " + TABLE_NAME + "("
        + COLUMN_USER_ID + ", "
        + COLUMN_TRAVEL_MODE_CHOICE + ", "
        + COLUMN_HOTEL_CHOICE + ", "
        + COLUMN_PLAN_ID + ") "
        + "VALUES(?1, ?2, ?3, ?4);";

    public static String SELECT_QUERY = "SELECT * FROM " + TABLE_NAME + " where " + COLUMN_PLAN_ID + "=? ;" ;
  }
}