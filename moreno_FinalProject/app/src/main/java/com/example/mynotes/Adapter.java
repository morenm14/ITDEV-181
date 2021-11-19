package com.example.mynotes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final List<Note> notes;

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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        String title = notes.get(position).getTitle();
        String date = notes.get(position).getDate();
        String time = notes.get(position).getTime();
        long id = notes.get(position).getID();

        holder.title.setText(title);
        holder.date.setText(date);
        holder.time.setText(time);
        holder.noteID.setText("ID: " + id);
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
                view.getContext().startActivity(intent);
            });
        }
    }
}
