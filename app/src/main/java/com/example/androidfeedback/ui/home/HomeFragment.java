package com.example.androidfeedback.ui.home;

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
import com.example.androidfeedback.ui.assignment.AddAssignment;
import com.example.androidfeedback.ui.assignment.AssignmentAdapter;
import com.example.androidfeedback.ui.assignment.AssignmentFragment;
import com.example.androidfeedback.ui.assignment.AssignmentModel;
import com.example.androidfeedback.ui.feedback.AddFeedback;
import com.example.androidfeedback.ui.feedback.FeedbackAdapter;
import com.example.androidfeedback.ui.feedback.FeedbackViewModel;

import java.util.ArrayList;
import java.util.List;

import common.serviceAPI.CallGet;
import common.serviceAPI.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment {

    private FeedbackTraineeViewModel feedbackTraineeViewModel;
    private RecyclerView recyclerFeedback;
    FeedbackTraineeAdapter feedbackAdapter;
    ArrayList<FeedbackTraineeViewModel> listFeedback;
    private AssignmentAdapter assignmentAdapter;
    private ArrayList<AssignmentModel> listAssignment;
    private RecyclerView recyclerAssignment;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SharedPreferences pref = getActivity().getSharedPreferences("GetSession",Context.MODE_PRIVATE);
        String role  = pref.getString("role", "");
        String userId = pref.getString("userId", "");
        if(role.matches("Admin")){
            final View root = inflater.inflate(R.layout.fragment_assignment, container, false);

            FrameLayout fl = (FrameLayout) getActivity().findViewById(this.getId());
            fl.removeAllViews();

            Button btnAdd = root.findViewById(R.id.btnAddAssignment);
            Button btnSearch = root.findViewById(R.id.btnSearchAssignment);
            EditText txtSearch = root.findViewById(R.id.txtSearchAssignment);
//            btnAdd.setVisibility(View.GONE);
            btnSearch.setVisibility(View.GONE);
            txtSearch.setVisibility(View.GONE);
            btnAdd.setVisibility(View.GONE);

            listAssignment = new ArrayList<AssignmentModel>();
            //createAssignmentList();
            recyclerAssignment = root.findViewById(R.id.recyclerAssignment);
            assignmentAdapter = new AssignmentAdapter(getActivity().getApplicationContext(),listAssignment);

            // call api to get list assignment
            Retrofit retrofit = RetrofitInstance.getClient();

            CallGet callGet = retrofit.create(CallGet.class);

            Call<List<AssignmentModel>> getListAssignments = callGet.getListAssignment();

            getListAssignments.enqueue(new Callback<List<AssignmentModel>>() {
                @Override
                public void onResponse(Call<List<AssignmentModel>> call, Response<List<AssignmentModel>> response) {
                    listAssignment = (ArrayList<AssignmentModel>) response.body();
                    reloadByTrainer(listAssignment, root );
                }

                @Override
                public void onFailure(Call<List<AssignmentModel>> call, Throwable t) {

                }

            });

            return root ;
        }
        else if(role.matches("Trainer")){
            final View root = inflater.inflate(R.layout.fragment_assignment, container, false);

            FrameLayout fl = (FrameLayout) getActivity().findViewById(this.getId());
            fl.removeAllViews();

            Button btnAdd = root.findViewById(R.id.btnAddAssignment);
            Button btnSearch = root.findViewById(R.id.btnSearchAssignment);
            EditText txtSearch = root.findViewById(R.id.txtSearchAssignment);
            btnAdd.setVisibility(View.GONE);
            btnSearch.setVisibility(View.GONE);
            txtSearch.setVisibility(View.GONE);

            listAssignment = new ArrayList<AssignmentModel>();
            //createAssignmentList();
            recyclerAssignment = root.findViewById(R.id.recyclerAssignment);
            assignmentAdapter = new AssignmentAdapter(getActivity().getApplicationContext(),listAssignment);

            // call api to get list assignment
            Retrofit retrofit = RetrofitInstance.getClient();

            CallGet callGet = retrofit.create(CallGet.class);
            Call<List<AssignmentModel>> getListAssignmentsByTrainer = callGet.getListAssignmentByTrainer(userId);

            getListAssignmentsByTrainer.enqueue(new Callback<List<AssignmentModel>>() {
                @Override
                public void onResponse(Call<List<AssignmentModel>> call, Response<List<AssignmentModel>> response) {
                    listAssignment = (ArrayList<AssignmentModel>) response.body();
                    reloadByTrainer(listAssignment, root );
                }

                @Override
                public void onFailure(Call<List<AssignmentModel>> call, Throwable t) {

                }

            });
            return root ;
        }
        else {
            View root = inflater.inflate(R.layout.fragment_home, container, false);

            FrameLayout fl = (FrameLayout) getActivity().findViewById(this.getId());
            fl.removeAllViews();

            listFeedback = new ArrayList<FeedbackTraineeViewModel>();
            recyclerFeedback = root.findViewById(R.id.recyclerHomeTraineeFeedback);
            FeedbackTraineeViewModel feedback = new FeedbackTraineeViewModel("title", "class","class name","module","module naemeeeee","endtime",true);
            feedback.setFeedbackTraineeTitle("title");
            feedback.setTraineeClassID("class");
            feedback.setTraineeClassName("class name");
            feedback.setTraineeModuleID("module");
            feedback.setTraineeModuleName("module nameeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
            feedback.setTraineeEndTime("endtime");
            feedback.setStatus(true);
            listFeedback.add(feedback);
            feedback = new FeedbackTraineeViewModel("title", "class","class name","module","module naemeeeee","endtime",true);
            feedback.setStatus(false);
            listFeedback.add(feedback);
            reload(listFeedback,root);
            return root ;
        }

    }
    public void reload(ArrayList<FeedbackTraineeViewModel> listFeedback, View view){
        feedbackAdapter = new FeedbackTraineeAdapter(getActivity().getApplicationContext(), listFeedback);
        // recyclerCategoryView.setHasFixedSize(true);
        recyclerFeedback.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerFeedback.setAdapter(feedbackAdapter);
    }
    public void reloadByTrainer(ArrayList<AssignmentModel> listAssignment, View view){
        assignmentAdapter = new AssignmentAdapter(getActivity(), listAssignment);
        // recyclerCategoryView.setHasFixedSize(true);
        recyclerAssignment.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerAssignment.setAdapter(assignmentAdapter);
    }
}