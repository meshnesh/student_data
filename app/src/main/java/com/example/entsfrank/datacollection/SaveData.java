package com.example.entsfrank.datacollection;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.example.entsfrank.datacollection.DataContract.DataEntry


/**
 * Created by frank on 9/23/16.
 */
public class SaveData extends AsyncTask<Object,Void,Long> {
    private DatabaseHelper helper;
    private String title, description, association, purpose, time, reminder;

    public SaveData(Context context, String title,
                    String description, String association,
                    String purpose, String time,
                    String reminder
    ){
        this.helper = new DatabaseHelper(context);
        this.title = title;
        this.description=description;
        this.association =association;
        this.purpose =purpose;
        this.time =time;
        this.reminder=reminder;
    }

    @Override
    protected Long doInBackground(Object... objects) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataEntry.COLUMN_TITLE, title);
        values.put(DataEntry.COLUMN_DESCRIPTION, description);
        values.put(DataEntry.COLUMN_ASSOCIATION, association);
        values.put(DataEntry.COLUMN_PURPOSE, purpose);
        values.put(DataEntry.COLUMN_TIME, time);
        values.put(DataEntry.COLUMN_REMINDER, reminder);

        long newRowId = db.insert(
                DataEntry.TABLE_NAME,
                null,
                values);
        db.close();
        return newRowId;
    }

}
