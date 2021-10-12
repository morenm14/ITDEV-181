package com.example.customtoast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button showToastBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showToastBtn = findViewById(R.id.showToastBtn);

        showToastBtn.setOnClickListener(view -> {

            Toast.makeText(this, "This is a toast", Toast.LENGTH_SHORT).show();
            showMyToast("This is my awesome toast message");
        });
    }

    public void showMyToast(String message){

        View view = getLayoutInflater().inflate(R.layout.my_custom_toast, (ViewGroup) findViewById(R.id.myToastLayout));

        TextView toastMessage = view.findViewById(R.id.myToastMessage);
        toastMessage.setText(message);

        Toast myToast = new Toast(MainActivity.this);
        myToast.setDuration(Toast.LENGTH_LONG);
        myToast.setView(view);
        myToast.setGravity(Gravity.BOTTOM | Gravity.FILL_HORIZONTAL, 0,200);
        myToast.show();
    }
}