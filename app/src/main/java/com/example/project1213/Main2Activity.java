package com.example.project1213;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    public String user_login = "";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode)
        {
            case 1:
                if(resultCode == RESULT_OK)
                {
                    user_login = data.getStringExtra("return_username");
                    Toast.makeText(Main2Activity.this,"Login successfully.",
                            Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
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
                            Toast.makeText(Main2Activity.this, "You should login first",
                                    Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Intent intent = new Intent(Main2Activity.this,AddRecords.class);
                            intent.putExtra("user_login", user_login);
                            startActivity(intent);
                        }
                        break;
                    case R.id.about:
                        startActivity(new Intent(Main2Activity.this,About.class));
                }
                return true;
            }
        });

        Button button_log = (Button) findViewById(R.id.to_loginActivity);
        button_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this, LoginActivity.class);
                startActivityForResult(intent, 1);
            }
        });

    }
}

