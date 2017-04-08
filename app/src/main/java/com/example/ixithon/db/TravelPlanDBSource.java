package com.example.ixithon.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.ixithon.model.Plan;
import com.example.ixithon.model.TravellerInvite;
import com.example.ixithon.model.UserDecision;

import java.util.ArrayList;

public final class TravelPlanDBSource {
  private TravelPlanSQLiteOpenHelper helper;
  private SQLiteDatabase db;

  public TravelPlanDBSource(Context context) {
    helper = new TravelPlanSQLiteOpenHelper(context);
  }

  public void openDb() {
    db = helper.getWritableDatabase();
  }

  public boolean addPlan(Plan plan) {
    if (plan == null) {
      return false;
    }
    try {
      db.beginTransaction();
      SQLiteStatement statement = db.compileStatement(PlanListContract.PlanListEntry.INSERT_DATA);
      statement.clearBindings();
      statement.bindLong(1, plan.getPlanID());
      statement.bindString(2, plan.getStartPoint());
      statement.bindString(3, plan.getDestinationPoint());
      statement.bindString(4, plan.getUserID());
      long rowId = statement.executeInsert();
      addInvitations(plan.getTravellerInvites(), plan.getPlanID());
      addUserDecision(plan.getUserDecisions(), plan.getPlanID());
      db.setTransactionSuccessful();
      return true;
    } catch (SQLiteException e) {
      return false;
    } finally {
      if (db != null && db.isOpen()) {
        db.endTransaction();
        db.close();
      }
    }
  }

  private void addInvitations(ArrayList<TravellerInvite> travellerInvites, long planId) {
    SQLiteStatement statement = db.compileStatement(TravellerInviteContract.TravellerInviteEntry.INSERT_DATA);
    TravellerInvite travellerInvite;
    for (int i = 0; i < travellerInvites.size(); i++) {
      travellerInvite = travellerInvites.get(i);
      statement.clearBindings();
      statement.bindString(1, travellerInvite.getTravellerID());
      statement.bindString(2, travellerInvite.getTravellerName());
      statement.bindLong(3, planId);
      statement.executeInsert();
    }
  }

  private void addUserDecision(ArrayList<UserDecision> userDecisions, long planId) {
    SQLiteStatement statement = db.compileStatement(UserDecisionContract.UserDecisionEntry.INSERT_DATA);
    statement.clearBindings();
    for (UserDecision userDecision : userDecisions) {
      statement.bindString(1, userDecision.getUserID());
      statement.bindString(2, userDecision.getHotel());
      statement.bindString(3, userDecision.getTravelMode());
      statement.bindLong(4, planId);
      statement.executeInsert();
    }
  }

  private ArrayList<Plan> getPlans(String userID) {
    ArrayList<Plan> plans = new ArrayList<>();
    Plan plan = null;
    Cursor cursor = null;
    try {
      cursor = db.rawQuery("select * from " + PlanListContract.PlanListEntry.TABLE_NAME + " where " + PlanListContract.PlanListEntry.COLUMN_USER_ID + "=" + userID, null);
      while (cursor.moveToNext()) {
        String id = cursor.getString(cursor.getColumnIndex(PlanListContract.PlanListEntry.COLUMN_USER_ID));
        if (userID.equals(id)) {
          plan = new Plan();
          plan.setPlanID(cursor.getLong(cursor.getColumnIndex(PlanListContract.PlanListEntry._ID)));
          plan.setUserID(cursor.getString(cursor.getColumnIndex(PlanListContract.PlanListEntry.COLUMN_USER_ID)));
          plan.setStartPoint(cursor.getString(cursor.getColumnIndex(PlanListContract.PlanListEntry.COLUMN_START_POINT)));
          plan.setDestinationPoint(cursor.getString(cursor.getColumnIndex(PlanListContract.PlanListEntry.COLUMN_DESTINATION_POINT)));

          ArrayList<UserDecision> userDecisions = getDecisionsList(plan.getPlanID());
          ArrayList<TravellerInvite> travellerInvites = getTravellerInvites(plan.getPlanID());
          plan.setUserDecisions(userDecisions);
          plan.setTravellerInvites(travellerInvites);
          plans.add(plan);
        }
      }
    } catch (SQLiteException e) {
      Log.e(TravelPlanDBSource.class.getSimpleName(), e.getMessage());
    } finally {
      if (cursor != null && !cursor.isClosed()) cursor.close();
      if (db != null && db.isOpen()) db.close();
    }
    return plans;
  }

  private ArrayList<TravellerInvite> getTravellerInvites(long planId) {
    Cursor cursor = null;
    ArrayList<TravellerInvite> travellerInvites = new ArrayList<>();
    TravellerInvite travellerInvite = null;
    try {
      cursor = db.rawQuery(TravellerInviteContract.TravellerInviteEntry.SELECT_QUERY, new String[]{String.valueOf(planId)});
      while (cursor.moveToNext()) {
        long id = cursor.getLong(cursor.getColumnIndex(TravellerInviteContract.TravellerInviteEntry.COLUMN_PLAN_ID));
        if (id == planId ) {
          travellerInvite = new TravellerInvite();
          travellerInvite.setPlanID(cursor.getLong(cursor.getColumnIndex(TravellerInviteContract.TravellerInviteEntry.COLUMN_PLAN_ID)));
          travellerInvite.setTravellerID(cursor.getString(cursor.getColumnIndex(TravellerInviteContract.TravellerInviteEntry.COLUMN_TRAVELLER_ID)));
          travellerInvite.setTravellerName(cursor.getString(cursor.getColumnIndex(TravellerInviteContract.TravellerInviteEntry.COLUMN_TRAVELLER_NAME)));
          travellerInvite.setPlanID(cursor.getLong(cursor.getColumnIndex(TravellerInviteContract.TravellerInviteEntry.COLUMN_PLAN_ID)));
          travellerInvites.add(travellerInvite);
        }
      }
    } catch (SQLiteException e) {
      Log.e(TravelPlanDBSource.class.getSimpleName(), e.getMessage());
    } finally {
      if (cursor != null && !cursor.isClosed()) cursor.close();
      if (db != null && db.isOpen()) db.close();
    }
    return travellerInvites;
  }

  private ArrayList<UserDecision> getDecisionsList(long planId) {
    return null;
  }

  public void closeDb() {
    if (db != null) {
      db.close();
      db = null;
    }
  }
}
