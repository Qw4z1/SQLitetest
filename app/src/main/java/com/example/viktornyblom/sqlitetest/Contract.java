package com.example.viktornyblom.sqlitetest;

import android.database.sqlite.SQLiteDatabase;

public class Contract {

    public static final String TABLE = "fott";

    public static final int VERSION = 1;

    public static void onCreate(SQLiteDatabase db) {
        String sql = "create table " + Contract.TABLE
                + "(" + Contract.Column.ID + " integer primary key autoincrement, "
                + Contract.Column.FEED_NUMBER + " text, "
                + Contract.Column.TITLE + " text, "
                + Contract.Column.DESCRIPTION + " text, "
                + Contract.Column.IMAGE + " text, "
                + "unique (" + Contract.Column.FEED_NUMBER + ") on conflict replace" + ")";

        db.execSQL(sql);
    }

    public static void onUpgrade(SQLiteDatabase db, int pOldVersion, int pNewVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Contract.TABLE);
        onCreate(db);
    }

    public class Column {
        public static final String ID = "id";
        public static final String FEED_NUMBER = "feed_number";
        public static final String TITLE = "title";
        public static final String DESCRIPTION = "description";
        public static final String IMAGE = "image";
    }
}
