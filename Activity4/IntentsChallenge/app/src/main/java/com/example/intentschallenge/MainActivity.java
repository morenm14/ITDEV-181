package com.example.intentschallenge;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView titleView;
    Button btnCreateContact;
    LinearLayout contactLayout;
    ImageView contactFace, contactPhone, contactWeb, contactLocation;

    String name = "", phone = "", web = "", face = "", map = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleView = findViewById(R.id.titleView);
        contactFace = findViewById(R.id.contactFace);
        contactPhone = findViewById(R.id.contactPhone);
        contactWeb = findViewById(R.id.contactWeb);
        contactLocation = findViewById(R.id.contactLocation);
        contactLayout = findViewById(R.id.contactLayout);
        btnCreateContact = findViewById(R.id.btnCreateContact);

        contactLayout.setVisibility(View.GONE);

        ActivityResultLauncher<Intent> getInfo = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK){
                contactLayout.setVisibility(View.VISIBLE);

                face = result.getData().getStringExtra("face");
                name = result.getData().getStringExtra("name");
                phone = result.getData().getStringExtra("phone");
                web = result.getData().getStringExtra("web");
                map = result.getData().getStringExtra("location");
                titleView.setText(name);

                if (face.equals("happyFace")){
                contactFace.setImageResource(R.drawable.happy_face);
                }else if (face.equals("normalFace")){
                    contactFace.setImageResource(R.drawable.normal_face);
                }else{
                    contactFace.setImageResource(R.drawable.sad_face);
                }

            }else{
                Toast.makeText(this, "No Data Received", Toast.LENGTH_SHORT).show();
            }
        });

        //Create contact button
        btnCreateContact.setOnClickListener(view -> {

            Intent intent = new Intent(MainActivity.this, com.example.intentschallenge.CreateContact.class);
            getInfo.launch(intent);

        });
        contactPhone.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
            startActivity(intent);
        });

        contactWeb.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://" + web));
            startActivity(intent);

        });

        contactLocation.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + map));
            startActivity(intent);

        });
    }
}