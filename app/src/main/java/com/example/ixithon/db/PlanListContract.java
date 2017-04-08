package com.example.ixithon.db;

import android.provider.BaseColumns;

public final class PlanListContract {

  private PlanListContract() {}

  public static class PlanListEntry implements BaseColumns {

    public static final String COLUMN_TABLE_NAME = "PLAN_LIST";

    public static final String COLUMN_PLAN_ID = "PLAN_ID";
    public static final String COLUMN_USER_ID = "USER_ID";
    public static final String COLUMN_USER_NAME = "USER_NAME";
    public static final String COLUMN_PLAN_STATUS = "PLAN_STATUS";
  }
}
