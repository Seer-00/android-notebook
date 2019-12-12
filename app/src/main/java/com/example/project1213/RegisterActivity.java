package com.example.project1213;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Intent intent = getIntent();
        final String userNameInLogin = intent.getStringExtra("userNameInLog");
        final String passwordInLogin = intent.getStringExtra("passwordInLog");

        final EditText usrN = (EditText)findViewById(R.id.Reg_user_name);
        final EditText pasW = (EditText)findViewById(R.id.Reg_password);
        final EditText email = (EditText)findViewById(R.id.Reg_email);

        usrN.setText(userNameInLogin);
        pasW.setText(passwordInLogin);
        String inputEmail = email.getText().toString();

        Button clrall = (Button) findViewById(R.id.Reg_clear_all);
        clrall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usrN.setText("");
                pasW.setText("");
                email.setText("");
            }
        });

        Button sub = (Button) findViewById(R.id.Reg_submit);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userNameInReg = usrN.getText().toString();
                String passwordInReg = pasW.getText().toString();
                String emailInReg = email.getText().toString();

            }
        });
    }
}
