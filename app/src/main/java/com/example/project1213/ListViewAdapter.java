package com.example.project1213;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<Map>{
    private int resourceId;
    public ListViewAdapter(Context context, int textViewResourceId,
                           List<Map> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Map map=getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent,
                false);
    }
}
