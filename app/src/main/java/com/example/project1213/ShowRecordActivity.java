package com.example.project1213;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ShowRecordActivity extends AppCompatActivity {

    private static final String TAG = "ShowRecordActivity";

    private boolean isOneSelf = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_record);

        Intent intent = getIntent();
        Record record = (Record) intent.getSerializableExtra("click_rec");
        if(intent.getIntExtra("id", 1) == 0){
            isOneSelf = true;
        }

        Account account = record.getAccount();
        //Log.d(TAG, "onCreate: " + account.getUserName());

        LinearLayout show_record_info = (LinearLayout)findViewById(R.id.show_record_info);
        ImageView show_record_head = (ImageView)findViewById(R.id.show_record_head);
        TextView show_record_name = (TextView) findViewById(R.id.show_record_name);

        TextView show_record_date = (TextView) findViewById(R.id.show_record_date);
        TextView show_record_loc = (TextView) findViewById(R.id.show_record_loc);
        TextView show_record_title = (TextView) findViewById(R.id.show_record_title);
        TextView show_record_text = (TextView) findViewById(R.id.show_record_text);
        ImageView show_record_image = (ImageView) findViewById(R.id.show_record_image);

        if(isOneSelf){
            show_record_info.setVisibility(View.GONE);
        }
        else{
            // find account
        }

        String date = record.getRecordDate();
        if(date == null) {
            date = "";
        }
        show_record_date.setText(record.getRecordDate());
        show_record_loc.setText(record.getRecordLocation());
        show_record_title.setText(record.getRecordTitle());
        show_record_text.setText(record.getRecordText());
        show_record_text.setMovementMethod(ScrollingMovementMethod.getInstance());

        //show_record_image
        byte[] im = record.getRecordImage();
        if (im != null && im.length != 0) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(im, 0, im.length);
            show_record_image.setImageBitmap(bitmap);
        }
        else{
            show_record_image.setVisibility(View.GONE);
        }

    }
}
