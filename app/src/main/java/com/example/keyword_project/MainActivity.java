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
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.keyword_project.adapter.NewExcludeKeywordAdapter;
import com.example.keyword_project.adapter.NewIncludeKeywordAdapter;
import com.example.keyword_project.adapter.NewsItemAdapter;
import com.example.keyword_project.adapter.NewsKeywordAdapter;
import com.example.keyword_project.domain.KeywordUser;
import com.example.keyword_project.item.NewsItem;
import com.example.keyword_project.item.NewsKeyword;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.messaging.FirebaseMessaging;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private NewsItemAdapter newsItemAdapter;
    private final List<NewsItem> newsData = new ArrayList<>();
    private final List<NewsItem> newsItemList = new ArrayList<>();
    private boolean isSettingBtnClicked = false;
    private boolean isSettingAlaramBtnClicked = false;
    private int clickedKeywordIndex = 0;

    private ArrayList<String> keywordList = new ArrayList<>();

    private NewIncludeKeywordAdapter newIncludeKeywordAdapter;
    private NewExcludeKeywordAdapter newExcludeKeywordAdapter;
    private NewExcludeKeywordAdapter newMainKeywordAdapter;
    private List<String> keywordIncludeDataList = new ArrayList<>();
    private List<String> keywordExcludeDataList = new ArrayList<>();
    private List<String> keywordMainDataList = new ArrayList<>();
    private boolean isPopupShown = false;
    private int setCycleTimeIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        KeywordUser userData = (KeywordUser) intent.getSerializableExtra("userData");
        Log.i("my@@",this+userData.toString());
        GlobalData.loginUser = userData;
        GlobalData.mainContext = this;

        FirebaseApp.initializeApp(this);


        callGetNewsDataApi();

        setNewsKeywordRecyclerView();
        setNewsRecyclerView();
        setSettingBtn();

        Intent serviceIntent = new Intent(this, NotifyService.class);
        startService(serviceIntent);


        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.i("my@@", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = (String)task.getResult();

                        // Log and toast
                        String msg = getString(R.string.msg_token_fmt, token);
                        Map<String,Object> map = new HashMap<>();
                        map.put("fcmToken",token);
                        ApiCallClient.callUpdateSetting(GlobalData.mainContext,-1,map);
                        Log.i("my@@", token);
                    }

                    
                });

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
                GlobalData.clickedKeyword = dataList.get(pos).getKeyword();
                changeClickedKeyword(adapter, dataList, pos);
                renderNewsItem();

            } else {
                showAddKeywordPopup(dataList);
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

    private void showAddKeywordPopup(List<NewsKeyword> dataList) {
        setMainkeyWord(dataList);
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

        for (String s : keywordMainDataList){
            list.add(new NewsKeyword(s, false));
        }

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
//            switchSettingBtnImage(settingBtn);
            if (!isPopupShown) {
                openPopUp();
            }
        });
    }

    private void switchSettingBtnImage(ImageButton settingBtn) {
        settingBtn.setImageResource(isSettingBtnClicked ? R.drawable.icon_setting : R.drawable.icon_close);
        isSettingBtnClicked = !isSettingBtnClicked;
    }

    private void openPopUp() {
        if (!isPopupShown) {
            // 팝업을 띄우기 위한 AlertDialog.Builder 생성
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            // XML 레이아웃 파일을 인플레이트하여 팝업 내용으로 설정
            builder.setView(getLayoutInflater().inflate(R.layout.popup_layout, null));

            // AlertDialog 생성 및 팝업 표시
            AlertDialog dialog = builder.create();
            dialog.getWindow().setGravity(Gravity.BOTTOM);
            WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
            layoutParams.y = 150;
            dialog.getWindow().setAttributes(layoutParams);
            dialog.show();

            loadUserSetting(dialog);


            // 팝업이 띄워져 있다고 표시
            isPopupShown = true;

            setSubSettingBtn(dialog);

            // AlertDialog의 dismiss 리스너를 설정하여 팝업이 닫힐 때 isPopupShown 값을 false로 변경
            dialog.setOnDismissListener(dialogInterface -> {
                isPopupShown = false;
            });
        }
    }

    private void loadUserSetting(AlertDialog dialog){
        //알림 정보 로드
        isSettingAlaramBtnClicked = !GlobalData.loginUser.isAlarmAllowed();
        ImageButton settingBtn = dialog.findViewById(R.id.sub_alaram_btn);
        settingBtn.setImageResource(!isSettingAlaramBtnClicked ? R.drawable.icon_alarm : R.drawable.icon_noalarm);
        TextView textView = dialog.findViewById((R.id.sub_alaram_text2));
        textView.setText(!isSettingAlaramBtnClicked ? "OFF" : "ON");

        //시간 정보 로드
        TextView clockText2 = dialog.findViewById((R.id.sub_clock_text2));
        TextView clockText4 = dialog.findViewById((R.id.sub_clock_text4));
        String time = GlobalData.loginUser.getFetchTime();
        Log.i("my@@",time);
        clockText2.setText(time.substring(0,2)+":"+time.substring(2,4));
        clockText4.setText(time.substring(4,6)+":"+time.substring(6,8));
        //주기 정보 로드
        String cycleTime = GlobalData.loginUser.getFetchInterval();
        TextView textView3 =  dialog.findViewById((R.id.sub_cycle_text2));
        String cycleText = "";
        switch (cycleTime){
            case "0":
                cycleText = "실시간";
                setCycleTimeIndex = 0;
                break;
            case "5":
                cycleText = "5분";
                setCycleTimeIndex = 1;
                break;
            case "30":
                cycleText = "30분";
                setCycleTimeIndex = 2;
                break;
            case "60":
                cycleText = "1시간";
                setCycleTimeIndex = 3;
                break;
            case "120":
                setCycleTimeIndex = 4;
                cycleText = "2시간";
                break;
        }
        textView3.setText(cycleText);
        //포함키워드 로드

        //제외키워드 로드


        Log.i("my@@","load "+isSettingAlaramBtnClicked);
    }

    private void setSubSettingBtn(AlertDialog dialog) {
        ImageButton settingBtn = dialog.findViewById(R.id.sub_alaram_btn);
        ImageButton settingBtn2 = dialog.findViewById(R.id.sub_clock_btn);
        ImageButton settingBtn3 = dialog.findViewById(R.id.sub_cycle_btn);
        ImageButton settingBtn4 = dialog.findViewById(R.id.sub_keyword_btn);
        ImageButton settingBtn5 = dialog.findViewById(R.id.sub_except_keyword_btn);

        TextView textView1 =  dialog.findViewById((R.id.sub_alaram_text2));
        TextView textView2 =  dialog.findViewById((R.id.sub_clock_text2));
        TextView textView24=  dialog.findViewById((R.id.sub_clock_text4));

        TextView textView3 =  dialog.findViewById((R.id.sub_cycle_text2));
        TextView textView4 =  dialog.findViewById((R.id.sub_keyword_text2));
        TextView textView5 =  dialog.findViewById((R.id.sub_except_keyword_text2));

        settingBtn.setOnClickListener(v -> {
            switchalaramBtnImage(settingBtn,textView1);
            HashMap<String,Object> map = new HashMap<>();
            map.put("isSettingAlaramBtnClicked",isSettingAlaramBtnClicked);
            ApiCallClient.callUpdateSetting(this, 0, map);
        });

        settingBtn2.setOnClickListener(v -> {
            setalaramClock(settingBtn2, textView2, textView24);
        });

        settingBtn3.setOnClickListener(v -> {
            setCycleTime(settingBtn3, textView3);
        });

        settingBtn4.setOnClickListener(v ->{
            setIncludekeyWord();
        });

        settingBtn5.setOnClickListener(v ->{
            setExcludekeyWord();
        });
    }

    private void setCycleTime(ImageButton settingBtn, TextView textView){
        setCycleTimeIndex++;
        if (setCycleTimeIndex == 5) setCycleTimeIndex = 0;
        String text = "";
        switch(setCycleTimeIndex){
            case 0: text = "실시간";break;
            case 1: text = "5분";break;
            case 2: text = "30분";break;
            case 3: text = "1시간";break;
            case 4: text = "2시간";break;
        }
        textView.setText(text);
    }

    private void switchalaramBtnImage(ImageButton settingBtn, TextView textView) {
        isSettingAlaramBtnClicked = !isSettingAlaramBtnClicked;
        settingBtn.setImageResource(!isSettingAlaramBtnClicked ? R.drawable.icon_alarm : R.drawable.icon_noalarm);
        textView.setText(isSettingAlaramBtnClicked ? "OFF" : "ON");
    }
    private void setalaramClock(ImageButton settingBtn, TextView clockText2, TextView clockText4) {
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


        timePicker_start.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
                clockText2.setText(hour+":"+minute);
            }
        });

        timePicker_end.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
                clockText4.setText(hour+":"+minute);
            }
        });

        Button alarmChoiceButton = dialogView.findViewById(R.id.alaramChoice);
        alarmChoiceButton.setOnClickListener(v -> {
            dialog.dismiss();
        });

        // 팝업 표시
        dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL);
        dialog.show();
    }

    private void setIncludekeyWord() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.keyword_insert, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();

        RecyclerView recyclerView = view.findViewById(R.id.keyword_include_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 기존의 어댑터를 사용하도록 수정
        if (newIncludeKeywordAdapter == null){
        newIncludeKeywordAdapter = new NewIncludeKeywordAdapter(keywordIncludeDataList);}
        recyclerView.setAdapter(newIncludeKeywordAdapter);

        //기존데이터 있으면 넣기.
        //newIncludeKeywordAdapter.addItem("포함키워드 테스트1");
        //newIncludeKeywordAdapter.addItem("포함키워드 테스트2");

        // 뷰에서 addKeywordButton을 찾음
        Button addKeywordButton = view.findViewById(R.id.addKeywordButton);
        EditText keywordEditText = view.findViewById(R.id.keywordEditText);

        addKeywordButton.setOnClickListener(v -> {
            // 버튼 클릭시 dataList에 값 추가하고 RecyclerView에 업데이트
            String newKeyword = keywordEditText.getText().toString().trim();
            if (!newKeyword.isEmpty()) {
                addDataToList(newKeyword);
                Map<String,Object> map = new HashMap<>();
                map.put("keyword",GlobalData.clickedKeyword);
                map.put("addContainKeyword",newKeyword);
                ApiCallClient.callUpdateSetting(this,3,map);
                keywordEditText.setText("");
            }
        });

    }

    private void addDataToList(String newData) {
        newIncludeKeywordAdapter.addItem(newData);
    }

    private void setExcludekeyWord() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.keyword_exclude, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();

        RecyclerView recyclerView = view.findViewById(R.id.keyword_exclude_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 기존의 어댑터를 사용하도록 수정
        if (newExcludeKeywordAdapter == null){
        newExcludeKeywordAdapter = new NewExcludeKeywordAdapter(keywordExcludeDataList);}

        recyclerView.setAdapter(newExcludeKeywordAdapter);

        //기존데이터 있으면 넣기.
        //newExcludeKeywordAdapter.addItem("제외키워드 테스트1");
        //newExcludeKeywordAdapter.addItem("제외키워드 테스트2");

        // 뷰에서 addKeywordButton을 찾음
        Button addKeywordButton = view.findViewById(R.id.excludeAddKeywordButton);
        EditText keywordEditText = view.findViewById(R.id.excludeKeywordEditText);

        addKeywordButton.setOnClickListener(v -> {
            // 버튼 클릭시 dataList에 값 추가하고 RecyclerView에 업데이트
            String newKeyword = keywordEditText.getText().toString().trim();
            if (!newKeyword.isEmpty()) {
                addDataToExcludeList(newKeyword);
                Map<String,Object> map = new HashMap<>();
                map.put("keyword",GlobalData.clickedKeyword);
                map.put("addExcludeKeyword",newKeyword);
                ApiCallClient.callUpdateSetting(this,5,map);
                keywordEditText.setText("");
            }
        });

    }

    private void addDataToExcludeList(String newData) {
        newExcludeKeywordAdapter.addItem(newData);
    }


    private void setMainkeyWord(List<NewsKeyword> dataList) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.keyword_main, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();

        RecyclerView recyclerView = view.findViewById(R.id.keyword_main_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 기존의 어댑터를 사용하도록 수정
        keywordMainDataList.clear();
        newMainKeywordAdapter = new NewExcludeKeywordAdapter(keywordMainDataList);
        recyclerView.setAdapter(newMainKeywordAdapter);

        //기존데이터 있으면 넣기.
        for (int i = 0 ; i < dataList.size()-1 ; i++){
            newMainKeywordAdapter.addItem(dataList.get(i).getKeyword());
        }

        // 뷰에서 addKeywordButton을 찾음
        Button addKeywordButton = view.findViewById(R.id.mainAddKeywordButton);
        EditText keywordEditText = view.findViewById(R.id.mainKeywordEditText);

        addKeywordButton.setOnClickListener(v -> {
            // 버튼 클릭시 dataList에 값 추가하고 RecyclerView에 업데이트
            String newKeyword = keywordEditText.getText().toString().trim();
            if (!newKeyword.isEmpty()) {
                addDataToMainList(newKeyword);
                Map<String,Object> map = new HashMap<>();
                ApiCallClient.callUpdateSetting(this,5,map);
                keywordEditText.setText("");
            }
        });

        dialog.setOnDismissListener(dialogInterface -> {
            setNewsKeywordRecyclerView();
        });
    }

    private void addDataToMainList(String newData) {
        newMainKeywordAdapter.addItem(newData);
    }

}