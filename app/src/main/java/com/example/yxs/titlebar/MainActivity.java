package com.example.yxs.titlebar;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.ContextMenu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements SlideView.OnSlideListener,
        AdapterView.OnItemClickListener, View.OnClickListener {
    List<Diary> diaries;
    private static final String TAG = "MainActivity";
    Diary diary;
    int idd;
    private ListViewCompat listView;
    private List<MessageItem> mMessageItems = new ArrayList<MainActivity.MessageItem>();
    MessageItem item;
    private SlideView mLastSlideViewWithStatusOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListViewCompat) findViewById(R.id.lv);
        refreshList();
        (findViewById(R.id.add)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DiaryAddAcitivity.class));
            }
        });
    }

    private void delete(int id) {
        SQLite_Diary sql_d = new SQLite_Diary(MainActivity.this);
        sql_d.delete(id);
        refreshList();
    }

    private void refreshList() {
        mMessageItems.clear();
        SQLite_Diary sqlite_diary = new SQLite_Diary(this);
        diaries = sqlite_diary.getAllDairies();
        for (Diary d : diaries) {
            item = new MessageItem();
            item.title = d.getTitle();
            item.time = d.getDatetime();
            mMessageItems.add(item);
        }
        listView.setAdapter(new SlideAdapter());
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshList();
    }

    private class SlideAdapter extends BaseAdapter {

        private LayoutInflater mInflater;

        SlideAdapter() {
            super();
            mInflater = getLayoutInflater();
        }

        @Override
        public int getCount() {
            return mMessageItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mMessageItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            SlideView slideView = (SlideView) convertView;
            if (slideView == null) {
                View itemView = mInflater.inflate(R.layout.list_item, null);

                slideView = new SlideView(MainActivity.this);
                slideView.setContentView(itemView);

                holder = new ViewHolder(slideView);
                slideView.setOnSlideListener(MainActivity.this);
                slideView.setTag(holder);
            } else {
                holder = (ViewHolder) slideView.getTag();
            }
            MessageItem item = mMessageItems.get(position);
            item.slideView = slideView;
            item.slideView.shrink();

            holder.title.setText(item.title);
            holder.time.setText(item.time);
            holder.deleteHolder.setOnClickListener(MainActivity.this);

            return slideView;
        }

    }

    public class MessageItem {

        public String title;
        public String time;
        public SlideView slideView;
    }

    private static class ViewHolder {
        public TextView title;
        public TextView time;
        public ViewGroup deleteHolder;

        ViewHolder(View view) {
            title = (TextView) view.findViewById(R.id.title);
            time = (TextView) view.findViewById(R.id.time);
            deleteHolder = (ViewGroup) view.findViewById(R.id.holder);
        }
    }

    //点击进入编辑界面
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        diary = diaries.get(position);
        idd = diary.getId();
        Log.e(TAG, "onItemClick position=" + position);
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt("id", diary.getId());
        intent.putExtras(bundle);
        intent.setClass(MainActivity.this, DiaryEditAcitivity.class);
        startActivity(intent);
    }

    @Override
    public void onSlide(View view, int status) {
        if (mLastSlideViewWithStatusOn != null && mLastSlideViewWithStatusOn != view) {
            mLastSlideViewWithStatusOn.shrink();
        }

        if (status == SLIDE_STATUS_ON) {
            mLastSlideViewWithStatusOn = (SlideView) view;
        }
    }

    //滑动点击删除
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.holder) {
            Log.e(TAG, "onClick v=" + v);
            //获取当前posiition
            int position = listView.getPositionForView(v);
            //获取对应SQLite 的id
            diary = diaries.get(position);
            idd = diary.getId();
            delete(idd);
        }
    }

}
