package com.example.viktornyblom.sqlitetest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter extends BaseAdapter {

    private List<DbItem> mDbItems;
    private Context mContext;

    public Adapter(Context context, List<DbItem> items) {
        mDbItems = items;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mDbItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mDbItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_db_item, parent, false);
        DbItem item = mDbItems.get(position);

        ((TextView)view.findViewById(R.id.textView)).setText("Id: " + item.getId());
        ((TextView)view.findViewById(R.id.textView2)).setText("FeedNumber: " + item.getFeedNumber());
        ((TextView)view.findViewById(R.id.textView3)).setText("Description: " + item.getDesc());
        ((TextView)view.findViewById(R.id.textView4)).setText("Title: " + item.getTitle());
        ((TextView)view.findViewById(R.id.textView5)).setText("Image: " + item.getImage());

        return view;
    }
}
