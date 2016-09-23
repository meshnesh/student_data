package com.example.entsfrank.datacollection;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.example.entsfrank.datacollection.DataContract.DataEntry;
import com.example.entsfrank.datacollection.DatabaseHelper;


/**
 * Created by frank on 9/23/16.
 */
public class SaveData extends AsyncTask<Object,Void,Long> {
    private DatabaseHelper helper;
    private String name, age, gender, university, job, salary, company;

    public SaveData(Context context, String s, String name,
                    String age, String gender,
                    String university, String job,
                    String salary, String company
    ){
        this.helper = new DatabaseHelper(context);
        this.name = name;
        this.age=age;
        this.gender =gender;
        this.university =university;
        this.job =job;
        this.salary=salary;
        this.company=company;
    }

    @Override
    protected Long doInBackground(Object... objects) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataEntry.COLUMN_NAME, name);
        values.put(DataEntry.COLUMN_AGE, age);
        values.put(DataEntry.COLUMN_GENDER, gender);
        values.put(DataEntry.COLUMN_UNIVERSITY, university);
        values.put(DataEntry.COLUMN_JOB, job);
        values.put(DataEntry.COLUMN_SALARY, salary);
        values.put(DataEntry.COLUMN_COMPANY, company);

        long newRowId = db.insert(
                DataEntry.TABLE_NAME,
                null,
                values);
        db.close();
        return newRowId;
    }

}
