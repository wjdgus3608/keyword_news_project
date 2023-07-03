package com.example.keyword_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.keyword_project.adapter.NewsItemAdapter;
import com.example.keyword_project.adapter.NewsKeywordAdapter;
import com.example.keyword_project.item.NewsItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private boolean isSettingBtnClicked = false;
    private int clickedKeywordIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setNewsKeywordRecyclerView();
        setNewsRecyclerView();
        setSettingBtn();
    }

    private void setNewsKeywordRecyclerView(){
        List<String> dataList = getKeywordList();
        RecyclerView recyclerView = findViewById(R.id.main_keyword_recyclerview);
        NewsKeywordAdapter adapter = new NewsKeywordAdapter(dataList);

        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);

        adapter.setOnItemClickListener((v, pos) -> {
            modifyKeywordColor(v,layoutManager);
            clickedKeywordIndex = pos;
        });

    }

    private void modifyKeywordColor(View v, RecyclerView.LayoutManager layoutManager){
        ColorStateList blueBackGround = ColorStateList.valueOf(Color.parseColor("#DEE9FF"));
        ColorStateList whiteBackGround = ColorStateList.valueOf(Color.parseColor("#F5F5F5"));
        int grayText = Color.parseColor("#B6B6B6");
        int blueText = Color.parseColor("#3A7DFF");

        View oldView = layoutManager.findViewByPosition(clickedKeywordIndex);
        if(oldView != null){
            oldView.setBackgroundTintList(whiteBackGround);
            TextView tx = oldView.findViewById(R.id.item_keyword_text_view);
            tx.setTextColor(grayText);
        }

        v.setBackgroundTintList(blueBackGround);
        TextView tx = v.findViewById(R.id.item_keyword_text_view);
        tx.setTextColor(blueText);
    }
    private List<String> getKeywordList(){
        List<String> list = new ArrayList<>();

        list.add("속보");
        list.add("특징주");
        list.add("김하성");
        list.add("첼시");
        list.add("LG");

        return list;
    }


    private void setNewsRecyclerView(){
        List<NewsItem> dataList = getNewsList();
        RecyclerView recyclerView = findViewById(R.id.main_recyclerview);
        NewsItemAdapter adapter = new NewsItemAdapter(dataList);
        adapter.setOnItemClickListener((v, pos) -> {

        });
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

    private void setSettingBtn(){
        ImageButton settingBtn = findViewById(R.id.main_setting_btn);

        settingBtn.setOnClickListener(v -> {
            switchSettingBtnImage(settingBtn);
        });
    }

    private void switchSettingBtnImage(ImageButton settingBtn){
        settingBtn.setImageResource(isSettingBtnClicked ? R.drawable.icon_setting : R.drawable.icon_close);
        isSettingBtnClicked = !isSettingBtnClicked;
    }
}