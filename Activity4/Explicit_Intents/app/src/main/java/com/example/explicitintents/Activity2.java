package com.example.explicitintents;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {
    TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        welcome = findViewById(R.id.welcome);

        String name = getIntent().getStringExtra("name");
        welcome.setText(name + ", Welcome to activity 2!");
    }
}