package com.example.project1213;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class FindFragment extends Fragment {

    private String context;
    private ListView lv;

    //private ShareAdapter adapter = null;
    //private List<Account> accountList;
    //private ListView findListView;

    public FindFragment(String context){
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // ShareListView

        List<Account> accountList = LitePal.findAll(Account.class,true);
        List<Account> share_ac_list = new ArrayList<>();
        for(int i = 0;i < 5; i++){
            int index = (int)(Math.random() * accountList.size());
            share_ac_list.add(accountList.get(index));
            accountList.remove(index);
        }
        Log.d("AAAAA", "onCreate: " + share_ac_list.size());

        View v = inflater.inflate(R.layout.find_fragment,null);

        ShareAdapter shareAdapter = new ShareAdapter(getActivity(), R.layout.find_listview_item, share_ac_list);
        ListView shareListView = (ListView) v.findViewById(R.id.find_listview);
        shareListView.setAdapter(shareAdapter);
        return v;

        //View v = inflater.inflate(R.layout.speakfragment,null);
        //classListView = (ListView) v.findViewById(R.id.lv_speak);
        //adapter = new ClassListAdapter(getActivity(), R.layout.speak_item, classList);
        //classListView.setAdapter(adapter);
        //return v;

        //View view = inflater.inflate(R.layout.find_fragment,container,false);
        //return view;
    }


}
