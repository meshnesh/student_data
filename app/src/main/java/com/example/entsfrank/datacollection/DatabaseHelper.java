package com.example.entsfrank.datacollection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.entsfrank.datacollection.DataContract.DataEntry;


/**
 * Created by frank on 9/23/16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "University.db";
    private static final String DATA_TYPE_TEXT = " TEXT, ";
;

    //    univeristy Table
    private static final String CREATE_DB_SQL =
            "CREATE TABLE "+ DataEntry.TABLE_NAME+" ("+
                    DataEntry._ID+" INTEGER PRIMARY KEY, "+
                    DataEntry.COLUMN_NAME+DATA_TYPE_TEXT+ DataEntry.COLUMN_AGE+
                    DATA_TYPE_TEXT+ DataEntry.COLUMN_GENDER+DATA_TYPE_TEXT+
                    DataEntry.COLUMN_UNIVERSITY +DATA_TYPE_TEXT+DataEntry.COLUMN_JOB+
                    DATA_TYPE_TEXT+ DataEntry.COLUMN_SALARY+DataEntry.COLUMN_COMPANY+ " TEXT )";
    private static final String DELETE_DB_SQL =
            "DROP TABLE IF EXISTS "+ DataEntry.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DELETE_DB_SQL);
        onCreate(db);
    }
}
