package com.example.androidfeedback.ui.enrollment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;

import java.util.ArrayList;

public class EnrollmentFragment extends Fragment {
    private RecyclerView recyclerEnrollmentView;
    EnrollmentAdapter enrollmentAdapter;
    ArrayList<EnrollmentViewModel> enrollmentList;
    Button btnAdd;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_enrollment,null);
        enrollmentList = new ArrayList<EnrollmentViewModel>();
        recyclerEnrollmentView = root.findViewById(R.id.recyclerEnrollmentView);

        btnAdd = root.findViewById(R.id.btnAddEnrollment);
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), AddEnrollment.class);
//                startActivity(intent);
//            }
//        });

        EnrollmentViewModel enrollment = new EnrollmentViewModel("1","11","1","22");
        enrollment.setTraineeID("1");
        enrollment.setTrainerName("111");
        enrollment.setClassID("1");
        enrollment.setClassName("222");
        enrollmentList.add(enrollment);
        enrollmentList.add(enrollment);
        enrollmentList.add(enrollment);
        reload(enrollmentList,root);
        return root;
    }
    public void reload(ArrayList<EnrollmentViewModel> listEnrollment, View view){
        enrollmentAdapter = new EnrollmentAdapter(getActivity().getApplicationContext(), listEnrollment);
        // recyclerCategoryView.setHasFixedSize(true);
        recyclerEnrollmentView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerEnrollmentView.setAdapter(enrollmentAdapter);
    }
}
