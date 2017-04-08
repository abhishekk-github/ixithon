package com.example.ixithon.db;

import android.provider.BaseColumns;

public final class PlanDetailsContract {

  private PlanDetailsContract() {}

  public static class PlanDetailsEntry implements BaseColumns {

    public static final String COLUMN_TABLE_NAME = "PLAN_DETAILS";

    public static final String COLUMN_PLAN_ID = "PLAN_ID";
    public static final String COLUMN_START_POINT = "START_POINT";
    public static final String COLUMN_START_DATE = "START_DATE";
    public static final String COLUMN_END_DATE = "END_DATE";

  }
}
