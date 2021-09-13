package com.example.vectorassets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView mainImage, btnBeach, btnCamera, btnCake;
    Button btnChangeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainImage = findViewById(R.id.mainImage);
        btnChangeImage = findViewById(R.id.btnChangeImage);
        btnBeach = findViewById(R.id.btnBeach);
        btnCamera = findViewById(R.id.btnCamera);
        btnCake = findViewById(R.id.btnCake);

        btnChangeImage.setOnClickListener(view -> mainImage.setImageResource(R.drawable.ic_baseline_call_24));

        btnBeach.setOnClickListener(view -> mainImage.setImageResource(R.drawable.beach));
        btnCamera.setOnClickListener(view -> mainImage.setImageResource(R.drawable.camera));
        btnCake.setOnClickListener(view ->  mainImage.setImageResource(R.drawable.cake));
    }
}