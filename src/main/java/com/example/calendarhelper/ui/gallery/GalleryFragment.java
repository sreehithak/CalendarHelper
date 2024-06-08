package com.example.calendarhelper.ui.gallery;

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

import com.example.calendarhelper.databinding.FragmentGalleryBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class GalleryFragment extends Fragment {
    private FragmentGalleryBinding binding;
    private RecyclerView recyclerView;
    private List<Assignment> itemList;
    private MyAssignmentAdapter myAssignmentAdapter;

    private boolean courseSort = true;
    private boolean dateSort = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        initializeItemList();
        recyclerView = binding.assignmentRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        myAssignmentAdapter = new MyAssignmentAdapter(itemList, position -> {
            itemList.remove(position);
            myAssignmentAdapter.notifyItemRemoved(position);
            myAssignmentAdapter.notifyItemRangeChanged(position, itemList.size());
        });
        recyclerView.setAdapter(myAssignmentAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(getActivity()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        return view;
    }
    private void initializeItemList() {
        itemList = new ArrayList<>();
        itemList.add(new Assignment("Article Critique", "2/7", "PSYC 2210"));
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.addAssignmentButton.setOnClickListener(v -> saveClassInfo());
        binding.sortByCourseButton.setOnClickListener(v -> sortByCourse());
        binding.sortByDateButton.setOnClickListener(v -> sortByDate());
    }

    public void sortByCourse() {
        itemList.sort(new Comparator<Assignment>() {
            @Override
            public int compare(Assignment x, Assignment y) {
                dateSort = !dateSort;
                return dateSort ? x.getTitle().compareToIgnoreCase(y.getTitle()) :
                        y.getTitle().compareToIgnoreCase(x.getTitle());
            }
        });
        displayAssignments();
    }

    private void sortByDate() {
        itemList.sort(new Comparator<Assignment>() {
            @Override
            public int compare(Assignment x, Assignment y) {
                courseSort = !courseSort;
                return courseSort ? x.getDue().compareTo(y.getDue()) :
                        y.getDue().compareTo(x.getDue());
            }
        });
        displayAssignments();
    }

    private void saveClassInfo() {
        String className = binding.editTextCourseName.getText().toString();
        String classTime = binding.editTextDueDate.getText().toString();
        String classInstructor = binding.editTextClass.getText().toString();

        if (className.isEmpty() || classTime.isEmpty() || classInstructor.isEmpty()) {
            Toast.makeText(getActivity(), "Please fill out all details!", Toast.LENGTH_SHORT).show();
            return;
        }
        Assignment newItem = new Assignment(className, classTime, classInstructor);
        itemList.add(newItem);
        myAssignmentAdapter.notifyItemInserted(itemList.size() - 1);
        recyclerView.scrollToPosition(itemList.size() - 1);
        Toast.makeText(getActivity(), "Assignment added: " + className, Toast.LENGTH_SHORT).show();
        binding.editTextCourseName.setText("");
        binding.editTextDueDate.setText("");
        binding.editTextClass.setText("");
    }
    private void displayAssignments() {
        List<Assignment> copyList = new ArrayList<>(itemList);
        itemList.clear();
        itemList.addAll(copyList);
        myAssignmentAdapter.notifyDataSetChanged();
        recyclerView.scrollToPosition(itemList.size()-1);
        }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}