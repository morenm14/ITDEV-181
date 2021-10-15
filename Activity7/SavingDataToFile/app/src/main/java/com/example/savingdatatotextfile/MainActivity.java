package com.example.savingdatatotextfile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    EditText name, lastName;
    Button addBtn, saveBtn;
    TextView results;
    ArrayList<Person> persons;
    String dataFile = "data.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        lastName = findViewById(R.id.lastName);
        addBtn = findViewById(R.id.addBtn);
        saveBtn = findViewById(R.id.saveBtn);
        results = findViewById(R.id.results);

        persons = new ArrayList<>();
        loadData();

        //add Button
        addBtn.setOnClickListener(view -> {
            
            if (name.getText().toString().isEmpty() || lastName.getText().toString().isEmpty()){
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            }else {
                String inName = name.getText().toString().trim();
                String inLastName = lastName.getText().toString().trim();
                Person person = new Person(inName,inLastName);
                persons.add(person);
            }
            setTextToTextView();
            name.setText(null);
            lastName.setText(null);
        });

        //save to file button
        saveBtn.setOnClickListener(view -> {
            try {
                FileOutputStream file = openFileOutput(dataFile, MODE_PRIVATE);
                OutputStreamWriter writer = new OutputStreamWriter(file);
                for (int i = 0; i < persons.size(); i++) {
                    writer.write(persons.get(i).getName() + "," + persons.get(i).getLastName() + "\n");
                }
                writer.flush();
                writer.close();
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
            }catch (IOException e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setTextToTextView(){
        String text = "";
        for (int i = 0; i < persons.size(); i++) {
            text = text + persons.get(i).getName() + " " + persons.get(i).getLastName() + "\n";
        }
        results.setText(text);
    }

    public void loadData(){
        persons.clear();

        File file = getApplicationContext().getFileStreamPath(dataFile);
        String line;

        if (file.exists()){
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput(dataFile)));
                while ((line = reader.readLine()) != null){

                    StringTokenizer token = new StringTokenizer(line, ",");
                    Person person = new Person(token.nextToken(), token.nextToken());
                    persons.add(person);
                }
                reader.close();
                setTextToTextView();
            }catch (IOException e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}