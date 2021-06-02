package com.example.androidfeedback.ui.assignment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.enrollment.EnrollmentFragment;
import com.example.androidfeedback.ui.uiclass.ClassFragment;


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
    private boolean allowRefresh = false ;
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

        // get session
        SharedPreferences pref = getActivity().getSharedPreferences("GetSession", Context.MODE_PRIVATE);
        final String userId = pref.getString("userId", "");
        final String role  = pref.getString("role", "");


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
        FrameLayout fl = (FrameLayout) getActivity().findViewById(this.getId());
        fl.removeAllViews();

        SharedPreferences prefs = getActivity().getSharedPreferences("Refresh",Context.MODE_PRIVATE);
        boolean shouldAttach = prefs.getBoolean("shouldAttach", true);
        if(shouldAttach){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("shouldAttach",false);
            editor.putBoolean("shouldReload",false);
            editor.apply();
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(this.getId(),new AssignmentFragment()).commit();
        }

        btnSearchAssignment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String inputText = txtSearchAssignment.getText().toString().trim();
                if(inputText!=null)
                {
                    Retrofit retrofit = RetrofitInstance.getClient();

                    CallGet callGet = retrofit.create(CallGet.class);
                    if(role.equals("Trainer"))
                    {
                        Call<List<AssignmentModel>> getSearchAssignmentsByTrainer = callGet.searchAssignmentByTrainer(userId,inputText);

                        getSearchAssignmentsByTrainer.enqueue(new Callback<List<AssignmentModel>>() {
                            @Override
                            public void onResponse(Call<List<AssignmentModel>> call, Response<List<AssignmentModel>> response) {
                                listAssignment = (ArrayList<AssignmentModel>) response.body();
                                reload(listAssignment, root );
                            }

                            @Override
                            public void onFailure(Call<List<AssignmentModel>> call, Throwable t) {
                                Toast.makeText(getActivity() ,"Not found!", Toast.LENGTH_LONG).show();
                            }

                        });
                    }
                    else
                    {
                        // call api to get list assignment search
                        Call<List<AssignmentModel>> getSearchAssignments = callGet.searchAssignment(inputText);

                        getSearchAssignments.enqueue(new Callback<List<AssignmentModel>>() {
                            @Override
                            public void onResponse(Call<List<AssignmentModel>> call, Response<List<AssignmentModel>> response) {
                                    listAssignment = (ArrayList<AssignmentModel>) response.body();
                                    reload(listAssignment, root );
                            }

                            @Override
                            public void onFailure(Call<List<AssignmentModel>> call, Throwable t) {
                                Toast.makeText(getActivity() ,"Not found!", Toast.LENGTH_LONG).show();
                            }

                        });

                    }
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

        if(role.equals("Trainer"))
        {
            Call<List<AssignmentModel>> getListAssignmentsByTrainer = callGet.getListAssignmentByTrainer(userId);

            getListAssignmentsByTrainer.enqueue(new Callback<List<AssignmentModel>>() {
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
        else
        {

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

        }
        return root;

    }
    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences prefs = getActivity().getSharedPreferences("Refresh",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        boolean shouldReload = prefs.getBoolean("shouldReload", false);
        if (!allowRefresh && shouldReload){
            allowRefresh = true;
            SharedPreferences pref = getActivity().getSharedPreferences("Refresh",Context.MODE_PRIVATE);
            editor = pref.edit();
            editor.putBoolean("shouldAttach",true);
            editor.apply();
        }
    }
    public void reload(ArrayList<AssignmentModel> listAssignment, View view){
        assignmentAdapter = new AssignmentAdapter(getActivity(), listAssignment);
        // recyclerCategoryView.setHasFixedSize(true);
        recyclerAssignment.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerAssignment.setAdapter(assignmentAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences prefs = getActivity().getSharedPreferences("Refresh", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        boolean shouldReload = prefs.getBoolean("shouldReload", false);

        if (allowRefresh) {
            // get seesion
            allowRefresh = false;
            editor.putBoolean("shouldAttach",false);
            editor.apply();
        }
        if(shouldReload){
            editor.putBoolean("shouldReload",false);
            editor.apply();
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(this.getId(),new AssignmentFragment()).commitAllowingStateLoss();
        }
    }
}
