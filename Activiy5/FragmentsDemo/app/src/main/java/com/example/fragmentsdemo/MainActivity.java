package com.example.fragmentsdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ListFrag.Listener {

    TextView description;

    ArrayList<String> descriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        description = findViewById(R.id.description);

        descriptions = new ArrayList<>();
        descriptions.add("Description item 1");
        descriptions.add("Description item 2");
        descriptions.add("Description item 3");
        descriptions.add("Description item 4");
        descriptions.add("Description item 5");

    }

    @Override
    public void clickedItem(int index) {
        Bundle bundle = new Bundle();
        bundle.putString("myKey", descriptions.get(index));
        getSupportFragmentManager().setFragmentResult("requestKey", bundle);
    }
}