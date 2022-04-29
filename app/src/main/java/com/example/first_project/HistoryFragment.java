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

import static com.example.first_project.utils.RecyclerView.*;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class HistoryFragment extends Fragment {
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

        listItems = new ArrayList<>();

        customAdapter = new CustomAdapter(listItems, (clickedResult) -> ((MainActivity)getActivity()).setState(clickedResult));
        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().deleteFile("history");

                listItems.clear();
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

        try {
            FileInputStream fis = getActivity().openFileInput("history");
            Scanner reader = new Scanner(new InputStreamReader(fis));

            while(reader.hasNextLine()) {
                listItems.add(new ListItem(reader.nextLine(), reader.nextLine()));
            }

            customAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}