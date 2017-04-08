package com.example.ixithon.db;

import android.provider.BaseColumns;

public final class TravellerInviteContract {

  private TravellerInviteContract() {
  }

  public static class TravellerInviteEntry implements BaseColumns {

    public static final String TABLE_NAME = "TRAVELLER_INVITES";

    public static final String COLUMN_PLAN_ID = "PLAN_ID";
    public static final String COLUMN_TRAVELLER_ID = "TRAVELLER_ID";
    public static final String COLUMN_TRAVELLER_NAME = "TRAVELLER_NAME";

    public static final String CREATE_TABLE =
        "CREATE TABLE " + TABLE_NAME + " (" +
            _ID + " INTEGER PRIMARY KEY AUTO INCREMENT," +
            COLUMN_TRAVELLER_NAME + " TEXT," +
            COLUMN_TRAVELLER_ID + " TEXT" +
            COLUMN_PLAN_ID + " INTEGER," +
            " FOREIGN KEY (" + COLUMN_PLAN_ID + ") REFERENCES " +
            PlanListContract.PlanListEntry.TABLE_NAME + "(" + PlanListContract.PlanListEntry._ID + "))";

    public static String INSERT_DATA = "INSERT INTO " + TABLE_NAME + "("
        + COLUMN_TRAVELLER_ID + ", "
        + COLUMN_TRAVELLER_NAME + ", "
        + COLUMN_PLAN_ID + ") "
        + "VALUES(?1, ?2, ?3);";

    public static String SELECT_QUERY = "SELECT * FROM " + TABLE_NAME + ";";
  }
}