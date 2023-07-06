package com.example.keyword_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.keyword_project.adapter.NewsItemAdapter;
import com.example.keyword_project.adapter.NewsKeywordAdapter;
import com.example.keyword_project.item.NewsItem;
import com.example.keyword_project.item.NewsKeyword;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private NewsItemAdapter newsItemAdapter;
    private final List<NewsItem> newsData = new ArrayList<>();
    private final List<NewsItem> newsItemList = new ArrayList<>();
    private boolean isSettingBtnClicked = false;
    private int clickedKeywordIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callGetNewsDataApi();

        setNewsKeywordRecyclerView();
        setNewsRecyclerView();
        setSettingBtn();
    }

    private void callGetNewsDataApi(){

    }

    private void setNewsKeywordRecyclerView(){
        List<NewsKeyword> dataList = getKeywordList();
        RecyclerView recyclerView = findViewById(R.id.main_keyword_recyclerview);
        NewsKeywordAdapter adapter = new NewsKeywordAdapter(dataList);

        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);

        adapter.setOnItemClickListener((v, pos) -> {
            if(pos < dataList.size()-1) {
                changeClickedKeyword(adapter, dataList, pos);
                renderNewsItem();
            }
            else{
                showAddKeywordPopup();
            }
        });
    }

    private void changeClickedKeyword(NewsKeywordAdapter adapter, List<NewsKeyword> dataList, int pos){
        resetPreviousKeywordView(dataList);
        adapter.notifyItemChanged(clickedKeywordIndex);
        changeNextKeywordView(dataList, pos);
        adapter.notifyItemChanged(clickedKeywordIndex);
    }

    private void renderNewsItem(){
        newsItemList.clear();
        newsItemList.add(new NewsItem(R.drawable.icon_badge,"20230606","title","text"));
        newsItemAdapter.notifyDataSetChanged();
    }

    private void showAddKeywordPopup(){
        
    }
    private void resetPreviousKeywordView(List<NewsKeyword> dataList){
        dataList.get(clickedKeywordIndex).setClicked(false);
    }

    private void changeNextKeywordView(List<NewsKeyword> dataList, int pos){
        clickedKeywordIndex = pos;
        dataList.get(clickedKeywordIndex).setClicked(true);
    }

    private List<NewsKeyword> getKeywordList(){
        List<NewsKeyword> list = new ArrayList<>();

        list.add(new NewsKeyword("속보",true));
        list.add(new NewsKeyword("특징주",false));
        list.add(new NewsKeyword("김하성",false));
        list.add(new NewsKeyword("첼시",false));
        list.add(new NewsKeyword("LG",false));
        list.add(new NewsKeyword("LG",false));
        list.add(new NewsKeyword("LG",false));
        list.add(new NewsKeyword("LG",false));
        list.add(new NewsKeyword("LG",false));
        list.add(new NewsKeyword("LG",false));
        list.add(new NewsKeyword("LG",false));
        list.add(new NewsKeyword("LG",false));
        list.add(new NewsKeyword("LG",false));
        list.add(new NewsKeyword("LG",false));
        list.add(new NewsKeyword("LG",false));

        addPlusBtn(list);

        return list;
    }

    private void addPlusBtn(List<NewsKeyword> list){
        list.add(new NewsKeyword("+",false));
    }


    private void setNewsRecyclerView(){
        setNewsList();

        RecyclerView recyclerView = findViewById(R.id.main_recyclerview);
        newsItemAdapter = new NewsItemAdapter(newsItemList,this);
        recyclerView.setAdapter(newsItemAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        setNewsRecyclerViewScrollEvent(recyclerView);
    }

    private void setNewsList(){
        newsItemList.add(new NewsItem(R.drawable.icon_badge,"20230606","title","text"));
        newsItemList.add(new NewsItem(R.drawable.icon_badge,"20230606","title","text"));
        newsItemList.add(new NewsItem(R.drawable.icon_badge,"20230606","title","text"));
        newsItemList.add(new NewsItem(R.drawable.icon_badge,"20230606","title","text"));
        newsItemList.add(new NewsItem(R.drawable.icon_badge,"20230606","title","text"));
        newsItemList.add(new NewsItem(R.drawable.icon_badge,"20230606","title","text"));
        newsItemList.add(new NewsItem(R.drawable.icon_badge,"20230606","title","text"));
        newsItemList.add(new NewsItem(R.drawable.icon_badge,"20230606","title","text"));
    }

    private void setNewsRecyclerViewScrollEvent(RecyclerView recyclerView){
        View subTitleView = findViewById(R.id.main_subtitle_container);
        int startHeight = subTitleView.getLayoutParams().height;

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                scaleDownSubTitleView(recyclerView,subTitleView,startHeight);
            }
        });
    }

    private void scaleDownSubTitleView(RecyclerView recyclerView,View subTitleView, int startHeight){
        int scrollY = recyclerView.computeVerticalScrollOffset(); // 수직 스크롤 위치
        int currentHeight = startHeight - scrollY;
        subTitleView.getLayoutParams().height = Math.max(currentHeight, 1);
        subTitleView.requestLayout();
        subTitleView.invalidate();
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