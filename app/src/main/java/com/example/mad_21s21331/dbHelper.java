package com.example.mad_21s21331;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class dbHelper extends SQLiteOpenHelper {
    public static final String Moh="UserDataBase.db";
    public static final String TableName="Login";
    public static final String FIRST_COLUMN="FullName";
    public static final String SECOND_COLUMN="PhoneNumber";
    public static final String THIRD_COLUMN="Password";
    public dbHelper(Context context){
        super(context,Moh,null,1);
    }
    public void onCreate(SQLiteDatabase database){
        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS " +TableName+"(" + FIRST_COLUMN + " TEXT, " + SECOND_COLUMN + " TEXT PRIMARY KEY, " + THIRD_COLUMN + " TEXT )";
        database.execSQL(CREATE_TABLE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);
    }
    public  boolean NewRegistration(String FullName, String PhoneNumber, String Password){
        SQLiteDatabase dbMoh=this.getReadableDatabase();
        ContentValues Madi = new ContentValues();
        Madi.put(FIRST_COLUMN,FullName);
        Madi.put(SECOND_COLUMN,PhoneNumber);
        Madi.put(THIRD_COLUMN,Password);
        long Mohresult= dbMoh.insert(TableName,null,Madi);
        dbMoh.close();
        if (Mohresult==1)
            return false;
            else
                return true;
    }
    public String MohLogin (String PHN){
        SQLiteDatabase dbMoh = this.getReadableDatabase();
        Cursor crsr=dbMoh.rawQuery("SELECT * FROM "+ TableName +" WHERE " + "PhoneNumber = '"+PHN+"'",null);
        crsr.moveToFirst();
        String Pass=crsr.getString(2);
        dbMoh.close();
        return Pass;
    }

}