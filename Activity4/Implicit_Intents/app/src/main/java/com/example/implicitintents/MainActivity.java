package com.example.implicitintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnCall, btnCallFriend, btnWeb, btnMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCall = findViewById(R.id.btnCall);
        btnCallFriend = findViewById(R.id.btnCallFriend);
        btnWeb = findViewById(R.id.btnWeb);
        btnMap = findViewById(R.id.btnMap);

        btnCall.setOnClickListener(view -> {

            Intent call = new Intent(Intent.ACTION_DIAL);
            startActivity(call);

        });

        btnCallFriend.setOnClickListener(view -> {

            Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:4145555555"));
            startActivity(call);

        });

        btnWeb.setOnClickListener(view -> {
            Intent web = new Intent(Intent.ACTION_VIEW, Uri.parse("http://mario-moreno.com"));
            startActivity(web);
        });

        btnMap.setOnClickListener(view -> {
            Intent map = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q= Milwaukee, Wisconsin"));
            startActivity(map);

        });

    }
}