package com.example.keyword_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.keyword_project.domain.KeywordUser;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.Response;

public class LogInActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginBtn = findViewById(R.id.login_btn);
        Button loginBtnGuest = findViewById(R.id.login_btn_guest);

        loginBtn.setOnClickListener(view -> {
            EditText editText = findViewById(R.id.login_textview);
            String userToken = editText.getText().toString();
            Log.d("my@@",userToken);
            callLogIn(userToken);
        });

        loginBtnGuest.setOnClickListener(view -> {
            callSignUp();
        });
    }

    private void moveToMainPage(KeywordUser user){
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("userData",user);
        Log.i("my@@",this+user.toString());
        startActivity(intent);
    }

    private void callSignUp(){
        //49.247.40.141:80/find-user


        String serverUrl = "http://49.247.40.141:80/user";

        GetApiTask task = new GetApiTask(result -> {
            TextView textView = findViewById(R.id.login_textview);
            Log.d("my@@",result);
            textView.setText(result);
            Toast.makeText(this,"ID 토큰이 생성되었습니다. 꼭 저장후 추후 로그인에 사용하세요",Toast.LENGTH_SHORT).show();
        });
        task.execute(serverUrl);

    }

    private void callLogIn(String userToken){
        String serverUrl = "http://49.247.40.141:80/logIn";

        String jsonData = "{\"userToken\":\""+userToken+"\"}";


        PostApiTask task = new PostApiTask((responseCode,result) -> {
            if(responseCode==200){
                moveToMainPage(ApiCallClient.parseUserData(result));
            }
            else{
                Toast.makeText(this,"토큰값이 유효하지 않습니다.",Toast.LENGTH_SHORT).show();
            }
        });

        task.execute(serverUrl,jsonData);

    }




}
