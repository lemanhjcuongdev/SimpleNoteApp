package com.lmc.noteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class NoteDBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME="note.db";
    public static final int DB_VERSION=1;
    public static String TB_NAME="tbl_note";
    public static String ID="id";
    public static String SUBJECT="subject";
    public static String CONTENT="content";
    public static String TIME="time";
    public Context context;

    public NoteDBHelper(@Nullable Context context){
        super(context, DB_NAME, null, DB_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE "+TB_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT"+", "+SUBJECT+" TEXT, "+CONTENT+" TEXT, "+TIME+" TEXT);";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL("INSERT INTO "+TB_NAME+" VALUES(" +
                "null,'Tiêu đề mới','Nội dung mới','Thời gian mới')," +
                "(null,'Tiêu đề 2','Nội dung 2','Thời gian 2')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql="DROP TABLE IF EXISTS "+TB_NAME;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }
    //phuong thuc insert note
    public void insertNote(Note note){
        ContentValues cv = new ContentValues();
        //put du lieu can insert trong doi tuong contentvalue
        cv.put(SUBJECT,note.getSubject());
        cv.put(CONTENT,note.getContent());
        cv.put(TIME,note.getTime());
        //lay ra sqlite db de thuc hien ghi du lieu
        SQLiteDatabase db = getWritableDatabase();
        long result = db.insert(TB_NAME,null,cv);
        if(result!=-1)
            Toast.makeText(context, "Insert Successfully!", Toast.LENGTH_LONG).show();
        else Toast.makeText(context, "Insert Failed!", Toast.LENGTH_LONG).show();
    }
    //pthuc update note
    public void updateNote(Note note, String subject){
        ContentValues contentUpdate = new ContentValues();
        contentUpdate.put(SUBJECT,note.getSubject());
        contentUpdate.put(CONTENT,note.getContent());
        contentUpdate.put(TIME,note.getTime());
        SQLiteDatabase db = getWritableDatabase();
        int result = db.update(TB_NAME,contentUpdate, SUBJECT + " LIKE % ?", new String[]{subject});
        if(result>0)
            Toast.makeText(context, "Update successfully!", Toast.LENGTH_LONG).show();
        else Toast.makeText(context, "Update Failed!", Toast.LENGTH_LONG).show();
    }
    //pthuc xoa note
    public void delNote(String subject){
        SQLiteDatabase db = getWritableDatabase();
        int result = db.delete(TB_NAME,SUBJECT + " LIKE % ?",new String[]{subject});
        if(result>0)
            Toast.makeText(context, "Delete successfully!", Toast.LENGTH_LONG).show();
        else Toast.makeText(context, "Delete Failed!", Toast.LENGTH_LONG).show();
    }
    //pthuc getAllNote
    public ArrayList<Note> getAllNote(){
        ArrayList<Note> result = new ArrayList<>();
        //lay ra sqlitedb thuc hien doc du lieu
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TB_NAME, null);
        while(cursor.moveToNext()){
            Note note = new Note();
            note.setSubject(cursor.getString(1));
            note.setContent(cursor.getString(2));
            note.setTime(cursor.getString(3));
            result.add(note);
        }
        return result;
    }
    //tim kiem
    public Note getNoteBySubject(String subject){
        Note note = new Note();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TB_NAME+" WHERE "+SUBJECT +" LIKE ?",new String[]{subject});
        if (cursor.getCount()>0){
            note.setSubject(cursor.getString(1));
            note.setContent(cursor.getString(2));
            note.setTime(cursor.getString(3));
        }
        return note;
    }
}
