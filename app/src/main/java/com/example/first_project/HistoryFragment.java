package com.example.first_project;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

        customAdapter = new CustomAdapter(list, (clickedResult) -> ((MainActivity)getActivity()).onHistoryClick(clickedResult));
        reset_btn.setOnClickListener(view1 -> {
            getActivity().deleteFile("history");

            list.clear();
            customAdapter.notifyDataSetChanged();
        });

        recyclerView.setAdapter(customAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        StringBuffer buf = new StringBuffer();
        String[] histories;

        try {
            FileInputStream fis = getActivity().openFileInput("history");
            Scanner reader = new Scanner(new InputStreamReader(fis));

            while(reader.hasNextLine()) {
                histories = reader.nextLine().split("=");
                if(histories.length < 2) break;

                list.add(new ListItem(histories[0], histories[1]));
            }

            customAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}