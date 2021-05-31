package com.example.androidfeedback.ui.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.enrollment.EnrollmentFragment;


import java.util.ArrayList;

public class AssignmentFragment extends Fragment {
    private ArrayList<AssignmentModel> listAssignment ;
    private RecyclerView recyclerAssignment;
    private AssignmentAdapter assignmentAdapter ;
    private boolean allowRefresh = false ;
    private Button btnAdd;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FrameLayout fl = (FrameLayout) getActivity().findViewById(this.getId());
        fl.removeAllViews();
        View root = inflater.inflate(R.layout.fragment_assignment, container, false);
        listAssignment = new ArrayList<AssignmentModel>();
        createAssignmentList();
        recyclerAssignment = root.findViewById(R.id.recyclerAssignment);


        //Create Assignment Adapter
        assignmentAdapter = new AssignmentAdapter(getActivity().getApplicationContext(),listAssignment);

        btnAdd = root.findViewById(R.id.btnAddAssignment);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),AddAssignment.class);
                startActivity(intent);
            }
        });
        //Set adapter to RecyclerView
        recyclerAssignment.setHasFixedSize(true);
        recyclerAssignment.setAdapter(assignmentAdapter);
        recyclerAssignment.setLayoutManager(new LinearLayoutManager(root.getContext()));

        return root;

    }

    private void createAssignmentList() {
        listAssignment.add(new AssignmentModel(1,"test","Class1","Trainer 1","CL1MIT160655877"));
    }

    @Override
    public void onPause() {
        super.onPause();
        if (!allowRefresh){
            allowRefresh = true;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (allowRefresh) {
            FrameLayout fl = (FrameLayout) getActivity().findViewById(this.getId());
            fl.removeAllViews();
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(this.getId(),new EnrollmentFragment()).commitAllowingStateLoss();
        }
    }
}