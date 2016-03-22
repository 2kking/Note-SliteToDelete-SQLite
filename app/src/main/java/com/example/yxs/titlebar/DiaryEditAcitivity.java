package com.example.yxs.titlebar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

/**
 * Created by yxs on 16/3/19.
 */
public class DiaryEditAcitivity extends AppCompatActivity {

    SQLite_Diary sqLite_diary = new SQLite_Diary(DiaryEditAcitivity.this);
    Button button;
    EditText editText, editText2;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diaryadd);

        editText = (EditText) findViewById(R.id.title_diaryadd);
        editText2 = (EditText) findViewById(R.id.content_diaryadd);
        button = (Button) findViewById(R.id.save);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                click();
            }
        });
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getInt("id");
            Diary diary = sqLite_diary.getDiaryById(id);
            editText.setText(diary.getTitle());
            editText2.setText(diary.getContent());

        }

    }
    public void click() {

        String title = editText.getText().toString();
        String content = editText2.getText().toString();
        Diary diary = new Diary(title, content, Tool.dateChange(new Date()));
        diary.setId(id);
        SQLite_Diary diaryDao = new SQLite_Diary(this);
        diaryDao.update(diary);
        finish();

    }
}
