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
import com.example.ixithon.model.VisitLocation;

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
      ArrayList<TravellerInvite> travellerInvites = plan.getTravellerInvites();
      TravellerInvite travellerInvite = new TravellerInvite();
      travellerInvite.setTravellerName(plan.getUserID());
      travellerInvite.setTravellerID(plan.getUserID());
      travellerInvite.setStatus(new TravellerInvite.TravelStatus(0));
      travellerInvite.setPlanID(plan.getPlanID());
      travellerInvites.add(travellerInvite);
      addInvitations(travellerInvites, plan.getPlanID());
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
      statement.bindLong(3, travellerInvite.getStatus().getValue());
      statement.bindLong(4, planId);
      statement.executeInsert();
    }
  }

  private void addUserDecision(ArrayList<UserDecision> userDecisions, long planId) {
    if (userDecisions != null) {
      SQLiteStatement statement = db.compileStatement(UserDecisionContract.UserDecisionEntry.INSERT_DATA);
      statement.clearBindings();
      for (UserDecision userDecision : userDecisions) {
        statement.bindString(1, userDecision.getUserID());
        statement.bindString(2, userDecision.getHotel());
        statement.bindString(3, userDecision.getTravelMode());
        statement.bindLong(4, planId);
        statement.executeInsert();
        addVisitLocations(userDecision.getVisitLocations(), userDecision.getUserID());
      }
    }
  }

  private void addVisitLocations(ArrayList<VisitLocation> visitLocations, String userId) {
    SQLiteStatement statement = db.compileStatement(UserDecisionContract.UserDecisionEntry.INSERT_DATA);
    statement.clearBindings();
    for (VisitLocation userDecision : visitLocations) {
      statement.bindString(1, userDecision.getUserId());
      statement.bindString(2, userDecision.getLocation());
      statement.bindString(3, userDecision.getLocationID());
      statement.executeInsert();
    }
  }

  public ArrayList<Plan> getPlansFromUserId(String userID) {
    ArrayList<Long> idList = new ArrayList<>();
    Cursor cursor = null;
    try {
      cursor = db.rawQuery("select * from " + TravellerInviteContract.TravellerInviteEntry.TABLE_NAME + " where " + TravellerInviteContract.TravellerInviteEntry.COLUMN_TRAVELLER_ID + "='" + userID+ "';" , null);
      while (cursor.moveToNext()) {
        String id = cursor.getString(cursor.getColumnIndex(TravellerInviteContract.TravellerInviteEntry.COLUMN_TRAVELLER_ID));
        if (userID.equals(id)) {
          long planID = cursor.getLong(cursor.getColumnIndex(TravellerInviteContract.TravellerInviteEntry.COLUMN_PLAN_ID));
          idList.add(planID);
        }
      }
    } catch (SQLiteException e) {
      Log.e(TravelPlanDBSource.class.getSimpleName(), e.getMessage());
    } finally {
      if (cursor != null && !cursor.isClosed()) cursor.close();
    }
    return getPlans(idList);
  }

  public ArrayList<Plan> getPlans(ArrayList<Long> plansList) {
    ArrayList<Plan> plans = new ArrayList<>();
    Plan plan = null;
    Cursor cursor = null;
    String query;
    try {
      query = "select * from " + PlanListContract.PlanListEntry.TABLE_NAME + " where " + PlanListContract.PlanListEntry._ID + " in (";
      StringBuilder stringBuilder = new StringBuilder(query);
      for (int i = 0; i < plansList.size(); i++) {
        if (i != 0) {
          stringBuilder.append(",");
        }
        stringBuilder.append(plansList.get(i));
      }
      stringBuilder.append(");");
      cursor = db.rawQuery(stringBuilder.toString(), null);
      while (cursor.moveToNext()) {
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
    } catch (SQLiteException e) {
      Log.e(TravelPlanDBSource.class.getSimpleName(), e.getMessage());
    } finally {
      if (cursor != null && !cursor.isClosed()) cursor.close();
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
        if (id == planId) {
          travellerInvite = new TravellerInvite();
          travellerInvite.setPlanID(cursor.getLong(cursor.getColumnIndex(TravellerInviteContract.TravellerInviteEntry.COLUMN_PLAN_ID)));
          travellerInvite.setTravellerID(cursor.getString(cursor.getColumnIndex(TravellerInviteContract.TravellerInviteEntry.COLUMN_TRAVELLER_ID)));
          travellerInvite.setTravellerName(cursor.getString(cursor.getColumnIndex(TravellerInviteContract.TravellerInviteEntry.COLUMN_TRAVELLER_NAME)));
          travellerInvite.setStatus(new TravellerInvite.TravelStatus(cursor.getLong(cursor.getColumnIndex(TravellerInviteContract.TravellerInviteEntry.COLUMN_INVITE_STATUS))));
          travellerInvite.setPlanID(cursor.getLong(cursor.getColumnIndex(TravellerInviteContract.TravellerInviteEntry.COLUMN_PLAN_ID)));
          travellerInvites.add(travellerInvite);
        }
      }
    } catch (SQLiteException e) {
      Log.e(TravelPlanDBSource.class.getSimpleName(), e.getMessage());
    } finally {
      if (cursor != null && !cursor.isClosed()) cursor.close();
    }
    return travellerInvites;
  }

  private ArrayList<UserDecision> getDecisionsList(long planId) {
    Cursor cursor = null;
    ArrayList<UserDecision> userDecisions = new ArrayList<>();
    UserDecision userDecision = null;
    try {
      cursor = db.rawQuery(UserDecisionContract.UserDecisionEntry.SELECT_QUERY, new String[]{String.valueOf(planId)});
      while (cursor.moveToNext()) {
        long id = cursor.getLong(cursor.getColumnIndex(UserDecisionContract.UserDecisionEntry.COLUMN_PLAN_ID));
        if (id == planId) {
          userDecision = new UserDecision();
          userDecision.setPlanID(cursor.getLong(cursor.getColumnIndex(UserDecisionContract.UserDecisionEntry.COLUMN_PLAN_ID)));
          userDecision.setHotel(cursor.getString(cursor.getColumnIndex(UserDecisionContract.UserDecisionEntry.COLUMN_HOTEL_CHOICE)));
          userDecision.setTravelMode(cursor.getString(cursor.getColumnIndex(UserDecisionContract.UserDecisionEntry.COLUMN_TRAVEL_MODE_CHOICE)));
          userDecision.setUserID(cursor.getString(cursor.getColumnIndex(UserDecisionContract.UserDecisionEntry.COLUMN_USER_ID)));

          // todo add visit locations object
//          userDecision.setVisitLocations(getVisit);
          userDecisions.add(userDecision);
        }
      }
    } catch (SQLiteException e) {
      Log.e(TravelPlanDBSource.class.getSimpleName(), e.getMessage());
    } finally {
      if (cursor != null && !cursor.isClosed()) cursor.close();
    }
    return userDecisions;
  }

  public void closeDb() {
    if (db != null) {
      db.close();
      db = null;
    }
  }
}
