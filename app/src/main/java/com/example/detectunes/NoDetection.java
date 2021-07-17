package com.example.detectunes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class NoDetection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_detection);
        getSupportActionBar().hide();
    }
}