package com.example.sharedpreferencesdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView welcomeMsg;
    EditText inputName;
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeMsg = findViewById(R.id.welcomeMsg);
        inputName = findViewById(R.id.inputName);
        submitBtn = findViewById(R.id.submitBtn);

        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("user", "");
        welcomeMsg.setText("Welcome to my app " + user + "!");

        submitBtn.setOnClickListener(view -> {
            if (!inputName.getText().toString().trim().isEmpty()){
                String name = inputName.getText().toString().trim();
                welcomeMsg.setText("Welcome to my app " + name + "!");

                SharedPreferences sharedPrefs = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString("user", name);
                editor.apply();
                inputName.setText(null);
            }else {
                Toast.makeText(this, "Enter a name", Toast.LENGTH_SHORT).show();
            }
        });
    }
}