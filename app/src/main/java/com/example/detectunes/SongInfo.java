package com.example.detectunes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SongInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_info);
        getSupportActionBar().hide();
    }
}