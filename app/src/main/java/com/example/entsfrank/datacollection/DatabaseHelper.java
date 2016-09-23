package com.example.entsfrank.datacollection;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by frank on 9/23/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Data.db";
    private static final String DATA_TYPE_TEXT = " TEXT, ";

    //    univeristy Table
    private static final String CREATE_CALLS_SQL =
            "CREATE TABLE "+ DataEntry.TABLE_NAME+" ("+
                    CallEntry._ID+" INTEGER PRIMARY KEY, "+
                    CallEntry.COLUMN_TITLE+DATA_TYPE_TEXT+ CallEntry.COLUMN_DESCRIPTION+
                    DATA_TYPE_TEXT+ CallEntry.COLUMN_ASSOCIATION+DATA_TYPE_TEXT+
                    CallEntry.COLUMN_TIME +DATA_TYPE_TEXT+CallEntry.COLUMN_REMINDER+
                    DATA_TYPE_TEXT+ CallEntry.COLUMN_PURPOSE+" TEXT )";
    private static final String DELETE_CALLS_SQL =
            "DROP TABLE IF EXISTS "+ CallEntry.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CALLS_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DELETE_CALLS_SQL);
        onCreate(db);
    }
}
