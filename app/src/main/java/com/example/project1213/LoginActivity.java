package com.example.project1213;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText username = (EditText) findViewById(R.id.user_name);
        final EditText password = (EditText) findViewById(R.id.password);

        Button button_reg = (Button) findViewById(R.id.register_button);
        button_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);

            String inputUserName = username.getText().toString();
            String inputPassword = password.getText().toString();

            intent.putExtra("userNameInLog", inputUserName);
            intent.putExtra("passwordInLog", inputPassword);
            startActivity(intent);
            }
        });

        Button button_log = (Button) findViewById(R.id.login_button);
        button_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputUserName = username.getText().toString();
                String inputPassword = password.getText().toString();
                Toast.makeText(LoginActivity.this, inputUserName,
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
