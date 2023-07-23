package com.example.keyword_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.keyword_project.adapter.NewsItemAdapter;
import com.example.keyword_project.adapter.NewsKeywordAdapter;
import com.example.keyword_project.item.NewsItem;
import com.example.keyword_project.item.NewsKeyword;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NewsItemAdapter newsItemAdapter;
    private final List<NewsItem> newsData = new ArrayList<>();
    private final List<NewsItem> newsItemList = new ArrayList<>();
    private boolean isSettingBtnClicked = false;
    private boolean isSettingAlaramBtnClicked = false;
    private int clickedKeywordIndex = 0;

    private ArrayList<String> keywordList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FirebaseApp.initializeApp(this);

        callGetNewsDataApi();

        setNewsKeywordRecyclerView();
        setNewsRecyclerView();
        setSettingBtn();

        Intent serviceIntent = new Intent(this, NotifyService.class);
        startService(serviceIntent);

//        FirebaseMessaging.getInstance().getToken()
//                .addOnCompleteListener(new OnCompleteListener<String>() {
//                    @Override
//                    public void onComplete(@NonNull Task<String> task) {
//                        if (!task.isSuccessful()) {
//                            Log.w("my@@", "Fetching FCM registration token failed", task.getException());
//                            return;
//                        }
//
//                        // Get new FCM registration token
//                        String token = (String)task.getResult();
//
//                        // Log and toast
//                        String msg = getString(R.string.msg_token_fmt, token);
//                        Log.d("my@@", token);
//                        Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
//                    }
//                });

    }

    private void callGetNewsDataApi() {

    }

    private void setNewsKeywordRecyclerView() {
        List<NewsKeyword> dataList = getKeywordList();
        RecyclerView recyclerView = findViewById(R.id.main_keyword_recyclerview);
        NewsKeywordAdapter adapter = new NewsKeywordAdapter(dataList);

        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter.setOnItemClickListener((v, pos) -> {
            if (pos < dataList.size() - 1) {
                changeClickedKeyword(adapter, dataList, pos);
                renderNewsItem();
            } else {
                showAddKeywordPopup();
            }
        });
    }

    private void changeClickedKeyword(NewsKeywordAdapter adapter, List<NewsKeyword> dataList, int pos) {
        resetPreviousKeywordView(dataList);
        adapter.notifyItemChanged(clickedKeywordIndex);
        changeNextKeywordView(dataList, pos);
        adapter.notifyItemChanged(clickedKeywordIndex);
    }

    private void renderNewsItem() {
        newsItemList.clear();
        newsItemList.add(new NewsItem(R.drawable.icon_badge, "20230606", "title", "text"));
        newsItemAdapter.notifyDataSetChanged();
    }

    private void showAddKeywordPopup() {

    }

    private void resetPreviousKeywordView(List<NewsKeyword> dataList) {
        dataList.get(clickedKeywordIndex).setClicked(false);
    }

    private void changeNextKeywordView(List<NewsKeyword> dataList, int pos) {
        clickedKeywordIndex = pos;
        dataList.get(clickedKeywordIndex).setClicked(true);
    }

    private List<NewsKeyword> getKeywordList() {
        List<NewsKeyword> list = new ArrayList<>();

        list.add(new NewsKeyword("속보", true));
        list.add(new NewsKeyword("특징주", false));
        list.add(new NewsKeyword("김하성", false));
        list.add(new NewsKeyword("첼시", false));
        list.add(new NewsKeyword("LG", false));
        list.add(new NewsKeyword("LG", false));
        list.add(new NewsKeyword("LG", false));
        list.add(new NewsKeyword("LG", false));
        list.add(new NewsKeyword("LG", false));
        list.add(new NewsKeyword("LG", false));
        list.add(new NewsKeyword("LG", false));
        list.add(new NewsKeyword("LG", false));
        list.add(new NewsKeyword("LG", false));
        list.add(new NewsKeyword("LG", false));
        list.add(new NewsKeyword("LG", false));

        addPlusBtn(list);

        return list;
    }

    private void addPlusBtn(List<NewsKeyword> list) {
        list.add(new NewsKeyword("+", false));
    }

    private void setNewsRecyclerView() {
        setNewsList();

        RecyclerView recyclerView = findViewById(R.id.main_recyclerview);
        newsItemAdapter = new NewsItemAdapter(newsItemList, this);
        recyclerView.setAdapter(newsItemAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        setNewsRecyclerViewScrollEvent(recyclerView);
    }

    private void setNewsList() {
        newsItemList.add(new NewsItem(R.drawable.icon_badge, "20230606", "title", "text"));
        newsItemList.add(new NewsItem(R.drawable.icon_badge, "20230606", "title", "text"));
        newsItemList.add(new NewsItem(R.drawable.icon_badge, "20230606", "title", "text"));
        newsItemList.add(new NewsItem(R.drawable.icon_badge, "20230606", "title", "text"));
        newsItemList.add(new NewsItem(R.drawable.icon_badge, "20230606", "title", "text"));
        newsItemList.add(new NewsItem(R.drawable.icon_badge, "20230606", "title", "text"));
        newsItemList.add(new NewsItem(R.drawable.icon_badge, "20230606", "title", "text"));
        newsItemList.add(new NewsItem(R.drawable.icon_badge, "20230606", "title", "text"));
    }

    private void setNewsRecyclerViewScrollEvent(RecyclerView recyclerView) {
        View subTitleView = findViewById(R.id.main_subtitle_container);
        int startHeight = subTitleView.getLayoutParams().height;

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                scaleDownSubTitleView(recyclerView, subTitleView, startHeight);
            }
        });
    }

    private void scaleDownSubTitleView(RecyclerView recyclerView, View subTitleView, int startHeight) {
        int scrollY = recyclerView.computeVerticalScrollOffset(); // 수직 스크롤 위치
        int currentHeight = startHeight - scrollY;
        subTitleView.getLayoutParams().height = Math.max(currentHeight, 1);
        subTitleView.requestLayout();
        subTitleView.invalidate();
    }

    private void setSettingBtn() {
        ImageButton settingBtn = findViewById(R.id.main_setting_btn);

        settingBtn.setOnClickListener(v -> {
            switchSettingBtnImage(settingBtn);
            openPopUp();
        });
    }

    private void switchSettingBtnImage(ImageButton settingBtn) {
        settingBtn.setImageResource(isSettingBtnClicked ? R.drawable.icon_setting : R.drawable.icon_close);
        isSettingBtnClicked = !isSettingBtnClicked;
    }

    private void openPopUp() {
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

    private void setSubSettingBtn(AlertDialog dialog) {
        ImageButton settingBtn = dialog.findViewById(R.id.sub_alaram_btn);
        ImageButton settingBtn2 = dialog.findViewById(R.id.sub_clock_btn);

        ImageButton settingBtn4 = dialog.findViewById(R.id.sub_keyword_btn);

        settingBtn.setOnClickListener(v -> {
            switchalaramBtnImage(settingBtn);
        });

        settingBtn2.setOnClickListener(v -> {
            setalaramClock(settingBtn2);
        });

        settingBtn4.setOnClickListener(v ->{
            setkeyWord(settingBtn4);
        });

    }

    private void switchalaramBtnImage(ImageButton settingBtn) {
        settingBtn.setImageResource(isSettingAlaramBtnClicked ? R.drawable.icon_alarm : R.drawable.icon_noalarm);
        isSettingAlaramBtnClicked = !isSettingAlaramBtnClicked;
    }

    private void setalaramClock(ImageButton settingBtn) {
        // 팝업을 띄우기 위한 AlertDialog.Builder 생성
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // XML 레이아웃 파일을 인플레이트하여 팝업 내용으로 설정
        View dialogView = getLayoutInflater().inflate(R.layout.alaram_time, null);
        builder.setView(dialogView);

        // AlertDialog 생성
        AlertDialog dialog = builder.create();

        // TimePicker를 AlertDialog의 View에서 찾습니다.
        TimePicker timePicker_start = dialogView.findViewById(R.id.timePickerStart);
        TimePicker timePicker_end = dialogView.findViewById(R.id.timePickerEnd);
        timePicker_start.setIs24HourView(true);
        timePicker_end.setIs24HourView(true);

        // 팝업 표시
        dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL);
        dialog.show();
    }

    private void setkeyWord(ImageButton settingBtn) {
        Log.d("test", "bbbb");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.keyword_insert, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();

        final EditText keywordEditText = view.findViewById(R.id.keywordEditText);
        TextView keywordListTextView = view.findViewById(R.id.keywordListTextView);

        // keyword_insert.xml 레이아웃 내의 버튼을 찾도록 수정
        Button addKeywordButton = view.findViewById(R.id.addKeywordButton);
        addKeywordButton.setOnClickListener(v ->{
            String keyword = keywordEditText.getText().toString().trim();
            keywordList.add(keyword);
            updateKeywordListTextView(keywordListTextView);
            Log.d("test", "aaaa");
            Log.d("test", "keywordList:"+keywordList.get(0));
        });
    }

    private void updateKeywordListTextView(TextView keywordListTextView) {
        // 'keyword_insert.xml' 레이아웃에서 keywordListTextView를 찾도록 수정

        StringBuilder keywordText = new StringBuilder("추가된 키워드 목록:\n");
        for (String keyword : keywordList) {
            keywordText.append("- ").append(keyword).append("\n");
        }
        Log.d("test", "keywordList:"+keywordList);

        keywordListTextView.setText(keywordText);
    }
}