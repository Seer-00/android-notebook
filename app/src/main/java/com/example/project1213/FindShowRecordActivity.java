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

import org.litepal.LitePal;

import java.util.List;

public class FindShowRecordActivity extends AppCompatActivity {

    private static final String TAG = "FindShowRecordActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_show_record);

        Intent intent = getIntent();
        String username = intent.getStringExtra("usr");
        List<Account> accountList = LitePal.where("userName=?", username).find(Account.class,true);
        Account account = accountList.get(0);
        Record record = account.getRecordList().get(0);

        ImageView f_show_record_head = (ImageView)findViewById(R.id.f_show_record_head);
        TextView f_show_record_name = (TextView) findViewById(R.id.f_show_record_name);

        TextView f_show_record_date = (TextView) findViewById(R.id.f_show_record_date);
        TextView f_show_record_loc = (TextView) findViewById(R.id.f_show_record_loc);
        TextView f_show_record_title = (TextView) findViewById(R.id.f_show_record_title);
        TextView f_show_record_text = (TextView) findViewById(R.id.f_show_record_text);
        ImageView f_show_record_image = (ImageView) findViewById(R.id.f_show_record_image);

        // set headshot
        byte[] urim = account.getUserImage();
        if (urim == null) {
            Log.d(TAG, "onCreate: User Image is NULL");
        }
        else{
            Log.d(TAG, "onCreate: User Image is NOT NULL " + urim.length);
            Bitmap bitmap = BitmapFactory.decodeByteArray(urim, 0, urim.length);
            f_show_record_head.setImageBitmap(bitmap);
        }

        // set username
        f_show_record_name.setText(account.getUserName());

        f_show_record_date.setText(record.getRecordDate());
        f_show_record_loc.setText(record.getRecordLocation());
        f_show_record_title.setText(record.getRecordTitle());
        f_show_record_text.setText(record.getRecordText());
        f_show_record_text.setMovementMethod(ScrollingMovementMethod.getInstance());

        //show_record_image
        byte[] im = record.getRecordImage();
        if (im != null && im.length != 0) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(im, 0, im.length);
            f_show_record_image.setImageBitmap(bitmap);
        }
        else{
            f_show_record_image.setVisibility(View.GONE);
        }
    }
}
