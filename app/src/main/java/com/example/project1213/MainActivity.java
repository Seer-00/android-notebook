package com.example.project1213;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button2 = (Button) findViewById(R.id.to_Main2Activity);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

        Connector.getDatabase();

        createUser();

    }

    public void createUser(){
        String usrn = "TomCat";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis() - (int)(Math.random()*604800));
        String date = formatter.format(curDate);

        Bitmap mBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.headshot_1)).getBitmap();
        byte[] im = img(mBitmap);

        if (im.length == 0) {
            Log.d(TAG, "onCreate: Image is NULL");
        }
        else{
            Log.d(TAG, "onCreate: Image is NOT NULL " + im.length);
        }

        Account account = new Account();
        account.setUserName(usrn);
        account.setPassword("Vm0weGQxSXh");
        account.setUserImage(im);
        account.save();

        Record record = new Record();
        record.setRecordTitle("A Wonderful Morning");
        record.setRecordText("......");
        record.setRecordDate(date);
        record.save();

        account.getRecordList().add(record);
        account.save();
    }

    private byte[]img(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
}
