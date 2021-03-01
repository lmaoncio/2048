package com.example.juego.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.juego.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        };
        Timer time = new Timer();
        time.schedule(task, 3000);
    }
}