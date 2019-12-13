package com.example.project1213;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    private RadioGroup radioGroup;
    private RadioButton find,mine;
    private MyFragment findFragment,mineFragment;

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


        radioGroup=(RadioGroup)findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(this);

        find=(RadioButton)findViewById(R.id.find);
        mine=(RadioButton)findViewById(R.id.mine);

        find.setChecked(true);
    }

    public void hideAllFragment(FragmentTransaction transaction){
        if(findFragment!=null){
            transaction.hide(findFragment);
        }
        if(mineFragment!=null){
            transaction.hide(mineFragment);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group,int checkedId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        switch (checkedId){
            case R.id.find:
                if(findFragment==null){
                    findFragment=new MyFragment("发现");
                    transaction.add(R.id.fl,findFragment);
                }else{
                    transaction.show(findFragment);
                }
                break;
            case R.id.mine:
                    if(mineFragment==null){
                       mineFragment=new MyFragment("我的");
                       transaction.add(R.id.fl,mineFragment);
                    }else{
                        transaction.show(mineFragment);
                    }
                break;
        }
        transaction.commit();

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

