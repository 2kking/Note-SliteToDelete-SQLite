package com.example.yxs.titlebar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yxs on 16/3/19.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String name="diary.db";
    public static final int version = 1;

    public DBHelper(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table diary" +
                "(_id integer primary key autoincrement,title varchar(20)," +
                "content varchar(1000),created)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
