package com.example.calendarhelper.ui.exams;

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

import com.example.calendarhelper.ui.home.MyAdapter;
import com.example.calendarhelper.databinding.FragmentExamsBinding;

import java.util.ArrayList;
import java.util.List;

public class Exams extends Fragment {

    private FragmentExamsBinding binding;
    private RecyclerView recyclerView;
    private List<Exam> itemList;
    private MyExamAdapter myAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentExamsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        initializeItemList();
        recyclerView = binding.examsRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        myAdapter = new MyExamAdapter(itemList, position -> {
            itemList.remove(position);
            myAdapter.notifyItemRemoved(position);
            myAdapter.notifyItemRangeChanged(position, itemList.size());
        });
        recyclerView.setAdapter(myAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(getActivity()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        return view;
    }
    private void initializeItemList() {
        itemList = new ArrayList<>();
        itemList.add(new Exam("1332 Exam1", "5:00 PM", "Howey L4"));
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.addExamButton.setOnClickListener(v -> saveClassInfo());
    }
    private void saveClassInfo() {
        String className = binding.examNameEditText.getText().toString();
        String classTime = binding.examTimeEditText.getText().toString();
        String classInstructor = binding.examLocationEditText.getText().toString();
        if (className.isEmpty() || classTime.isEmpty() || classInstructor.isEmpty()) {
            Toast.makeText(getActivity(), "Fill out all details!", Toast.LENGTH_SHORT).show();
            return;
        }
        Exam newItem = new Exam(className, classTime, classInstructor);
        itemList.add(newItem);
        myAdapter.notifyItemInserted(itemList.size() - 1);
        recyclerView.scrollToPosition(itemList.size() - 1);
        Toast.makeText(getActivity(), "Exam added: " + className, Toast.LENGTH_SHORT).show();
        binding.examNameEditText.setText("");
        binding.examTimeEditText.setText("");
        binding.examLocationEditText.setText("");
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}