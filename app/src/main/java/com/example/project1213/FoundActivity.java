package com.example.project1213;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class FoundActivity extends AppCompatActivity {

    private  List<Account> accounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found);

        // ToolBar && Menu
        Toolbar toolbar = (Toolbar) findViewById(R.id.find_toolbar);
        toolbar.setTitle("Traveller");
        toolbar.inflateMenu(R.menu.find_operation);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.f_tuniu:
                        Intent intent1 = new Intent(FoundActivity.this,WebView.class);
                        intent1.putExtra("website_address", "https://www.tuniu.com");
                        intent1.putExtra("website_name","途牛旅游网");
                        startActivity(intent1);
                        break;
                    case R.id.f_xiecheng:
                        Intent intent2 = new Intent(FoundActivity.this,WebView.class);
                        intent2.putExtra("website_address", "https://hotels.ctrip.com");
                        intent2.putExtra("website_name","携程");
                        startActivity(intent2);
                        break;
                    case R.id.f_qunaer:
                        Intent intent3 = new Intent(FoundActivity.this,WebView.class);
                        intent3.putExtra("website_address", "https://www.qunar.com");
                        intent3.putExtra("website_name","去哪儿网");
                        startActivity(intent3);
                        break;
                    case R.id.f_mafengwo:
                        Intent intent5 = new Intent(FoundActivity.this,WebView.class);
                        intent5.putExtra("website_address", "https://www.mafengwo.cn");
                        intent5.putExtra("website_name","马蜂窝");
                        startActivity(intent5);
                        break;

                    case R.id.f_about:
                        startActivity(new Intent(FoundActivity.this, About.class));
                        break;
                }
                return true;
            }
        });

        // ShareListView

        List<Account> accountList = LitePal.where("id < ?", "13").find(Account.class,true);
        accounts = new ArrayList<>();
        for(int i = 0;i < 5; i++){
            int index = (int)(Math.random() * accountList.size());
            accounts.add(accountList.get(index));
            accountList.remove(index);
        }

        double rand = Math.random();
        if(rand > 0.6d){
            accounts.add(accountList.get(0));
        }
        else if (rand < 0.2d){
            accounts.add(accountList.get(0));
            accounts.add(accountList.get(1));
        }
        else{
            accounts.remove(0);
        }

        ShareAdapter shareAdapter = new ShareAdapter(FoundActivity.this, R.layout.find_listview_item, accounts);
        ListView listView = (ListView) findViewById(R.id.found_list_view);
        listView.setAdapter(shareAdapter);

        // Click item of ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Account account = accounts.get(position);

                Intent intent = new Intent(FoundActivity.this, FindShowRecordActivity.class);
                intent.putExtra("usr", account.getUserName());
                startActivity(intent);
            }
        });
    }
}
