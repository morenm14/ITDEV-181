package com.example.backendlessregisterandlogin;

import androidx.appcompat.app.AppCompatActivity;

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
import com.backendless.persistence.local.UserIdStorageFactory;

public class Login extends AppCompatActivity {

    View registerForm;
    View loadingView;
    TextView loadingText, passwordReset;
    Button registerBtn, loginBtn;
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registerForm = findViewById(R.id.registerForm);
        loadingView = findViewById(R.id.loadingView);
        loadingText = findViewById(R.id.loadingText);
        passwordReset = findViewById(R.id.resetPasswordBtn);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        registerBtn = findViewById(R.id.registerBtn);
        loginBtn = findViewById(R.id.loginBtn);

        showProgress(false);

        Backendless.UserService.isValidLogin(new AsyncCallback<Boolean>() {
            @Override
            public void handleResponse(Boolean response) {
                if (response){
                    String objectId = UserIdStorageFactory.instance().getStorage().get();
                    Backendless.Data.of(BackendlessUser.class).findById(objectId, new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser response) {
                            Intent loggedInUser = new Intent(Login.this, MainActivity.class);
                            loggedInUser.putExtra("user", response.getProperty("name").toString());
                            startActivity(loggedInUser);
                            showProgress(false);
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(Login.this, fault.getMessage(), Toast.LENGTH_LONG).show();
                            showProgress(false);
                        }
                    });
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(Login.this, fault.getMessage(), Toast.LENGTH_LONG).show();
                showProgress(false);
            }
        });

        //register button
        registerBtn.setOnClickListener(view -> {
            startActivity(new Intent(Login.this, Register.class));
        });

        //log in
        loginBtn.setOnClickListener(view -> {
            if (!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()){
                String emailValue = email.getText().toString().trim();
                String passwordValue = password.getText().toString().trim();

                showProgress(true);
                loadingText.setText("Logging in...");

                Backendless.UserService.login(emailValue, passwordValue, new AsyncCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser response) {
                        String user = response.getProperty("name").toString();
                        Intent loginIntent = new Intent(Login.this, MainActivity.class);
                        loginIntent.putExtra("user", user);

                        email.setText(null);
                        password.setText(null);

                        startActivity(loginIntent);
                        showProgress(false);
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(Login.this, fault.getMessage(), Toast.LENGTH_LONG).show();
                        showProgress(false);
                    }
                }, true);

            }else{
                showProgress(false);
                Toast.makeText(Login.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            }
        });

        //reset password
        passwordReset.setOnClickListener(view -> {
            if (email.getText().toString().isEmpty()) {

                Toast.makeText(Login.this, "Please enter your email in the email field to reset your password", Toast.LENGTH_LONG).show();
            }else{
                String objectId = email.getText().toString().trim();
                showProgress(true);

                Backendless.UserService.restorePassword(objectId, new AsyncCallback<Void>() {
                    @Override
                    public void handleResponse(Void response) {
                        Toast.makeText(Login.this, "Please check your email to restore your password", Toast.LENGTH_LONG).show();
                        email.setText(null);
                        password.setText(null);
                        showProgress(false);
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(Login.this, fault.getMessage(), Toast.LENGTH_LONG).show();
                        showProgress(false);
                    }
                });
            }
        });
    }

   //show activity progress
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