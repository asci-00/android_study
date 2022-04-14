package com.example.first_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<String> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_view);
        ArrayAdapter<String> adp =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        // list item view를 위한 adapter

        listView.setAdapter(adp);

        items.add("sample list item 1");
        items.add("sample list item 2");
        items.add("sample list item 3");
        items.add("sample list item 4");
        items.add("sample list item 5");

        adp.notifyDataSetChanged(); // data 적용(필수)
    }
}