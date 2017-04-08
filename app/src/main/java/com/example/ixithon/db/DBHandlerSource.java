package com.example.ixithon.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBHandlerSource {
  private TravellerSQLiteOpenHelper helper;
  private SQLiteDatabase db;

  public DBHandlerSource(Context context) {
    helper = new TravellerSQLiteOpenHelper(context);
  }

  public void openDb() {
    db = helper.getWritableDatabase();
  }

  public void closeDb() {
    if (db != null) {
      db.close();
      db = null;
    }
  }
}
