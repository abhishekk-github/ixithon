package com.example.ixithon.db;

import android.provider.BaseColumns;

public final class PlanListContract {

  private PlanListContract() {
  }

  public static class PlanListEntry implements BaseColumns {

    public static final String TABLE_NAME = "PLAN_LIST";

    public static final String COLUMN_START_POINT = "START_POINT";
    public static final String COLUMN_DESTINATION_POINT = "DESTINATION_POINT";
    public static final String COLUMN_USER_ID = "USER_ID";

    public static final String CREATE_TABLE =
        "CREATE TABLE " + TABLE_NAME + " (" +
            _ID + " INTEGER PRIMARY KEY," +
            COLUMN_START_POINT + " TEXT," +
            COLUMN_DESTINATION_POINT + " TEXT," +
            COLUMN_USER_ID + " TEXT)";

    public static String INSERT_DATA = "INSERT INTO " + TABLE_NAME + "("
        +  _ID + ", "
        + COLUMN_START_POINT + ", "
        + COLUMN_DESTINATION_POINT + ", "
        + COLUMN_USER_ID + ") VALUES(?1, ?2, ?3, ?4);";

    public static String SELECT_QUERY = "SELECT * FROM " + TABLE_NAME + ";";
  }
}