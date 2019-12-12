package com.example.project1213;

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
import org.litepal.crud.LitePalSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

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

        // Create database: Account.db
        Connector.getDatabase();

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

                if(userNameInReg.isEmpty() || passwordInReg.isEmpty() || emailInReg.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this,
                            "Please input ALL info.", Toast.LENGTH_LONG).show();
                    return;
                }

                List<Account> accountList = LitePal.select("userName")
                        .where("userName == ?", userNameInReg)
                        .find(Account.class);

                if(!accountList.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this,
                            "UserName has been registered.",Toast.LENGTH_LONG).show();
                    return;
                }

                Account account = new Account();
                account.setUserName(userNameInReg);
                account.setPassword(passwordInReg);
                account.setEmailAddress(emailInReg);
                account.save();


                Intent intent_return = new Intent();
                intent_return.putExtra("userNameFromReg", userNameInReg);
                intent_return.putExtra("passwordFromReg", passwordInReg);
                setResult(RESULT_OK, intent_return);
                finish();

                /*
                List<Account> accounts = LitePal.findAll(Account.class);
                for(Account temp:accounts)
                {
                    Log.d(TAG, "onClick: Usr: " + temp.getUserName());
                    Log.d(TAG, "onClick: Psd: " + temp.getPassword());
                    Log.d(TAG, "onClick: EA : " + temp.getEmailAddress());
                }
                */
            }
        });
    }
}
