package com.example.mad_21s21331;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbHelper2 extends SQLiteOpenHelper {

    public static final String Moh = "AdminDataBase.db";
    public static final String TableName = "Reservations";
    public static final String FIRST_COLUMN = "Occasion";
    public static final String SECOND_COLUMN = "NumberOfPeople";

    public static final String THIRD_COLUMN = "PhoneNumber";
    public static final String FOURTH_COLUMN = "Price";


    public dbHelper2(Context Madi1) {
        super(Madi1, Moh, null, 1);
    }

    public void onCreate(SQLiteDatabase database){
        String CreateTable="CREATE TABLE IF NOT EXISTS " + TableName+"(" + FIRST_COLUMN + "TEXT, " + SECOND_COLUMN + "TEXT, " + THIRD_COLUMN + "TEXT PRIMARY KEY, " + FOURTH_COLUMN + "TEXT  )";
        database.execSQL(CreateTable);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);
    }

    public boolean NewReservation( String Occasion, String NumberOfPeople, String PhoneNumber, String Price){
        SQLiteDatabase dbMadi=this.getReadableDatabase();
        ContentValues Madi114= new ContentValues();
        Madi114.put(FIRST_COLUMN,Occasion);
        Madi114.put(SECOND_COLUMN,NumberOfPeople);
        Madi114.put(THIRD_COLUMN,PhoneNumber);
        Madi114.put(FOURTH_COLUMN,Price);

        long MadiResult=dbMadi.insert(TableName, null,Madi114);
        dbMadi.close();
        if(MadiResult==1)
            return false;
            else
                return true;
    }

    public String MOHRservation (String ReservationRecord){
        SQLiteDatabase dbMadi = this.getReadableDatabase();
        Cursor crsr114 = dbMadi.rawQuery("SELECT * FROM " + TableName + "WHERE " + "PhoneNumber = " + ReservationRecord+"'", null);
        crsr114.moveToFirst();
        String Record= crsr114.getString(2);
        dbMadi.close();
        return Record;
    }

}



