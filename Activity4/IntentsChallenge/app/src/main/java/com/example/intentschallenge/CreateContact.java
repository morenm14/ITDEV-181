package com.example.intentschallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class CreateContact extends AppCompatActivity implements View.OnClickListener {
    EditText phoneNumber, web, location, name;
    ImageView happyFace, normalFace, sadFace;
    LinearLayout facesLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        name =findViewById(R.id.name);
        phoneNumber = findViewById(R.id.phoneNumber);
        web = findViewById(R.id.web);
        location = findViewById(R.id.location);

        happyFace = findViewById(R.id.happyFace);
        normalFace = findViewById(R.id.normalFace);
        sadFace = findViewById(R.id.sadFace);

        facesLayout = findViewById(R.id.facesLayout);
        happyFace.setOnClickListener(this);
        normalFace.setOnClickListener(this);
        sadFace.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (name.getText().toString().isEmpty() || phoneNumber.getText().toString().isEmpty() ||
                web.getText().toString().isEmpty() || location.getText().toString().isEmpty()){

            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
        }else{
            Intent contact = new Intent();
            contact.putExtra("name", name.getText().toString().trim());
            contact.putExtra("phone", phoneNumber.getText().toString().trim());
            contact.putExtra("web", web.getText().toString().trim());
            contact.putExtra("location", location.getText().toString().trim());

            if (view.getId() == R.id.happyFace){

                contact.putExtra("face", "happyFace");

            }else if (view.getId() == R.id.normalFace){
                contact.putExtra("face", "normalFace");

            }else {
                contact.putExtra("face", "sadFace");
            }
            setResult(RESULT_OK, contact);

            CreateContact.this.finish();
        }
    }
}