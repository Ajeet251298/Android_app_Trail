package com.example.social_event;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DeveloperActivity extends AppCompatActivity {

    Dialog dialog;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_developer );

        dialog = new Dialog(this);
        imageView = (ImageView)findViewById( R.id.imageView2 );
        imageView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Showpopup(view);
            }
        } );
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
