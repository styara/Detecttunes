package com.example.detectunes;

import android.Manifest;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MainActivity2 extends AppCompatActivity {
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;
    private static final int MICROPHONE_PERMISSION_CODE = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Objects.requireNonNull(getSupportActionBar()).hide();

        if (isMicrophonePresent()) {
            getMicrophonePermission();
        }
    }
    public void btnRecordPressed(View v){
        //TODO HANDLER FUNCTION
        Handler mhandler = new Handler(Looper.getMainLooper());
        Runnable timerRunnable = new Runnable() {
            @Override
            public void run() {
                stopRecording();
                Toast.makeText(getApplicationContext(), "Recording completed", Toast.LENGTH_LONG).show();
                Thread thread = new Thread(){
                    public void run(){
                        try{
                            sleep( 2000);
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                        finally {
                            Intent intent = new Intent(MainActivity2.this,SongInfo.class);
                            startActivity(intent);
                        }
                    }
                };thread.start();



            }

            private void stopRecording() {
                mediaRecorder.stop();
                mediaRecorder.release();
                mediaRecorder = null;
            }
        };

        try {
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setOutputFile(getRecordingFilePath());
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.prepare();

            Toast.makeText(this, "Recording audio", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaRecorder.start();
//        TODO HANDLER CALL
        mhandler.postDelayed(timerRunnable, 20000);

    }
    public void btnPlayPressed(View v){

        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(getRecordingFilePath());
            mediaPlayer.prepare();
            mediaPlayer.start();
            Toast.makeText(this, "Recording is playing", Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }



    private boolean isMicrophonePresent(){
        return this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE);
    }
    private void getMicrophonePermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.RECORD_AUDIO},MICROPHONE_PERMISSION_CODE );
        }
    }

    private String getRecordingFilePath(){
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File musicDirectory = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File file = new File(musicDirectory, "testRecordingFile" + ".mp3");
        return file.getPath();
    }
}
















