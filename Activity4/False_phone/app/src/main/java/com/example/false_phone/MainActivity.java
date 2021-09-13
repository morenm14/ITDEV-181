package com.example.false_phone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView fakeMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fakeMessage = findViewById(R.id.fakeMessage);

        if (getIntent().getData() != null){
            fakeMessage.setText(getIntent().getData().toString().trim());
        }else {
            fakeMessage.setText("No Data Received");
        }

    }
}