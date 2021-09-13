package com.example.explicitintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity3 extends AppCompatActivity {
    EditText inputLastName;
    Button btnSubmit, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        inputLastName = findViewById(R.id.inputLastName);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnCancel = findViewById(R.id.btnCancel);

        btnSubmit.setOnClickListener(view -> {

            if (inputLastName.getText().toString().isEmpty()){
                Toast.makeText(Activity3.this, "Please enter your last name", Toast.LENGTH_LONG).show();
            }else{
                String lName = inputLastName.getText().toString().trim();
                Intent intent = new Intent();
                intent.putExtra("lastName", lName);

                setResult(RESULT_OK, intent);

                Activity3.this.finish();
            }
        });

        btnCancel.setOnClickListener(view -> {
            setResult(RESULT_CANCELED);
            Activity3.this.finish();
        });


    }
}