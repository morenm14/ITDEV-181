package com.example.magicidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editTextID;
    Button btnSubmit;
    TextView textViewID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextID = findViewById(R.id.editTextID);
        btnSubmit = findViewById(R.id.btnSubmit);
        textViewID = findViewById(R.id.textViewID);

        textViewID.setVisibility(View.GONE);

        btnSubmit.setOnClickListener(view -> {

            String idNumber = editTextID.getText().toString().trim();
            String dob = idNumber.substring(0,6);
            int gender = Integer.parseInt(Character.toString(idNumber.charAt(6)));
            int nationality = Integer.parseInt(Character.toString(idNumber.charAt(10)));

            String sGender;
            String sNationality;

            if (gender < 5) {

                sGender = getString(R.string.female);
            }else{
                sGender = getString(R.string.male);
            }

            if (nationality == 0){
                sNationality = getString(R.string.saCitizen);
            }else{
                sNationality = getString(R.string.permanent);
            }

            String text = getString(R.string.dob) + dob + getString(R.string.newLine) +
                    getString(R.string.gender) + sGender + getString(R.string.newLine) +
                    getString(R.string.nationality) + sNationality + getString(R.string.newLine);

            textViewID.setText(text);
            textViewID.setVisibility(View.VISIBLE);

        });
    }
}