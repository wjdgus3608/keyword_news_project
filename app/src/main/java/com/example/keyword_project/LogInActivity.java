package com.example.keyword_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LogInActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginBtn = findViewById(R.id.login_btn);
        Button loginBtnGuest = findViewById(R.id.login_btn_guest);

        loginBtn.setOnClickListener(view -> {
            moveToMainPage();
        });

        loginBtnGuest.setOnClickListener(view -> {
            moveToMainPage();
        });
    }

    private void moveToMainPage(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
