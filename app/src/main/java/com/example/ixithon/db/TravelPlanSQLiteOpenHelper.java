package com.example.ixithon.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TravelPlanSQLiteOpenHelper extends SQLiteOpenHelper {

  private static final int DATABASE_VERSION = 1;
  private static final String DATABASE_NAME = "TripPlan.db";

  public TravelPlanSQLiteOpenHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(PlanListContract.PlanListEntry.CREATE_TABLE);
    db.execSQL(TravellerInviteContract.TravellerInviteEntry.CREATE_TABLE);
    db.execSQL(UserDecisionContract.UserDecisionEntry.CREATE_TABLE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}