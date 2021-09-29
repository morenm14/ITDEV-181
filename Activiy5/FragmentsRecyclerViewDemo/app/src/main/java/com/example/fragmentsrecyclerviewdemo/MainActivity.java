package com.example.fragmentsrecyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultOwner;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements PersonAdapter.ItemClicked {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onItemClicked(int index) {
        Bundle result = new Bundle();
        result.putString("personName",ApplicationClass.people.get(index).getName());
        result.putString("personNumber", ApplicationClass.people.get(index).getPhoneNumber());
        getSupportFragmentManager().setFragmentResult("personDetails", result);
    }


}