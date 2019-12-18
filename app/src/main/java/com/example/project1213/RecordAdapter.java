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
            Record record = getItem(position);
            View view;
            ViewHolder viewHolder;
            if(convertView == null) {
                view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
                viewHolder = new ViewHolder();

                viewHolder.title =( TextView)view.findViewById(R.id.my_listview_title);
                viewHolder.summary = (TextView)view.findViewById(R.id.my_listview_summary);
                viewHolder.date = (TextView)view.findViewById(R.id.my_listview_date);

                view.setTag(viewHolder); // 将ViewHolder存储在View中
            }
            else{
                view = convertView;
                viewHolder = (ViewHolder) view.getTag();
            }

            String record_title = record.getRecordTitle();
            if(record_title == null){
                viewHolder.title.setText(R.string.no_title);
            }
            else {
                viewHolder.title.setText(record.getRecordTitle());
            }

            viewHolder.summary.setText(record.getRecordText());
            viewHolder.date.setText(record.getRecordDate().substring(0, 11));
            return view;
        }

        class ViewHolder{

            TextView title;

            TextView summary;

            TextView date;
        }
}
