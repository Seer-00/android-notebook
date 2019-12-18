package com.example.project1213;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;


public class MyFragment extends Fragment {
    private String context;
    private TextView mTextView;
    private List<Record> recordList=new ArrayList<>();

    public MyFragment(String context){
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_fragment,container,false);
        //mTextView = (TextView)view.findViewById(R.id.my_textView);
        //mTextView.setText(context);
        RecordAdapter adapter=new RecordAdapter(getActivity(),R.layout.my_listview_item,recordList);
        ListView listView=(ListView)view.findViewById(R.id.my_listview);
        listView.setAdapter(adapter);

        return view;
    }
    public void refreshRecords()
    {

    }



//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        /*Button log_in=(Button)getActivity().findViewById(R.id.log_in);
//        log_in.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), LoginActivity.class);
//                startActivityForResult(intent, 1);
//            }
//        });*/
//    }



//    class RecordAdapter extends ArrayAdapter<Record>{
//        private int resourceId;
//
//        public RecordAdapter(@NonNull Context context, int resource, @NonNull List<Record> objects) {
//            super(context, resource, objects);
//            resourceId = resource;
//        }
//
//        @NonNull
//        @Override
//        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//            Record record=getItem(position);
//            View view=LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
//            TextView title=(TextView)view.findViewById(R.id.my_listview_title);
//            TextView summary=(TextView)view.findViewById(R.id.my_listview_summary);
//            TextView date=(TextView)view.findViewById(R.id.my_listview_date);
//            title.setText(record.getRecordTitle());
//            summary.setText(record.getRecordText());
//            date.setText(record.getRecordDate());
//            return view;
//
//        }
//    }

}
