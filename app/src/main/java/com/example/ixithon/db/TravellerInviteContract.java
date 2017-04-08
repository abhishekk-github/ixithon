package com.example.ixithon.db;

import android.provider.BaseColumns;

public final class TravellerInviteContract {

    private TravellerInviteContract() {}

    public static class TravellerInviteEntry implements BaseColumns {

        public static final String TABLE_NAME = "TRAVELLER_INVITES";
        public static final String COLUMN_TRAVELLER_NAME = "TRAVELLER_NAME";
        public static final String COLUMN_TRAVELLER_ID = "TRAVELLER_ID";
    }
}
