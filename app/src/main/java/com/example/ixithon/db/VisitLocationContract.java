package com.example.ixithon.db;

import android.provider.BaseColumns;

public final class VisitLocationContract {

  private VisitLocationContract() {}

  public static class VisitLocationEntry implements BaseColumns {

    public static final String TABLE_NAME = "VISIT_LOCATIONS";

    public static final String COLUMN_USER_ID = "USER_ID";
    public static final String COLUMN_LOCATION = "PLAN_ID";
    public static final String COLUMN_LOCATION_ID = "LOCATION_ID";

    public static final String CREATE_TABLE =
        "CREATE TABLE " + TABLE_NAME + " (" +
            _ID + " INTEGER PRIMARY KEY," +
            COLUMN_LOCATION + " TEXT," +
            COLUMN_LOCATION_ID + " TEXT," +
            COLUMN_USER_ID + " TEXT," +
            " FOREIGN KEY (" + COLUMN_USER_ID + ") REFERENCES " +
            UserDecisionContract.UserDecisionEntry.TABLE_NAME + "(" + UserDecisionContract.UserDecisionEntry.COLUMN_USER_ID + "))";

    public static String INSERT_DATA = "INSERT INTO " + TABLE_NAME + "("
        + COLUMN_USER_ID + ", "
        + COLUMN_LOCATION + ", "
        + COLUMN_LOCATION_ID + ") VALUES(?1, ?2, ?3);";

    public static String SELECT_QUERY = "SELECT * FROM " + TABLE_NAME + "where" + COLUMN_USER_ID + "=?;";
  }
}