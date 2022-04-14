package com.example.first_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    private TextView tv_sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        tv_sub = findViewById(R.id.tv_sub);

        Intent intent = getIntent();    // 이전 View 값을 받아옴
        String str = intent.getStringExtra("str"); // 이전 View에서 전달한 String 값을 받아옴

        tv_sub.setText(str);
    }
}