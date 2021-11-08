package com.example.backendlessregisterandlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    View registerForm;
    View loadingView;
    TextView loadingText;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registerForm = findViewById(R.id.registerForm);
        loadingView = findViewById(R.id.loadingView);
        loadingText = findViewById(R.id.loadingText);
        registerBtn = findViewById(R.id.registerBtn);
        showProgress( false);

        registerBtn.setOnClickListener(view -> {
          startActivity(new Intent(Login.this, Register.class));
        });


    }

    public void  showProgress(boolean show){

        if (show){
            registerForm.setVisibility(View.GONE);
            loadingView.setVisibility(View.VISIBLE);
        }else{
            registerForm.setVisibility(View.VISIBLE);
            loadingView.setVisibility(View.GONE);
        }
    }
}