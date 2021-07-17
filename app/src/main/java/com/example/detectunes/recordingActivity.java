package com.example.detectunes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class recordingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);

        getSupportActionBar().hide();

        Thread thread = new Thread(){
            public void run(){
                try{
                    sleep( 8000);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    Intent intent = new Intent(  recordingActivity.this, SongInfo.class);
                    startActivity(intent);
                }
            }
        };thread.start();
    }

}