package com.example.recyclerviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    private ArrayList<Person> people;
    ItemCLicked activity;

    public PersonAdapter(Context context, ArrayList<Person> list){

        people = list;
        activity = (ItemCLicked) context;


    }

    public interface ItemCLicked{
        public void onItemClicked(int index);
    }


    @NonNull
    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.ViewHolder holder, int position) {

        holder.itemView.setTag(people.get(position));
        holder.name.setText(people.get(position).getName());
        holder.lastName.setText(people.get(position).getLastName());

        if (people.get(position).getPreference().equals("plane")){

            holder.imageView.setImageResource(R.drawable.plane);

        }else {

            holder.imageView.setImageResource(R.drawable.bus);

        }
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView name, lastName;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.name);
            lastName = itemView.findViewById(R.id.lastName);

            itemView.setOnClickListener(view -> {

                activity.onItemClicked(people.indexOf((Person) view.getTag()));
            });
        }
    }
}
