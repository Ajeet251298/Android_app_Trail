package com.example.social_event;

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

public class login extends AppCompatActivity implements View.OnClickListener {

    private Button b1;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textview;

    private ProgressDialog progressDialog;

    //for firebase auth
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

       /* if(firebaseAuth.getCurrentUser()!=null)
        {
            //start profile activity
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }*/

        progressDialog = new ProgressDialog(this);

        b1=(Button)findViewById(R.id.button);
        editTextEmail=(EditText)findViewById(R.id.editTextEmail);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);
        textview=(TextView)findViewById(R.id.textview);

        b1.setOnClickListener(this);
        textview.setOnClickListener(this);

    }

    private void userLogin()
    {
        final String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            //email is empty
            Toast.makeText(this,"Enter your Email",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password))
        {
            //password is empty;
            Toast.makeText(this,"Enter your pasword",Toast.LENGTH_SHORT).show();
            return ;
        }
        //If validation is ok
        ///first we will show a progress bar

        progressDialog.setMessage("Login Please wait...");
        progressDialog.show();
        ///adding listener to check whether user has been registerd or not
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressDialog.dismiss();
                        if(task.isSuccessful())
                        {
                            finish();
                            Intent intent=new Intent(getApplicationContext(), profile.class);
                            intent.putExtra("email",email);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(login.this, "Login failed", Toast.LENGTH_SHORT).show();
                            int q=1;
                        }
                    }
                });




    }

    @Override
    public void onClick(View view) {
        if(view==b1)
        {
            userLogin();
        }
        if(view==textview)
        {
            finish();
            Intent intent = new Intent(this,singup.class);
            startActivity(intent);

        }
    }
}

