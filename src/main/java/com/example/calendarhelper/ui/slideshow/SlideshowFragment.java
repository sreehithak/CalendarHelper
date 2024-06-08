package com.example.calendarhelper.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calendarhelper.databinding.FragmentSlideshowBinding;

import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    private RecyclerView recyclerView;
    private List<Task> itemList;
    private TaskAdaptor taskAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        initializeItemList();

        recyclerView = binding.tasksRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        taskAdapter = new TaskAdaptor(itemList, position -> {
            itemList.remove(position);
            taskAdapter.notifyItemRemoved(position);
            taskAdapter.notifyItemRangeChanged(position, itemList.size());
        });
        recyclerView.setAdapter(taskAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(getActivity()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        return view;
    }

    private void initializeItemList() {
        itemList = new ArrayList<>();
        itemList.add(new Task("Project 1",  "Scheduler App"));
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.addTaskButton.setOnClickListener(v -> saveClassInfo());
    }

    private void saveClassInfo() {
        String className = binding.taskNameEditText.getText().toString();
        String classInstructor = binding.taskDescriptionEditText.getText().toString();

        if (className.isEmpty() || classInstructor.isEmpty()) {
            Toast.makeText(getActivity(), "You cannot add an incomplete task!", Toast.LENGTH_SHORT).show();
            return;
        }
        Task newItem = new Task(className, classInstructor);
        itemList.add(newItem);
        taskAdapter.notifyItemInserted(itemList.size() - 1);
        recyclerView.scrollToPosition(itemList.size() - 1);
        Toast.makeText(getActivity(), "Task added: " + className, Toast.LENGTH_SHORT).show();
        binding.taskNameEditText.setText("");
        binding.taskDescriptionEditText.setText("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}