package com.example.androidfeedback.ui.assignment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.androidfeedback.R;


import java.util.ArrayList;

public class AssignmentFragment extends Fragment {
    private ArrayList<AssignmentModel> listAssignment ;
    private RecyclerView recyclerAssignment;
    private AssignmentAdapter assignmentAdapter ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_assignment, container, false);
        listAssignment = new ArrayList<AssignmentModel>();
        createAssignmentList();
        recyclerAssignment = root.findViewById(R.id.recyclerAssignment);

        //Create Assignment Adapter
        assignmentAdapter = new AssignmentAdapter(getActivity().getApplicationContext(),listAssignment);

        //Set adapter to RecyclerView
        recyclerAssignment.setHasFixedSize(true);
        recyclerAssignment.setAdapter(assignmentAdapter);
        recyclerAssignment.setLayoutManager(new LinearLayoutManager(root.getContext()));

        return root;

    }

    private void createAssignmentList() {
        listAssignment.add(new AssignmentModel(1,"test","Class1","Trainer 1","CL1MIT160655877"));
    }
}