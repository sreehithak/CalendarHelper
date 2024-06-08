package com.example.calendarhelper.ui.exams;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calendarhelper.R;

import java.util.List;

public class MyExamAdapter extends RecyclerView.Adapter<MyExamAdapter.MyViewHolder> {
    private final List<Exam> itemList;
    private OnItemDeletedListener listener;

    public MyExamAdapter(List<Exam> itemList, OnItemDeletedListener listener) {
        this.itemList = itemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new MyExamAdapter.MyViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Exam item = itemList.get(position);

        // Bind data to ViewHolder views
        holder.className.setText(item.getName());
        holder.classTime.setText(item.getTime());
        holder.classInstructor.setText(item.getLocation());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    // Method to update the dataset and refresh the adapter
    public void updateList(List<Exam> newList) {
        itemList.clear();
        itemList.addAll(newList);
        notifyDataSetChanged();
    }

    // ViewHolder class
    public class MyViewHolder extends RecyclerView.ViewHolder {
        EditText className, classTime, classInstructor;
        Button deleteButton;

        public MyViewHolder(@NonNull View itemView, OnItemDeletedListener listener) {
            super(itemView);
            // Initialize views
            className = itemView.findViewById(R.id.itemLayoutName);
            classTime = itemView.findViewById(R.id.itemLayoutTime);
            classInstructor = itemView.findViewById(R.id.itemLayoutInstructor);
            deleteButton = itemView.findViewById(R.id.deleteClassButton);

            // Set onClickListener for deleteButton
            deleteButton.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && listener != null) {
                    listener.onItemDeleted(position);
                }
            });
        }
    }

    // Interface for handling item deletion events
    public interface OnItemDeletedListener {
        void onItemDeleted(int position);
    }
}
