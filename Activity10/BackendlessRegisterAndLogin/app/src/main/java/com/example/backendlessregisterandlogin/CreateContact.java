package com.example.backendlessregisterandlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class CreateContact extends AppCompatActivity {

    EditText name, phone, email;
    Button saveContactBtn;
    TextView loadingText;
    View loadingView, newContactForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        loadingText = findViewById(R.id.loadingText);
        loadingView = findViewById(R.id.loadingView);
        newContactForm = findViewById(R.id.newContactForm);
        saveContactBtn = findViewById(R.id.saveContactBtn);

        saveContactBtn.setOnClickListener(view -> {
            
            if (!name.getText().toString().isEmpty() && !phone.getText().toString().isEmpty() && !email.getText().toString().isEmpty()){

                Contact contact = new Contact();
                contact.setName(name.getText().toString().trim());
                contact.setPhone(phone.getText().toString().trim());
                contact.setEmail(email.getText().toString().trim());
                contact.setUserEmail(AppInitializer.user.getEmail());

                loadingView.setVisibility(View.VISIBLE);
                newContactForm.setVisibility(View.INVISIBLE);
                loadingText.setText("Saving New Contact...");

                Backendless.Persistence.save(contact, new AsyncCallback<Contact>() {
                    @Override
                    public void handleResponse(Contact response) {

                        name.setText(null);
                        phone.setText(null);
                        email.setText(null);

                        Toast.makeText(CreateContact.this, "Saved!", Toast.LENGTH_SHORT).show();

                        loadingView.setVisibility(View.GONE);
                        newContactForm.setVisibility(View.VISIBLE);

                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {

                    }
                });

                
            }else{
                Toast.makeText(CreateContact.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }
}