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
    String [] pieces;

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

        pieces = getResources().getStringArray(R.array.pieces);

        setListAdapter(new ArrayAdapter<>(inflater.getContext(), android.R.layout.simple_list_item_1,pieces));
        if (this.getActivity().findViewById(R.id.layout_land) != null){
            listener.clickedItem(0);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        listener.clickedItem(position);
    }
}