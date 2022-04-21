package com.example.first_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class HistoryFragment extends Fragment {
    private ArrayList<ListItem> list;
    private CustomAdapter customAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("History", "Call onCreateView");

        View view = inflater.inflate(R.layout.fragment_history, container, false);

        recyclerView = view.findViewById(R.id.recycle_view);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        list = new ArrayList<>();

        customAdapter = new CustomAdapter(list);
        recyclerView.setAdapter(customAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("History", "Call onStart");
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.i("History", "Call onResume");
        StringBuffer buf = new StringBuffer();
        try {
            FileInputStream fis = getActivity().openFileInput("history");
            Scanner reader = new Scanner(new InputStreamReader(fis));

            while(reader.hasNextLine()) {
                list.add(new ListItem(reader.nextLine(), reader.nextLine()));
            }

            customAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}