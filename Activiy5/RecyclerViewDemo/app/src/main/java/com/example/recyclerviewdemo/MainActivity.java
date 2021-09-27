package com.example.recyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PersonAdapter.ItemCLicked {

    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Person> people;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        people = new ArrayList<>();
        people.add(new Person("Mario", "Moreno","bus"));
        people.add(new Person("Bruce", "Lee","plane"));
        people.add(new Person("Chuck", "Morris","plane"));
        people.add(new Person("Jackie", "Chan","bus"));
        people.add(new Person("Mario", "Moreno","bus"));
        people.add(new Person("Bruce", "Lee","plane"));
        people.add(new Person("Chuck", "Morris","plane"));
        people.add(new Person("Jackie", "Chan","bus"));
        people.add(new Person("Mario", "Moreno","bus"));
        people.add(new Person("Bruce", "Lee","plane"));
        people.add(new Person("Chuck", "Morris","plane"));
        people.add(new Person("Jackie", "Chan","bus"));
        people.add(new Person("Mario", "Moreno","bus"));
        people.add(new Person("Bruce", "Lee","plane"));
        people.add(new Person("Chuck", "Morris","plane"));
        people.add(new Person("Jackie", "Chan","bus"));
        people.add(new Person("Mario", "Moreno","bus"));
        people.add(new Person("Bruce", "Lee","plane"));
        people.add(new Person("Chuck", "Morris","plane"));
        people.add(new Person("Jackie", "Chan","bus"));
        people.add(new Person("Mario", "Moreno","bus"));
        people.add(new Person("Bruce", "Lee","plane"));
        people.add(new Person("Chuck", "Morris","plane"));
        people.add(new Person("Jackie", "Chan","bus"));

        myAdapter = new  PersonAdapter(this, people);
        recyclerView.setAdapter(myAdapter);


    }

    @Override
    public void onItemClicked(int index) {

        Toast.makeText(this, "Preference: " + people.get(index).getPreference() , Toast.LENGTH_SHORT).show();
    }
}