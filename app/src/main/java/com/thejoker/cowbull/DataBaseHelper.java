package com.thejoker.cowbull;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "user.db";
    public static final String TABLE_NAME = "user";
    public static final String COL_1 = "ID";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create Table " + TABLE_NAME + " (ID TEXT PRIMARY KEY)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertUser(String userId){
        clearUser();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,userId);
        return db.insert(TABLE_NAME, null, contentValues) != -1;
    }

    public String getUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        if(res.getCount() == 0)
            return null;
        res.moveToNext();
        return res.getString(0);
    }

    public void clearUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "1", null);
    }
}