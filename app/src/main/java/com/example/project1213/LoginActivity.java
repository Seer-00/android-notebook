package com.example.project1213;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case 1:
                if(resultCode == RESULT_OK)
                {
                    EditText usrn = (EditText) findViewById(R.id.user_name);
                    EditText pswd = (EditText) findViewById(R.id.password);
                    usrn.setText(data.getStringExtra("userNameFromReg"));
                    pswd.setText(data.getStringExtra("passwordFromReg"));

                    Toast.makeText(LoginActivity.this,
                            "Registered successfully.", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

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
                startActivityForResult(intent, 1);
            }
        });

        Button button_log = (Button) findViewById(R.id.login_button);
        button_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputUserName = username.getText().toString();
                String inputPassword = password.getText().toString();

                if(inputUserName.isEmpty() || inputPassword.isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Please input username and password.",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                List<Account> accountList = LitePal.where("userName == ?", inputUserName)
                        .find(Account.class);

                if(accountList.isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "User: " + inputUserName + " doesn't exist.",
                            Toast.LENGTH_LONG).show();
                }
                else
                {
                    if(accountList.size() != 1)
                    {
                        Toast.makeText(LoginActivity.this, "Account database ERROR.",
                                Toast.LENGTH_LONG).show();
                        return;
                    }

                    Account account = accountList.get(0);

                    /* 输出所有的 Account信息
                    List<Account> accounts = LitePal.findAll(Account.class);
                    for(Account temp:accounts)
                    {
                        Log.d(TAG, "onClick: Usr: " + temp.getUserName());
                        Log.d(TAG, "onClick: Psd: " + temp.getPassword());
                        Log.d(TAG, "onClick: EA : " + temp.getEmailAddress());
                    }
                    */

                    if (inputPassword.equals(account.getPassword()))
                    {
                        Intent intent = new Intent();
                        intent.putExtra("return_username", account.getUserName());
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Incorrect username or password.",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
}
