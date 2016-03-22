package com.example.yxs.titlebar;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yxs on 16/3/19.
 */
public class SQLite_Diary {
    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    public SQLite_Diary(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void save(Diary diary) {
        String insert = "insert  into diary(title,content,created) values(?,?,?)";
        sqLiteDatabase = dbHelper.getWritableDatabase();
        sqLiteDatabase.execSQL(
                insert,
                new String[]{diary.getTitle(), diary.getContent(), diary.getDatetime()});
    }

    public void delete(Integer id) {
        String delete = "delete from diary where _id=?";
        sqLiteDatabase = dbHelper.getWritableDatabase();
        sqLiteDatabase.execSQL(
                delete,
                new Object[]{id});
    }

    public void update(Diary diary) {
        String update = "update diary set title=?,content=?,created=? where _id=?";
        sqLiteDatabase = dbHelper.getWritableDatabase();
        sqLiteDatabase.execSQL(
                update,
                new Object[]{diary.getTitle(), diary.getContent(),
                        diary.getDatetime(), diary.getId()});
    }

    public List<Diary> getAllDairies() {
        Diary diary;
        List<Diary> diaries = new ArrayList<Diary>();
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from diary ", null);

        while (cursor.moveToNext()) {
            int id=cursor.getInt(cursor.getColumnIndex("_id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String created = cursor.getString(cursor.getColumnIndex("created"));
            diary = new Diary(title, content, created);
            diary.setId(id);
            diaries.add(diary);
        }

        return diaries;
    }

    public long count() {
        long count = 0;
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select count(*) from diary ",
                null);
        cursor.moveToFirst();
        count = cursor.getLong(0);
        return count;

    }

    public Diary getDiaryById(int id) {
        sqLiteDatabase = dbHelper.getWritableDatabase();
        Diary diary = null;
        Cursor cursor = sqLiteDatabase.rawQuery(
                "select * from diary where _id= ?", new String[]{id + ""});
        if (cursor.moveToFirst()) {

            String title = cursor.getString(cursor.getColumnIndex("title"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String created = cursor.getString(cursor.getColumnIndex("created"));
            diary = new Diary(title, content, created);

        }

        return diary;
    }

}
