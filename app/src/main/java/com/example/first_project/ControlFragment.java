package com.example.first_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ControlFragment extends Fragment {
    private final String[] btn_id = {
        "1", "2", "3", "4",
        "5", "6", "7", "8",
        "9", "0", "plus", "minus",
        "dot", "equal", "clear",
        "bracket", "mod", "div", "multi"
    };
    private View.OnClickListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_control, container, false);
        for(String id: btn_id)
            view.findViewById(
                    getActivity()
                            .getResources()
                            .getIdentifier("btn_" + id, "id", getActivity().getPackageName())
            ).setOnClickListener(listener);

        return view;
    }
    public void setButtonClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }
}