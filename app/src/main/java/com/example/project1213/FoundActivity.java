package com.example.project1213;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class FoundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found);

        // ShareListView

        List<Account> accountList = LitePal.where("id < ?", "13").find(Account.class,true);
        List<Account> share_ac_list = new ArrayList<>();
        for(int i = 0;i < 5; i++){
            int index = (int)(Math.random() * accountList.size());
            share_ac_list.add(accountList.get(index));
            accountList.remove(index);
        }

        double rand = Math.random();
        if(rand > 0.6d){
            share_ac_list.add(accountList.get(0));
        }
        else if (rand < 0.2d){
            share_ac_list.add(accountList.get(0));
            share_ac_list.add(accountList.get(1));
        }
        else{
            share_ac_list.remove(0);
        }

        ShareAdapter shareAdapter = new ShareAdapter(FoundActivity.this, R.layout.find_listview_item, share_ac_list);
        ListView listView = (ListView) findViewById(R.id.found_list_view);
        listView.setAdapter(shareAdapter);
    }
}
