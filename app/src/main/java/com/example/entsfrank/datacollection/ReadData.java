package com.example.entsfrank.datacollection;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.example.entsfrank.datacollection.DataContract.DataEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frank on 9/23/16.
 */
public class ReadData extends AsyncTask<Object,Void,List<DataContract>> {

    private DatabaseHelper helper;
    private static String[] projection = {
            DataEntry._ID,
            DataEntry.COLUMN_NAME, DataEntry.COLUMN_AGE,
            DataEntry.COLUMN_GENDER, DataEntry.COLUMN_UNIVERSITY,
            DataEntry.COLUMN_JOB, DataEntry.COLUMN_SALARY, DataEntry.COLUMN_COMPANY
    };
    public ReadData(Context c) {
        this.helper = new DatabaseHelper(c);
    }
    @Override
    protected List<DataContract> doInBackground(Object[] objects) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(
                DataEntry.TABLE_NAME, projection,
                null, null, null, null,
                DataEntry._ID + " DESC"
        );
        List<DataContract> calls = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                calls.add(new DataContract(
                        cursor.getInt(0),cursor.getString(1),
                        cursor.getString(2),cursor.getString(3),
                        cursor.getString(4),cursor.getString(5),
                        cursor.getString(6),cursor.getString(7)
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return calls;
    }
}
