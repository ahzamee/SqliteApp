package com.example.zamee.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zamee on 01/04/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DatabaseName = "Student.db";
    public static final String TableName = "Student_table";
    public static final String Col1 = "ID";
    public static final String Col2 = "NAME";
    public static final String Col3 = "SURNAME";
    public static final String Col4 = "MARKS";

    public DatabaseHelper(Context context) {
        super(context, DatabaseName, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table " + TableName + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,MARKS INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name, String surname, String marks){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col2,name);
        contentValues.put(Col3,surname);
        contentValues.put(Col4,marks);
        long result = sqLiteDatabase.insert(TableName, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from "+ TableName, null );
        return res;
    }

    public boolean updateData(String id, String name, String surname, String marks){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col1,id);
        contentValues.put(Col2,name);
        contentValues.put(Col3,surname);
        contentValues.put(Col4,marks);
        sqLiteDatabase.update(TableName, contentValues, "ID = ?", new String[]{id});
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TableName,"ID = ?", new String[] {id});
    }
}
