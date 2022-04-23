package com.lmc.noteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddNoteActivity extends AppCompatActivity {
    EditText edtSubject, edtContent;
    Button btnSave, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        edtSubject = findViewById(R.id.subject);
        edtContent = findViewById(R.id.content);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

//        Intent receivedIntent = getIntent();
//        String mess = receivedIntent.getStringExtra("msg");
//        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                String currentDate = "Tạo vào "+calendar.get(Calendar.DATE)+
                        "/"+(calendar.get(Calendar.MONTH)+1)+
                        "/"+calendar.get(Calendar.YEAR)+
                        " lúc "+calendar.get(Calendar.HOUR_OF_DAY)+
                        "H"+calendar.get(Calendar.MINUTE)+
                        "'";
                Intent intentResult = new Intent();
                intentResult.putExtra("subject",edtSubject.getText().toString());
                intentResult.putExtra("content",edtContent.getText().toString());
                intentResult.putExtra("time", currentDate.toString());
                setResult(RESULT_OK,intentResult);
                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}