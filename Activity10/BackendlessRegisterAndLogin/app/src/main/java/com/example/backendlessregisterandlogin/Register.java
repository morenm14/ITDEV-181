package com.example.backendlessregisterandlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class Register extends AppCompatActivity {

    View registerForm;
    View loadingView;
    TextView loadingText;
    Button registerBtn;
    EditText name, email, password, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerForm = findViewById(R.id.registerForm);
        loadingView = findViewById(R.id.loadingView);
        loadingText = findViewById(R.id.loadingText);
        registerBtn = findViewById(R.id.registerBtn);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        showProgress(false);
        
        registerBtn.setOnClickListener( view -> {
            if (name.getText().toString().isEmpty() || email.getText().toString().isEmpty() ||
                    password.getText().toString().isEmpty() || confirmPassword.getText().toString().isEmpty()){
                Toast.makeText(Register.this, "Please enter all fields", Toast.LENGTH_SHORT).show();

            }else {

                if (password.getText().toString().trim().equals(confirmPassword.getText().toString().trim())){
                    String nameValue = name.getText().toString().trim();
                    String emailValue = email.getText().toString().trim();
                    String passwordValue = password.getText().toString().trim();

                    BackendlessUser user = new BackendlessUser();
                    user.setProperty("name", nameValue);
                    user.setEmail(emailValue);
                    user.setPassword(passwordValue);

                    showProgress(true);
                    loadingText.setText("Registering " + nameValue);

                    Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser response) {

                            name.setText(null);
                            email.setText(null);
                            password.setText(null);
                            confirmPassword.setText(null);

                            Toast.makeText(Register.this,  response.getProperty("name") + " has been registered!", Toast.LENGTH_LONG).show();

                            String user = (String) response.getProperty("name");
                            Intent registered = new Intent(Register.this, Login.class);
                            registered.putExtra("user", user);
                            startActivity(registered);
                            Register.this.finish();
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            showProgress(false);
                            Toast.makeText(Register.this, fault.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }

                else{

                    Toast.makeText(Register.this, "passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }

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