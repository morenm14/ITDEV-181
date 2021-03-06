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
    Button logoutBtn, newContactBtn, myContactsBtn;
    View loadingView, logoutView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logoutBtn = findViewById(R.id.logoutBtn);
        loadingView = findViewById(R.id.loadingView);
        logoutView =findViewById(R.id.logoutView);
        welcome = findViewById(R.id.result);
        newContactBtn = findViewById(R.id.createNewContactBtn);
        myContactsBtn = findViewById(R.id.myContactsBtn);

        String result = getIntent().getStringExtra("user");
        welcome.setText("Welcome " + result + "!!!");


        newContactBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CreateContact.class);
            startActivity(intent);
        });

        myContactsBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ContactList.class);
            startActivity(intent);

        });

        //log out
        logoutBtn.setOnClickListener(view -> {
            loadingView.setVisibility(View.VISIBLE);
            logoutView.setVisibility(View.GONE);

            Backendless.UserService.logout(new AsyncCallback<Void>() {
                @Override
                public void handleResponse(Void response) {
                    Toast.makeText(MainActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, Login.class));
                    welcome.setText("logged out");
                    MainActivity.this.finish();
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    Toast.makeText(MainActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                    loadingView.setVisibility(View.GONE);

                }
            });
        });
    }

}
