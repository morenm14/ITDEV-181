package com.example.backendlessregisterandlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class MainActivity extends AppCompatActivity {

    TextView welcome;
    Button logoutBtn;
    View loadingView, logoutView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logoutBtn = findViewById(R.id.logoutBtn);
        loadingView = findViewById(R.id.loadingView);
        logoutView =findViewById(R.id.logoutView);
        welcome = findViewById(R.id.result);
        String result = getIntent().getStringExtra("user");
        welcome.setText("Welcome " + result + "!!!");



        logoutBtn.setOnClickListener(view -> {

            loadingView.setVisibility(View.VISIBLE);
            logoutView.setVisibility(View.INVISIBLE);

            Backendless.UserService.logout(new AsyncCallback<Void>() {
                @Override
                public void handleResponse(Void response) {

                    Toast.makeText(MainActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, Login.class));
                    welcome.setText("logged out");
                    loadingView.setVisibility(View.INVISIBLE);
                    logoutView.setVisibility(View.VISIBLE);
                    MainActivity.this.finish();

                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    Toast.makeText(MainActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        });
    }

}
