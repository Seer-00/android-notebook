package com.example.project1213;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FindFragment extends Fragment {

    private String context;
    private ListView lv;

    private String[] data={"1","2","3","4","5","6","7","8","9","0","10"};
    //private TextView mTextView;

    public FindFragment(String context){
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find_fragment,container,false);
        //mTextView = (TextView)view.findViewById(R.id.find_textView);
        //mTextView.setText(context);
        //lv=(ListView)view.findViewById(R.id.find_listview);
        //ArrayAdapter<String> adapter=new ArrayAdapter<String>(FindFragment.this,android.R.layout.simple_list_item_1,data);
        //lv.setAdapter(adapter);
        return view;
    }


}
