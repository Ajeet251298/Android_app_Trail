package com.example.social_event;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Button login,register;
    FirebaseUser firebaseUser;
   /* @Override
    protected void onStart()
    {
        super.onStart();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        //redirect if userr is not null
        if(firebaseUser!=null)
        {
            startActivity(new Intent( getApplicationContext(),profile.class ));
            finish();
        }
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login =(Button)findViewById( R.id.login );
        register=(Button)findViewById( R.id.register );
        login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( getApplicationContext(),login.class ) );
            }
        } );
        register.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( getApplicationContext(),singup.class ) );
            }
        } );
    }
}
