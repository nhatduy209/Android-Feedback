package com.example.androidfeedback.ui.home;

import android.content.Intent;
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
import com.example.androidfeedback.ui.feedback.AddFeedback;
import com.example.androidfeedback.ui.feedback.FeedbackAdapter;
import com.example.androidfeedback.ui.feedback.FeedbackViewModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FeedbackTraineeViewModel feedbackTraineeViewModel;
    private RecyclerView recyclerFeedback;
    FeedbackTraineeAdapter feedbackAdapter;
    ArrayList<FeedbackTraineeViewModel> listFeedback;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        listFeedback = new ArrayList<FeedbackTraineeViewModel>();
        recyclerFeedback = root.findViewById(R.id.recyclerHomeTraineeFeedback);
        FeedbackTraineeViewModel feedback = new FeedbackTraineeViewModel("title", "class","class name","module","module naemeeeee","endtime",true);
        feedback.setFeedbackTraineeTitle("title");
        feedback.setTraineeClassID("class");
        feedback.setTraineeClassName("class name");
        feedback.setTraineeModuleID("module");
        feedback.setTraineeModuleName("module nameeeeee");
        feedback.setTraineeEndTime("endtime");
        feedback.setStatus(false);
        listFeedback.add(feedback);
        feedback.setStatus(true);
        listFeedback.add(feedback);
        reload(listFeedback,root);
        return root ;
    }
    public void reload(ArrayList<FeedbackTraineeViewModel> listFeedback, View view){
        feedbackAdapter = new FeedbackTraineeAdapter(getActivity().getApplicationContext(), listFeedback);
        // recyclerCategoryView.setHasFixedSize(true);
        recyclerFeedback.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerFeedback.setAdapter(feedbackAdapter);
    }
}