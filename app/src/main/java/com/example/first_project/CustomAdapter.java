package com.example.first_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private ArrayList<ListItem> list;

    public CustomAdapter(ArrayList<ListItem> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.result.setText(list.get(position).getResult());
        holder.formula.setText(list.get(position).getFormula());

        holder.itemView.setTag(position);
//        holder.itemView.setOnClickListener();
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView formula, result;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.formula = itemView.findViewById(R.id.item_formula);
            this.result = itemView.findViewById(R.id.item_result);
        }
    }
}
