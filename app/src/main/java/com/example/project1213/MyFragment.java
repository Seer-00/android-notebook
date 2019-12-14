package com.example.project1213;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class MyFragment extends Fragment {
    private String context;
    private TextView mTextView;

    public MyFragment(String context){
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_fragment,container,false);
        mTextView = (TextView)view.findViewById(R.id.textView);
        mTextView.setText(context);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button log_in=(Button)getActivity().findViewById(R.id.login_button);
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    public void checkLoginButton(String name){
        Button log_in=(Button)getActivity().findViewById(R.id.login_button);
        if(name.isEmpty())
            log_in.setVisibility(View.VISIBLE);
        else
            log_in.setVisibility(View.INVISIBLE);
    }







}
