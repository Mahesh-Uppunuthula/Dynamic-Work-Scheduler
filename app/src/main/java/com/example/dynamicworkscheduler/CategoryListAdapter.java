package com.example.dynamicworkscheduler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {

    private List<String> category_names;

    public CategoryListAdapter(List<String> category_names) {
        this.category_names = category_names;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.catrgory_list_item,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String text = category_names.get(position);
        holder.setData(text);
        holder.itemView.setOnClickListener(view -> {
            Toast.makeText(view.getContext(), category_names.get(position), Toast.LENGTH_SHORT).show();

        });


    }

    @Override
    public int getItemCount() {
        return category_names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView cat_tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cat_tv = itemView.findViewById(R.id.category_TV);
        }

        public void setData(String text) {
            cat_tv.setText(text);
        }
    }



}