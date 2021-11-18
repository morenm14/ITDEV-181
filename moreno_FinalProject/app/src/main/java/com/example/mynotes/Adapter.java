package com.example.mynotes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<Note> notes;

    public Adapter(Context context, List<Note> notes){
        this.inflater = LayoutInflater.from(context);
        this.notes = notes;

    }


    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_list_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        String title = notes.get(position).getTitle();
        String date = notes.get(position).getDate();
        String time = notes.get(position).getTime();
        long id = notes.get(position).getID();

        holder.title.setText(title);
        holder.date.setText(date);
        holder.time.setText(time);
        holder.noteID.setText("ID: " + String.valueOf(id));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, date, time, noteID;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.noteTitle);
            date = itemView.findViewById(R.id.noteDate);
            time = itemView.findViewById(R.id.noteTime);
            noteID = itemView.findViewById(R.id.noteID);

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), NoteDetails.class);
                intent.putExtra("ID", notes.get(getBindingAdapterPosition()).getID());
//                intent.putExtra("title",notes.get(getBindingAdapterPosition()).getTitle());
//                intent.putExtra("content", notes.get(getBindingAdapterPosition()).getContent());

                view.getContext().startActivity(intent);
            });
        }
    }
}
