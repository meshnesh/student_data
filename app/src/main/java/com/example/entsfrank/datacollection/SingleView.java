package com.example.entsfrank.datacollection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.example.entsfrank.datacollection.DataContract.DataEntry;

/**
 * Created by frank on 9/23/16.
 */
public class SingleView extends AsyncTask<Object,Void,DataContract> {
    private DatabaseHelper helper;
    private static String[] projection = {
            DataEntry._ID,
            DataEntry.COLUMN_TITLE, DataEntry.COLUMN_DESCRIPTION,
            DataEntry.COLUMN_PURPOSE, DataEntry.COLUMN_ASSOCIATION,
            DataEntry.COLUMN_TIME, DataEntry.COLUMN_REMINDER
    };
    private String type;
    private String[] selectionArgs;
    private ContentValues values;
    private String selection = DataEntry._ID + " = ?";
    public SingleView(Context c, long id, String type, ContentValues values) {
        this.helper = new DatabaseHelper(c);
        this.selectionArgs = new String[]{id+""};
        this.type=type;
        this.values=values;
    }
    @Override
    protected DataContract doInBackground(Object... objects) {
        DataContract event = null;
        switch (type) {
            case "read":
                event = getEvent();
                break;
            case "update":
                event = updateEvent(values);
                break;
            case "delete":
                event = deleteEvent();
                break;
        }

        return event;
    }

    public DataContract getEvent(){
        SQLiteDatabase db = helper.getReadableDatabase();
        DataContract event = null;
        Cursor cursor = db.query(
                DataEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            event = new DataContract(
                    cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3),
                    cursor.getString(4), cursor.getString(5),
                    cursor.getString(6)
            );
        }
        cursor.close();
        return event;
    }

    public DataContract updateEvent(ContentValues values){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.update(
                DataEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
        db.close();

        return null;
    }

    public DataContract deleteEvent(){
        SQLiteDatabase db = helper.getReadableDatabase();
        db.delete(
                DataEntry.TABLE_NAME,
                selection,
                selectionArgs
        );
        return null;
    }
}
