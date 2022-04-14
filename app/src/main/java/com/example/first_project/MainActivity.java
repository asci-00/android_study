package com.example.first_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                long strText = adapterView.getItemIdAtPosition(i);

                Toast.makeText(getApplicationContext(), String.valueOf(strText), Toast.LENGTH_LONG).show();
            }
        });
        items.add("sample list item 1");
        items.add("sample list item 2");
        items.add("sample list item 3");
        items.add("sample list item 4");
        items.add("sample list item 5");

        adp.notifyDataSetChanged(); // data 적용(필수)
    }
}