package com.example.project1213;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class RecordAdapter extends ArrayAdapter<Record>{
        private int resourceId;

        public RecordAdapter(@NonNull Context context, int resource, @NonNull List<Record> objects) {
            super(context, resource, objects);
            resourceId = resource;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            Record record=getItem(position);
            View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            TextView title=(TextView)view.findViewById(R.id.my_listview_title);
            TextView summary=(TextView)view.findViewById(R.id.my_listview_summary);
            TextView date=(TextView)view.findViewById(R.id.my_listview_date);
            title.setText(record.getRecordTitle());
            summary.setText(record.getRecordText());
            date.setText(record.getRecordDate());
            return view;

        }
}
