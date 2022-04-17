package com.example.first_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<MainData> itemList;
    private MainAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private EditText input_form;
    private Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv);
        input_form = findViewById(R.id.input_form);
        btn_add = findViewById(R.id.btn_add);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        itemList = new ArrayList<>();

        adapter = new MainAdapter(itemList);
        recyclerView.setAdapter(adapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = input_form.getText().toString().trim();

                if(text.length() < 1) {
                    Toast.makeText(getApplicationContext(), "글자를 입력해 주세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                MainData mainData = new MainData(
                        R.mipmap.ic_launcher,
                        text,
                        "RecycleView");
                itemList.add(mainData);
                adapter.notifyDataSetChanged();

                input_form.setText("");
            }
        });

        input_form.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(keyEvent.getAction() != KeyEvent.ACTION_UP) return false;
                switch (keyCode) {
                    case KeyEvent.KEYCODE_ENTER:
                        btn_add.callOnClick();
                }
                return false;
            }
        });
    }
}