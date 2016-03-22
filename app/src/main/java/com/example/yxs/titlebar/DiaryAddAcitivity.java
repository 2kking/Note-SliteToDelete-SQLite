package com.example.yxs.titlebar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

/**
 * Created by yxs on 16/3/19.
 */
public class DiaryAddAcitivity extends AppCompatActivity {

    SQLite_Diary sqLite_diary = new SQLite_Diary(DiaryAddAcitivity.this);
    Button button;
    EditText editText, editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diaryadd);

        editText = (EditText) findViewById(R.id.title_diaryadd);
        editText2 = (EditText) findViewById(R.id.content_diaryadd);
        button = (Button) findViewById(R.id.save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editText.getText().toString();
                String content = editText2.getText().toString();
                Diary diary = new Diary(title, content, Tool.dateChange(new Date()));
                SQLite_Diary sqLite_diary = new SQLite_Diary(DiaryAddAcitivity.this);
                sqLite_diary.save(diary);
                finish();
            }
        });

    }
}
