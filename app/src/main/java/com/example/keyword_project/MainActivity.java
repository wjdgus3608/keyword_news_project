package com.example.keyword_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
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

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private boolean isSettingBtnClicked = false;
    private boolean isSettingAlaramBtnClicked = false;
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

            clickedKeywordIndex = pos;

            v.setBackgroundTintList(blueBackGround);
            TextView tx = v.findViewById(R.id.item_keyword_text_view);
            tx.setTextColor(blueText);
        });

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
            openPopUp();
        });
    }

    private void switchSettingBtnImage(ImageButton settingBtn){
        settingBtn.setImageResource(isSettingBtnClicked ? R.drawable.icon_setting : R.drawable.icon_close);
        isSettingBtnClicked = !isSettingBtnClicked;
    }

    private void openPopUp(){
        // 팝업을 띄우기 위한 AlertDialog.Builder 생성
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        // XML 레이아웃 파일을 인플레이트하여 팝업 내용으로 설정
        builder.setView(getLayoutInflater().inflate(R.layout.popup_layout, null));

        // AlertDialog 생성 및 팝업 표시
        AlertDialog dialog = builder.create();
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.show();

        setSubSettingBtn(dialog);
    }

    private void setSubSettingBtn(AlertDialog dialog){
        ImageButton settingBtn = dialog.findViewById(R.id.sub_alaram_btn);

        settingBtn.setOnClickListener(v -> {
            switchalaramBtnImage(settingBtn);
        });
    }
    private void switchalaramBtnImage(ImageButton settingBtn){
        settingBtn.setImageResource(isSettingAlaramBtnClicked ? R.drawable.icon_alarm : R.drawable.icon_noalarm);
        isSettingAlaramBtnClicked = !isSettingAlaramBtnClicked;
    }
}