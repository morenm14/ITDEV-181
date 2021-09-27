package com.example.fragmentsdemo;

import android.content.Context;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;


public class ListFrag extends ListFragment{
    ArrayList<String> data;

    public interface Listener {
         void clickedItem(int index);
    }

    Listener listener;

    public ListFrag() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        listener = (Listener) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        data = new ArrayList<>();
        data.add("1. This is item 1");
        data.add("2. This is item 2");
        data.add("3. This is item 3");
        data.add("4. This is item 4");
        data.add("5. This is item 5");
        setListAdapter(new ArrayAdapter<>(inflater.getContext(), android.R.layout.simple_list_item_1,data));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        listener.clickedItem(position);
    }
}