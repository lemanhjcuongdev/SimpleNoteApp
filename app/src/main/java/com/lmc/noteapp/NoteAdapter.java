package com.lmc.noteapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class NoteAdapter extends ArrayAdapter<Note> {
    public NoteAdapter(Context context, ArrayList<Note> lstNote){
        super(context,0,lstNote);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentView = convertView;
        if(currentView == null){
            currentView = LayoutInflater.from(getContext()).inflate(R.layout.item_note,parent,false);
        }
        Note note = getItem(position);

        TextView subject_item = currentView.findViewById(R.id.subject_item);
        TextView content_item = currentView.findViewById(R.id.content_item);
        TextView date_item = currentView.findViewById(R.id.date_item);

        subject_item.setText(note.getSubject());
        content_item.setText(note.getContent());
        date_item.setText(note.getTime());

        return currentView;
    }
}

