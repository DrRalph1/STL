package com.android_code_challenge.stl.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBController extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Bookmarks.db";
    public static final String TABLE_NAME = "bookmarks";
    public static final String COL_1 = "id";
    public static final String COL_2 = "bookmark_id";

    public DBController(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (id INTEGER PRIMARY KEY AUTOINCREMENT, bookmark_id TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String bookmark_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,bookmark_id);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select DISTINCT * from "+TABLE_NAME +" group by bookmark_id",null);
        return res;
    }

    public boolean getData(String bookmark_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME +" where bookmark_id = " + bookmark_id + " group by bookmark_id",null);
        while (res.moveToNext()){
            return true;
        }
            return false;
    }

    public boolean updateData(String id,String bookmark_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,bookmark_id);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }


    public void deleteData(String bookmark_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("DELETE from "+TABLE_NAME +" where bookmark_id = " + bookmark_id,null);
        while (res.moveToNext()){
            return;
        }
    }
}
