package com.example.viktornyblom.sqlitetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

    public DatabaseHelper(Context context) {
        super(context, "database.db", null, Contract.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Contract.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Contract.onUpgrade(db, oldVersion, newVersion);
    }
}
