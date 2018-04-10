package com.ourblog.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Chloe on 4/10/2018.
 */

public class HomeActivity extends AppCompatActivity {
    private Button login;
    private Button signUp;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        login = (Button) findViewById(R.id.homeLoginBtn);
        signUp = (Button) findViewById(R.id.home_sign_up_btn);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(HomeActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });


        if(user == null){

//            login.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent();
//                    intent.setClass(HomeActivity.this, LogInActivity.class);
//                    startActivity(intent);
//
//                }
//            });
//            signUp.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent();
//                    intent.setClass(HomeActivity.this, SignUpActivity.class);
//                    startActivity(intent);
//                }
//            });
        } else{
//            Intent intent = new Intent();
//            intent.setClass(SignUpActivity.this, MainMainActivity.class);
//            startActivity(intent);
        }



    }

}
