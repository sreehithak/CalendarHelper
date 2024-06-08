package com.example.calendarhelper.ui.home;

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

import com.example.calendarhelper.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private List<Item> itemList;
    private MyAdapter myAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        initializeItemList();

        recyclerView = binding.classesRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        // Instantiate MyAdapter once with both itemList and the deletion listener
        myAdapter = new MyAdapter(itemList, position -> {
            // Remove the item
            itemList.remove(position);
            // Notify the adapter
            myAdapter.notifyItemRemoved(position);
            // Optional: Notify for range change if needed
            myAdapter.notifyItemRangeChanged(position, itemList.size());
            recyclerView.scrollToPosition(itemList.size()-1);
        });
        recyclerView.setAdapter(myAdapter);

        // Optionally, add decoration between RecyclerView items
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(getActivity()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        return view;
    }

    private void initializeItemList() {
        // Initialize your items list here
        itemList = new ArrayList<>();
        // Add example item
        itemList.add(new Item("CS 1301", "8:25 AM", "Prof. McDaniel"));
        // Add more items as needed
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.addClassButton.setOnClickListener(v -> saveClassInfo());
    }

    private void saveClassInfo() {
        String className = binding.classNameEditText.getText().toString();
        String classTime = binding.classTimeEditText.getText().toString();
        String classInstructor = binding.classInstructorEditText.getText().toString();

        if (className.isEmpty() || classTime.isEmpty() || classInstructor.isEmpty()) {
            Toast.makeText(getActivity(), "You cannot make a non-existent class!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create new Item object
        Item newItem = new Item(className, classTime, classInstructor);

        // Add to your itemList
        itemList.add(newItem);

        // Notify the adapter that an item has been inserted
        myAdapter.notifyItemInserted(itemList.size() - 1);

        // Optionally, scroll to the newly added item
        recyclerView.scrollToPosition(itemList.size() - 1);

        // Show confirmation
        Toast.makeText(getActivity(), "Class added: " + className, Toast.LENGTH_SHORT).show();

        // Clear the input fields
        binding.classNameEditText.setText("");
        binding.classTimeEditText.setText("");
        binding.classInstructorEditText.setText("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}