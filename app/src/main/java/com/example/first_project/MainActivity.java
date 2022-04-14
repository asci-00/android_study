package com.example.first_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button move_btn;
    private EditText et_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_test = findViewById(R.id.et_text);
        move_btn = findViewById(R.id.btn_move);

        move_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                // 현재 Activity 와 이동할 Activity 입력

                intent.putExtra("str", et_test.getText().toString()); // intent에 data 전달

                startActivity(intent); // Activity 이동 함수
            }
        });
    }
}