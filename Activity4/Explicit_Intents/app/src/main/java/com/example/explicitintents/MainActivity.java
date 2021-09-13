package com.example.explicitintents;



import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText inputName;
    Button btnActivity2, btnActivity3;
    TextView results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputName = findViewById(R.id.inputName);
        btnActivity2 = findViewById(R.id.btnActivity2);
        btnActivity3 = findViewById(R.id.btnActivity3);
        results = findViewById(R.id.results);

        btnActivity2.setOnClickListener(view -> {

            if (inputName.getText().toString().isEmpty()){
                Toast.makeText(MainActivity.this, "Please enter your name", Toast.LENGTH_LONG).show();
            }else{

                String name = inputName.getText().toString().trim();
                Intent intent = new Intent(MainActivity.this, com.example.explicitintents.Activity2.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });

        ActivityResultLauncher<Intent> getInput = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {

                        if (result.getResultCode() == RESULT_OK){
                            results.setText(result.getData().getStringExtra("lastName").trim());
                        }else
                        {
                            results.setText("No data received");
                        }
                    });


        btnActivity3.setOnClickListener(view -> {

            Intent intent = new Intent(this, com.example.explicitintents.Activity3.class);
            getInput.launch(intent);

            //THIS HAS BEEN DEPRECATED!!!!
            //Intent intent = new Intent(MainActivity.this, com.example.explicitintents.Activity3.class);
            // startActivityForResult(intent,ACTIVITY3);

        });
    }

            //THIS HAS BEEN DEPRECATED!!!!

        //    @Override
        //    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //        super.onActivityResult(requestCode, resultCode, data);
        //
        //        if (requestCode == ACTIVITY3){
        //            if (requestCode == RESULT_OK){
        //
        //                results.setText(data.getStringExtra("lastName"));
        //
        //            }
        //            if (requestCode == RESULT_CANCELED){
        //
        //                results.setText("No data received");
        //            }
        //        }
        //    }
}