package com.example.project1213;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.List;

/*
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
//获取当前时间
Date date = new Date(System.currentTimeMillis());
time1.setText("Date获取当前日期时间"+simpleDateFormat.format(date));
 */


public class AddRecords extends AppCompatActivity {

    private static final String TAG = "AddRecords";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_records);

        Intent intent = getIntent();
        final String user_login = intent.getStringExtra("user_login");

        final EditText record_text = (EditText)findViewById(R.id.Rec_text);
        final EditText record_title = (EditText)findViewById(R.id.Rec_title);
        //Button record_pic = (Button)findViewById(R.id.Rec_pic);
        Button record_create = (Button)findViewById(R.id.Rec_create);
        final CheckBox use_title = (CheckBox)findViewById(R.id.use_title);

        use_title.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(use_title.isChecked())
                {
                    record_title.setVisibility(View.VISIBLE);
                }
                else
                {
                    record_title.setVisibility(View.INVISIBLE);
                }
            }
        });
/*
        record_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/

        record_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = record_text.getText().toString();
                Record record = new Record();
                record.setRecordText(text);
                boolean record_save = record.save();

                List<Account> accountList = LitePal
                        .where("userName == ?", user_login)
                        .find(Account.class);

                if(accountList.size() != 1)
                {
                    Toast.makeText(AddRecords.this, "Account database error",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                Account account = accountList.get(0);
                account.getRecordList().add(record);
                boolean account_upgrade = account.save();

                if(record_save && account_upgrade)
                {
                    Toast.makeText(AddRecords.this, "Add record successfully.",
                            Toast.LENGTH_SHORT).show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 2000);
                }
                else
                {
                    Toast.makeText(AddRecords.this,
                            "Add record FAILED for unknown reason.", Toast.LENGTH_SHORT).show();
                }
            }
        });
      
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Traveller");
        toolbar.inflateMenu(R.menu.operation);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.add:
                        if(user_login.isEmpty())
                        {
                            Toast.makeText(AddRecords.this, "You should login first",
                                    Toast.LENGTH_LONG).show();
                        }
                        else;
                        {
                            Intent intent = new Intent(Main2Activity.this,AddRecords.class);
                            intent.putExtra("user_login", user_login);
                            startActivity(intent);
                        }
                        break;
                    case R.id.about:
                        startActivity(new Intent(AddRecords.this,About.class));
                }
                return true;
            }
        });


    }


    }
}

