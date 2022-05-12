package com.example.biodump;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Citizenlogin extends AppCompatActivity {

    EditText mEmail,mPassword;
    Button mLoginBtn,mLoginWPhone;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizenlogin);

        mEmail=findViewById(R.id.Lemail);
        mPassword=findViewById(R.id.Lpassword);
        fAuth=FirebaseAuth.getInstance();
        mLoginBtn=findViewById(R.id.Loginbtn);
        mLoginWPhone=findViewById(R.id.Phonebtn);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is Required");
                    return;
                }
                if(password.length()<6){
                    mPassword.setError("Password Must be >=6 Characters");
                    return;
                }
                // Authenticate the user

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Citizenlogin.this,"Logged In",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Homepg.class));
                        }
                        else {
                            Toast.makeText(Citizenlogin.this,"Error Occurred"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}