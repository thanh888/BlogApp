package com.poinle.blogapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences userPre = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
                boolean isLoggedIn = userPre.getBoolean("isLoggedIn", false);

                if (isLoggedIn){
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    finish();
                }
                else {
                    isFirstTime();
                }
            }
        },1000);
    }
    private void isFirstTime(){
        SharedPreferences preferences = getApplication().getSharedPreferences("On Board", Context.MODE_PRIVATE);
        boolean isFirst = preferences.getBoolean("isFirst", true);

        if(isFirst){
            SharedPreferences.Editor editor =  preferences.edit();
            editor.putBoolean("isFirst", false);
            editor.apply();

            startActivity(new Intent(MainActivity.this, OnBoardActivity.class));
            finish();
        }
        else{
            startActivity(new Intent(MainActivity.this, AuthActivity.class));
            finish();
        }
    }
}