package com.example.calendarhelper.ui.gallery;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calendarhelper.ui.exams.MyExamAdapter;
import com.example.calendarhelper.ui.gallery.MyAssignmentAdapter;
import com.example.calendarhelper.ui.home.MyAdapter;
import com.example.calendarhelper.R;


import java.util.List;

public class MyAssignmentAdapter extends RecyclerView.Adapter<MyAssignmentAdapter.MyViewHolder>{
    private final List<Assignment> itemList;
    private OnItemDeletedListener listener;

    public MyAssignmentAdapter(List<Assignment> itemList) {
        this.itemList = itemList;
    }

    public interface OnItemDeletedListener {
        void onItemDeleted(int position);
    }


    public MyAssignmentAdapter(List<Assignment> itemList, OnItemDeletedListener listener) {
        this.itemList = itemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new MyAssignmentAdapter.MyViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Assignment item = itemList.get(position);

        holder.className.setText(item.getTitle());
        holder.classTime.setText(item.getDue());
        holder.classInstructor.setText(item.getInstructor());
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
