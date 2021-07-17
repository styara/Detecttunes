package com.example.detectunes;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    //variables
    Animation topanim, bottomanim;
    ImageView imageView;
    ImageView imageV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashactivity);

        getSupportActionBar().hide();
        //animation
        topanim= AnimationUtils.loadAnimation(this,R.anim.top_anim);
        bottomanim= AnimationUtils.loadAnimation(this,R.anim.btm_anim);
        //hooks
        imageView = findViewById(R.id.imageView);
        imageV = findViewById(R.id.imageV);

        imageView.setAnimation(topanim);
        imageV.setAnimation(bottomanim);


        Thread thread = new Thread(){
            public void run(){
                try{
                    sleep( 4000);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    Intent intent = new Intent(  SplashActivity.this, MainActivity2.class);
                    startActivity(intent);
                    finish();
                }
            }
        };thread.start();
    }
}