package com.example.social_event;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class homeFragment extends Fragment implements AdapterView.OnItemClickListener {

    ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

         View view =inflater.inflate(R.layout.fragment_home,null);

        String s[]=new String[]{"CHILD LABOUR","GIRLS EDUCATION","CHILD MARRIAGE"};
        listView =(ListView)view.findViewById(R.id.listview);
        ArrayAdapter arrayAdapter = new ArrayAdapter( getActivity(),android.R.layout.simple_expandable_list_item_1,s );
        listView.setAdapter( arrayAdapter );
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(),"HOME M HO",Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
