package com.example.entsfrank.datacollection;

import android.provider.BaseColumns;

/**
 * Created by frank on 9/23/16.
 */
public class DataContract {
    private String _name, _age, _gender, _university, _job, _salary, _company;
    private int _id;

    public DataContract(int id, String name, String age, String gender, String university, String job, String salary, String company){
        this._name = name;
        this._age=age;
        this._gender =gender;
        this._university =university;
        this._job =job;
        this._id=id;
        this._salary=salary;
        this._company=company;
    }
    public static abstract class DataEntry implements BaseColumns {
        public static final String TABLE_NAME = "Uni_data";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_AGE = "age";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_UNIVERSITY = "university";
        public static final String COLUMN_JOB = "job";
        public static final String COLUMN_SALARY = "salary";
        public static final String COLUMN_COMPANY = "company";
    }

    public String getReminder() {
        return _job;
    }

    public String getDescription() {
        return _age;
    }

    public String getAssociation() {
        return _gender;
    }

    public String getPurpose() {
        return _university;
    }

    public String getTitle() {
        return _name;
    }

    public String getTime() {
        return _salary;
    }

    public String get_company() {
        return _company;
    }

    public int getId() {
        return _id;
    }

    @Override
    public String toString() {
        return "AppointmentContract("+
                "name="+_name+", age="+_age+
                ", gender="+ _gender +", university="+_university+
                ", job="+ _job +", salary="+ _salary +", company="+ _company +")";
    }
}
