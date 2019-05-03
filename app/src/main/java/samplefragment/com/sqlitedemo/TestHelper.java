package samplefragment.com.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TestHelper extends SQLiteOpenHelper {

    public static int version = 1;
    public static String dbName = "Student";


    public TestHelper(Context context) {
        super(context, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table  StudentTable " +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT," +
                "SURNAME TEXT," +
                "MARKS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TABLE_NAME");
    }

    private boolean insertData(int idVaue, String name, String surName, String marks) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", idVaue);
        contentValues.put("NAME", name);
        contentValues.put("ID", idVaue);
        contentValues.put("ID", idVaue);
        long result = sqLiteDatabase.insert("TABLE_NAME", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


}
