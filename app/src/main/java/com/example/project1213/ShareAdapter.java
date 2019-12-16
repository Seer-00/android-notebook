package com.example.project1213;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ShareAdapter extends ArrayAdapter {
    private int resourceId;

    public ShareAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        resourceId=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView image=(ImageView)view.findViewById(R.id.image);
        TextView title=(TextView)view.findViewById(R.id.title);
        TextView summary=(TextView)view.findViewById(R.id.info);
        TextView date=(TextView)view.findViewById(R.id.my_listview_date);

        //还没传值

        return view;

    }
}
