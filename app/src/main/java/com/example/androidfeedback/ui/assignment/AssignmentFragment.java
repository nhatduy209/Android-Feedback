package com.example.androidfeedback.ui.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.androidfeedback.R;


import java.util.ArrayList;
import java.util.List;

import common.serviceAPI.CallGet;
import common.serviceAPI.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AssignmentFragment extends Fragment {
    private ArrayList<AssignmentModel> listAssignment ;
    private RecyclerView recyclerAssignment;
    private AssignmentAdapter assignmentAdapter ;
    private EditText txtSearchAssignment;
    private Button btnAdd, btnSearchAssignment;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_assignment, container, false);
        listAssignment = new ArrayList<AssignmentModel>();
        //createAssignmentList();
        recyclerAssignment = root.findViewById(R.id.recyclerAssignment);
        //Find input text
        txtSearchAssignment = root.findViewById(R.id.txtSearchAssignment);

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
        btnSearchAssignment = root.findViewById(R.id.btnSearchAssignment);
        btnSearchAssignment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String inputText = txtSearchAssignment.getText().toString();
                if(inputText!=null)
                {
                    // call api to get list assignment search
                    Retrofit retrofit = RetrofitInstance.getClient();

                    CallGet callGet = retrofit.create(CallGet.class);

                    Call<List<AssignmentModel>> getSearchAssignments = callGet.searchAssignment(inputText);

                    getSearchAssignments.enqueue(new Callback<List<AssignmentModel>>() {
                        @Override
                        public void onResponse(Call<List<AssignmentModel>> call, Response<List<AssignmentModel>> response) {
                            listAssignment = (ArrayList<AssignmentModel>) response.body();
                            reload(listAssignment, root );
                        }

                        @Override
                        public void onFailure(Call<List<AssignmentModel>> call, Throwable t) {

                        }

                    });

                }

            }
        });
        //Set adapter to RecyclerView
        recyclerAssignment.setHasFixedSize(true);
        recyclerAssignment.setAdapter(assignmentAdapter);
        recyclerAssignment.setLayoutManager(new LinearLayoutManager(root.getContext()));


        // call api to get list assignment
        Retrofit retrofit = RetrofitInstance.getClient();

        CallGet callGet = retrofit.create(CallGet.class);

        Call<List<AssignmentModel>> getListAssignments = callGet.getListAssignment();

        getListAssignments.enqueue(new Callback<List<AssignmentModel>>() {
            @Override
            public void onResponse(Call<List<AssignmentModel>> call, Response<List<AssignmentModel>> response) {
                listAssignment = (ArrayList<AssignmentModel>) response.body();
                reload(listAssignment, root );
            }

            @Override
            public void onFailure(Call<List<AssignmentModel>> call, Throwable t) {

            }

        });

        return root;

    }
    public void reload(ArrayList<AssignmentModel> listAssignment, View view){
        assignmentAdapter = new AssignmentAdapter(getActivity(), listAssignment);
        // recyclerCategoryView.setHasFixedSize(true);
        recyclerAssignment.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerAssignment.setAdapter(assignmentAdapter);
    }
}

