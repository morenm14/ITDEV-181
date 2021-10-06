package com.example.fragmentsrecyclerviewdemo;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class addPersonFrag extends Fragment {

    EditText inputName, inputPhone;
    Button addButton;
    listFrag lFrag;
    FragmentManager fragmentManager;

    public addPersonFrag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_person, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inputName = view.findViewById(R.id.inputName);
        inputPhone = view.findViewById(R.id.inputPhone);
        addButton = view.findViewById(R.id.addButton);
        fragmentManager = this.getActivity().getSupportFragmentManager();
        lFrag = (listFrag) fragmentManager.findFragmentById(R.id.listFrag);

        addButton.setOnClickListener(view1 -> {

            if (inputName.getText().toString().isEmpty() || 
                    inputPhone.getText().toString().isEmpty()){
                Toast.makeText(getContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();
            }else {
                ApplicationClass.people.add(new Person(inputName.getText().toString().trim(),inputPhone.getText().toString().trim()));
                inputName.setText(null);
                inputPhone.setText(null);
                Toast.makeText(getContext(), "Person Added!", Toast.LENGTH_SHORT).show();
                lFrag.notifyDataChanged();
            }
        });
    }
}