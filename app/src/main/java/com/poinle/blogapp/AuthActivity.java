package com.poinle.blogapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.poinle.blogapp.Fragment.SignInFragment;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_authContainer, new SignInFragment()).commit();
    }
}