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
import android.widget.Button;

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
    private Button reset_btn;
    private LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("History", "Call onCreateView");

        View view = inflater.inflate(R.layout.fragment_history, container, false);

        recyclerView = view.findViewById(R.id.recycle_view);
        reset_btn = view.findViewById(R.id.reset_btn);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        list = new ArrayList<>();

        customAdapter = new CustomAdapter(list, (clickedResult) -> ((MainActivity)getActivity()).setState(clickedResult));
        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().deleteFile("history");

                list = new ArrayList<>();
                customAdapter.notifyDataSetChanged();
            }
        });

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