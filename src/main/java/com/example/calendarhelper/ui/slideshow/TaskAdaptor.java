package com.example.calendarhelper.ui.slideshow;
import com.example.calendarhelper.R;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdaptor extends RecyclerView.Adapter<TaskAdaptor.MyViewHolder>{
    private final List<Task> itemList;
    private OnItemDeletedListener listener;

    public TaskAdaptor(List<Task> itemList) {
        this.itemList = itemList;
    }

    public interface OnItemDeletedListener {
        void onItemDeleted(int position);
    }


    public TaskAdaptor(List<Task> itemList, OnItemDeletedListener listener) {
        this.itemList = itemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Task item = itemList.get(position);

        holder.className.setText(item.getTask());
        holder.classTime.setText(item.getDue());
        holder.classInstructor.setText(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        EditText className, classTime, classInstructor;

        Button deleteButton;
        public MyViewHolder(@NonNull View itemView, OnItemDeletedListener listener) {
            super(itemView);
            className = itemView.findViewById(R.id.itemLayoutName);
            classTime = itemView.findViewById(R.id.itemLayoutTime);
            classInstructor = itemView.findViewById(R.id.itemLayoutInstructor);

            deleteButton = itemView.findViewById(R.id.deleteClassButton);
            deleteButton.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && listener != null) {
                    listener.onItemDeleted(position);
                }
            });
    }
}}
