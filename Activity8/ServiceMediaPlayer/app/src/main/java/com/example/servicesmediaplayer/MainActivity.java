package com.example.servicesmediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String audioLink = "https://dl.freesound.org/data/previews/593/593786_2282212-lq.mp3";
    static ImageView imageControl;
    boolean isPlaying = false;
    Intent serviceIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageControl = findViewById(R.id.imageControl);
        serviceIntent = new Intent(this, MyPlayService.class);

        imageControl.setOnClickListener(view -> {

            if (!isPlaying){
                playMusic();
                imageControl.setImageResource(R.drawable.play_img);
                isPlaying = true;
            }else {
                stopMusic();
                imageControl.setImageResource(R.drawable.stop_img);
                isPlaying =false;
            }

        });

    }

    private void stopMusic() {
        try {
            stopService(serviceIntent);
        }catch (SecurityException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void playMusic() {
        serviceIntent.setAction("com.example.servicesmediaplayer");
        serviceIntent.putExtra("AudioLink", audioLink);
        try {
            startService(serviceIntent);
        }catch (SecurityException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}