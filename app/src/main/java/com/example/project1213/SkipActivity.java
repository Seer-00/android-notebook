package com.example.project1213;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SkipActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skip);

        Button button_to_mine = (Button) findViewById(R.id.to_Main2Activity);
        button_to_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SkipActivity.this, Main2Activity.class);
                startActivity(intent1);
            }
        });

        Button button_to_found = (Button) findViewById(R.id.to_foundActivity);
        button_to_found.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(SkipActivity.this, FoundActivity.class);
                startActivity(intent2);
            }
        });
    }
}
