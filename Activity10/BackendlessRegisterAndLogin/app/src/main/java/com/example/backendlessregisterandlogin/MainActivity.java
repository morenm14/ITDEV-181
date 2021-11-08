package com.example.backendlessregisterandlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.backendless.Backendless;

public class MainActivity extends AppCompatActivity {

    TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcome = findViewById(R.id.result);
        String result = getIntent().getStringExtra("user");
        welcome.setText("Welcome " + result + "!!!");
    }
}
