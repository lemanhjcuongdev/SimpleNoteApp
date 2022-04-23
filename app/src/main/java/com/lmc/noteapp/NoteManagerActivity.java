package com.lmc.noteapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class NoteManagerActivity extends AppCompatActivity {

    MenuItem detail, update, delete;
    FloatingActionButton btnAddNote;
    final int INSERT_CODE = 1000;
    ListView lvNote;
    ArrayList<Note> lstNote = new ArrayList<>();
    NoteAdapter noteAdapter;
    NoteDBHelper noteDBHelper;

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_manager);

        btnAddNote = findViewById(R.id.btnAddNote);
        lvNote = findViewById(R.id.lvNote);
//        img = findViewById(R.id.imageView);

//        String url = "https://img1.oto.com.vn/crop/575x430/2022/02/18/20220218162001-dcd1_wm.jpg";
//        Uri imgWeb = Uri.parse(url);
//        Intent intentImg = new Intent(Intent.ACTION_VIEW,imgWeb);
//        startActivity(intentImg);

//        int id = R.drawable.bmw_1;
//        img.setImageResource(id);

//        noteAdapter = new NoteAdapter(NoteManagerActivity.this,lstNote);
//        lvNote.setAdapter(noteAdapter);
        getListNote();

        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoteManagerActivity.this,AddNoteActivity.class);
//                intent.putExtra("msg","Alo 123 Test");
//                startActivity(intent);
                startActivityForResult(intent, INSERT_CODE);
            }
        });
        registerForContextMenu(lvNote);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.longclick_note_item,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.detail_option:
                    //xu li detail
                    Toast.makeText(this, "Detail", Toast.LENGTH_SHORT).show();
                break;
            case R.id.update_option:
                //xu li update
                Toast.makeText(this, "Update", Toast.LENGTH_SHORT).show();
                break;
            case R.id.del_option:
                //Xu li xoa
                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    private void getListNote(){
        noteDBHelper = new NoteDBHelper(NoteManagerActivity.this);
        lstNote = noteDBHelper.getAllNote();
        noteAdapter = new NoteAdapter(NoteManagerActivity.this,lstNote);
        //set adapter cho listview
        lvNote.setAdapter(noteAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == INSERT_CODE) {
            if(resultCode == RESULT_OK){
                String subject = data.getStringExtra("subject");
                String content = data.getStringExtra("content");
                String time = data.getStringExtra("time");
//                Toast.makeText(this, subject+content+time, Toast.LENGTH_SHORT).show();
//                lstNote.add(new Note(subject,content,time));


                noteDBHelper = new NoteDBHelper(NoteManagerActivity.this);
                noteDBHelper.insertNote(new Note(subject,content,time));
//                noteAdapter.notifyDataSetChanged();
                getListNote();
            }
        }else
        super.onActivityResult(requestCode,resultCode,data);
    }
}