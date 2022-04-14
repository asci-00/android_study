package com.example.first_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText input_form;
    private Button btn_test;
    private TextView saved_text;
    private String shared = "", text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_form = findViewById(R.id.input_form);   // xml에서 해당 id 값을 가진 view를 가져옴
        btn_test = findViewById(R.id.btn_test);
        saved_text = findViewById(R.id.saved_text);

        SharedPreferences sharedPreferences = getSharedPreferences(shared, 0);
        String value = sharedPreferences.getString("key", "");
        saved_text.setText(value);

        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = input_form.getText().toString();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences sharedPreferences = getSharedPreferences(shared, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("key", text);
        editor.commit();
    }
}