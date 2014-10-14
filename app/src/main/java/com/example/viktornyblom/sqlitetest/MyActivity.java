package com.example.viktornyblom.sqlitetest;

import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class MyActivity extends ListActivity {

    private static final String TAG = "MyActivity";
    private SQLiteDatabase mDb;
    private List<DbItem> mItemList;

    private static final int COUNT = 5;

    private String[] mAllColumns = {Contract.Column.ID,
            Contract.Column.FEED_NUMBER,
            Contract.Column.TITLE,
            Contract.Column.DESCRIPTION,
            Contract.Column.IMAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDb = new DatabaseHelper(this).getWritableDatabase();
        createItems();
    }

    private void createItems() {
        mItemList = new ArrayList<DbItem>(COUNT);
        for (int i = 0; i < COUNT ; i++) {
            mItemList.add(new DbItem(i,
                    "title " + i,
                    "desc " + i,
                    "image " + i,
                    "feedNumber " + 1
                    ));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.idLoad) {
            load(true, false);
            populateListView();
            return true;
        }

        if (id == R.id.load) {
            load(false, false);
            populateListView();
            return true;
        }

        if (id == R.id.correct) {
            load(true, true);
            populateListView();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Testing that ContentValues are replaced
    private void dryRun() {
        ContentValues cv = new ContentValues();
        for (int i = 0; i < COUNT; i++) {
            cv.put(Contract.Column.TITLE, i);
            cv.put(Contract.Column.IMAGE, "image");
            Log.d(TAG, "ContentValues: " + cv.get(Contract.Column.TITLE) + " ; " + cv.get(Contract.Column.IMAGE));
        }
    }

    // Testing actual problem
    private void load(boolean withId, boolean withUpdate) {
        ContentValues cv = new ContentValues();

        for (int i = 0; i< COUNT; i++) {
            cv.put(Contract.Column.FEED_NUMBER, "feed number " + mItemList.get(i).getId());
            cv.put(Contract.Column.DESCRIPTION, mItemList.get(i).getDesc());
            cv.put(Contract.Column.IMAGE, mItemList.get(i).getImage());
            cv.put(Contract.Column.TITLE, mItemList.get(i).getTitle());
            if (withId) {
                cv.put(Contract.Column.ID, mItemList.get(i).getId());
            }

            long row = mDb.insertWithOnConflict(Contract.TABLE, null, cv, SQLiteDatabase.CONFLICT_IGNORE);
            if(row == -1 && withUpdate) {
                row = mDb.update(Contract.TABLE, cv, Contract.Column.ID + " = ?", new String[]{Integer.toString(mItemList.get(i).getId())});
            }
            Log.d(TAG, "ContentValues: " + cv.get(Contract.Column.FEED_NUMBER) +
                    " \n " + cv.get(Contract.Column.DESCRIPTION) +
                    " \n " + cv.get(Contract.Column.IMAGE) +
                    " \n " + cv.get(Contract.Column.TITLE) +
                    " \n " + cv.get(Contract.Column.ID) +
                    " \n " + row);
        }
    }

    private void populateListView() {
        Cursor cursor = mDb.query(Contract.TABLE, mAllColumns, null, null, null, null, null);
        cursor.moveToFirst();
        List<DbItem> list = new ArrayList<DbItem>();
        while (!cursor.isAfterLast()) {
            DbItem item = new DbItem(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4));
            list.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        getListView().setAdapter(new Adapter(this, list));
    }
}
