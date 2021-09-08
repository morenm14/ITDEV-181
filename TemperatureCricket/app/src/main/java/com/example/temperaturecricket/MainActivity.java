package com.example.temperaturecricket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText inputValue;
    Button button;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        button = findViewById(R.id.button);
        result = findViewById(R.id.result);

        result.setVisibility(View.GONE);

        button.setOnClickListener( view -> {

            if (inputValue.getText().toString().isEmpty()){
                Toast.makeText(MainActivity.this, "Please enter number of chirps.", Toast.LENGTH_LONG).show();
            }else{

                int chirps = Integer.parseInt(inputValue.getText().toString().trim());

                int temperatureF = chirps + 40;
                int temperatureC = (chirps/3) + 4;

                String output = "The approximate temperature outside is: \n" +
                        temperatureF + "F " + "( " + temperatureC + "C )";

                result.setText(output);
                inputValue.setText(null);
                result.setVisibility(View.VISIBLE);
            }

        });
    }

}