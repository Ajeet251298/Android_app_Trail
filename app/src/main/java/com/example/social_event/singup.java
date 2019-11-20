package com.example.social_event;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import static java.sql.DriverManager.println;

public class singup extends AppCompatActivity implements View.OnClickListener{


    EditText username, fullname, email, password;
    Button register;
    TextView txt_login;

    TextView textview;

    private ProgressDialog progressDialog;

    //for firebase auth
    private FirebaseAuth firebaseAuth;

    // database reference object
    DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);


        firebaseAuth = FirebaseAuth.getInstance();

        databaseUsers = FirebaseDatabase.getInstance().getReference("users");

        progressDialog = new ProgressDialog(this);

        register=(Button)findViewById(R.id.register);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        username=(EditText)findViewById(R.id.username);
        fullname=(EditText)findViewById(R.id.fullname);
        txt_login=(TextView)findViewById(R.id.txt_login);

        register.setOnClickListener(this);
        txt_login.setOnClickListener(this);
    }

    private void registerUser()
    {
         final String str_email=email.getText().toString().trim();
         final String str_password=password.getText().toString().trim();
         final String str_fullname = fullname.getText().toString().trim();
         final String str_username = username.getText().toString().trim();

        if(TextUtils.isEmpty(str_email)||TextUtils.isEmpty(str_password)||TextUtils.isEmpty(str_username)||TextUtils.isEmpty(str_fullname))
        {
            //email is empty
            Toast.makeText(this,"All Fields are Required!",Toast.LENGTH_SHORT).show();
            return;
        }


        //If validation is ok
        ///first we will show a progress bar

        progressDialog.setMessage("Registering User...");
        progressDialog.show();
        ///adding listener to check whether user has been registerd or not
        firebaseAuth.createUserWithEmailAndPassword(str_email,str_password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            //finish();

                            // kisi naye user ko push krne se phle ek id genetate krega
                            databaseUsers = FirebaseDatabase.getInstance().getReference( "Users" );
                            String userid = databaseUsers.push().getKey();
                            String boi = "";
                            String image = "https://firebasestorage.googleapis.com/v0/b/socialevent2.appspot.com/o/logo.jpg?alt=media&token=61833b28-9f95-46f0-b098-bf3fd3d77edf ";
                            User user = new User( userid, str_username, str_fullname, str_email, str_password, boi, image );
                            databaseUsers.child( userid ).setValue( user );
                            Toast.makeText( singup.this, "Registered successfully!", Toast.LENGTH_SHORT ).show();

                            Intent intent = new Intent( getApplicationContext(), profile.class );
                            intent.putExtra( "email", str_email );
                            startActivity( intent );

                        }
                        else {
                            progressDialog.dismiss();

                            Toast.makeText( singup.this, "Registration failed", Toast.LENGTH_SHORT ).show();

                        }
                    }

                            } );
    }

    @Override
    public void onClick(View view)
    {
        if(view==register)
        {
            println("Hello");
            registerUser();
        }
        if(view==txt_login)
        {
            //will open signin activity;
            // finish();

            Intent intent=new Intent(this,login.class);
            startActivity(intent);
        }
    }

}
