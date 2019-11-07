package com.example.social_event;


import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeveloperFragment extends Fragment {

   Dialog dialog;
   ImageView imageView;
    public DeveloperFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate( R.layout.fragment_developer, container, false );
        dialog = new Dialog(getContext());
        imageView = (ImageView)view.findViewById( R.id.imageView2 );
        imageView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Showpopup(view);
            }
        } );

        return view;
    }
    public void Showpopup(View view)
    {
        TextView txtclose;
        dialog.setContentView( R.layout.custompopup1 );
        txtclose = (TextView)dialog.findViewById( R.id.txtclose );
        txtclose.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        } );
        dialog.show();
    }

}
