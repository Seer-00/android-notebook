package com.example.project1213;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class testDatabase extends AppCompatActivity {

    private static final String TAG = "testDatabase";

    // testDatabase 只测试该用户的第一个记录
    // Record record = recordList.get(i); 可以得到第i个记录
    // recordList.size() 可以得到该用户一共有多少个记录

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_database);

        Intent intent = getIntent();
        String user_login = intent.getStringExtra("test_user");

        Connector.getDatabase();

        List<Account> accountList = LitePal.where("userName == ?", user_login)
                .find(Account.class, true);

        // 得到 user_login 对应的account
        Account account = accountList.get(0);

        List<Record> recordList = account.getRecordList();
        Log.d(TAG, "onCreate: " + user_login + " has " + recordList.size() + " records.");

        if(recordList.size() == 0){
            return;
        }
        // ！！！ just test the FIRST record of user_login
        Record record = recordList.get(0);

        // show title
        TextView title = (TextView) findViewById(R.id.test_title);
        title.setText(record.getRecordTitle());

        // show text
        TextView text = (TextView) findViewById(R.id.test_text);
        text.setText(record.getRecordText());

        // show Date
        TextView date = (TextView) findViewById(R.id.test_date);
        date.setText(record.getRecordDate());

        // show Picture
        ImageView image = (ImageView) findViewById(R.id.test_image);
        byte[] im = record.getRecordImage();
        if (im == null) {
            Log.d(TAG, "onCreate: Image is NULL");
        }
        else{
            Log.d(TAG, "onCreate: Image is NOT NULL");
            Bitmap bitmap = BitmapFactory.decodeByteArray(im, 0, im.length);
            image.setImageBitmap(bitmap);
        }
    }
}
