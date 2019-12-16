package com.example.project1213;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ShareAdapter extends ArrayAdapter<Account> {
    private int resourceId;

    public ShareAdapter(@NonNull Context context, int resource, @NonNull List<Account> objects) {
        super(context, resource, objects);
        resourceId=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Account account = getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId, parent,false);

        List<Record> recordList = account.getRecordList();
        Record record = recordList.get(0);

        // set UserImage
        byte[] urim = account.getUserImage();
        ImageView image=(ImageView)view.findViewById(R.id.image);
        Bitmap bitmap = BitmapFactory.decodeByteArray(urim, 0, urim.length);
        image.setImageBitmap(bitmap);

        // set title
        TextView title=(TextView)view.findViewById(R.id.title);
        String rc_title = record.getRecordTitle();
        title.setText(rc_title);

        // set text
        TextView summary=(TextView)view.findViewById(R.id.info);
        String re_text = record.getRecordText();
        summary.setText(re_text);

        // set date
        TextView date=(TextView)view.findViewById(R.id.my_listview_date);
        String rc_date = record.getRecordDate();
        date.setText(rc_date);

        return view;

    }
}
