package com.example.keyword_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.keyword_project.adapter.NewsItemAdapter;
import com.example.keyword_project.item.NewsItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setNewsRecyclerView();
    }

    private void setNewsRecyclerView(){
        List<NewsItem> dataList = getNewsList();
        RecyclerView recyclerView = findViewById(R.id.main_recyclerview);
        NewsItemAdapter adapter = new NewsItemAdapter(dataList);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private List<NewsItem> getNewsList(){
        List<NewsItem> list = new ArrayList<>();
        list.add(new NewsItem(R.drawable.icon_badge,"20230606","title","text"));
        list.add(new NewsItem(R.drawable.icon_badge,"20230606","title","text"));
        list.add(new NewsItem(R.drawable.icon_badge,"20230606","title","text"));
        list.add(new NewsItem(R.drawable.icon_badge,"20230606","title","text"));
        list.add(new NewsItem(R.drawable.icon_badge,"20230606","title","text"));
        list.add(new NewsItem(R.drawable.icon_badge,"20230606","title","text"));
        list.add(new NewsItem(R.drawable.icon_badge,"20230606","title","text"));
        list.add(new NewsItem(R.drawable.icon_badge,"20230606","title","text"));
        return list;
    }
}