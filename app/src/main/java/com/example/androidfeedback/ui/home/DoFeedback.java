package com.example.androidfeedback.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.feedback.EditFeedBack;
import com.example.androidfeedback.ui.feedback.FeedbackTopicAdapter;
import com.example.androidfeedback.ui.question.QuestionViewModel;

import java.util.ArrayList;

public class DoFeedback extends AppCompatActivity {

    private Context context = this ;
    private Button btnSubmit ;
    private TextView txtModuleName,txtClass,txtName,txtComment;
    private RecyclerView recyclerTopic;
    private DoFeedbackTopicAdapter feedbackTopicAdapter;
    private int[] listTopic;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.do_feedback_layout);
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

//        btnSubmit = findViewById(R.id.btnSubmitDoFeedback);
//        btnSubmit.setOnClickListener( new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                finish();
//                navController.navigate(R.id.nav_feedback);
//            }
//        });

        txtClass = findViewById(R.id.txtClassDoFeedback);
        txtName = findViewById(R.id.txtNameDoFeedback);
        txtModuleName = findViewById(R.id.txtModuleNameDofeedback);
        txtComment = findViewById(R.id.txtCommentDoFeedback);
        recyclerTopic = findViewById(R.id.recyclerQuestionDoFeedback);
        listTopic = new int[]{1,2,1,3,9,88};

        feedbackTopicAdapter = new DoFeedbackTopicAdapter(getApplicationContext(),listTopic);
        recyclerTopic.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerTopic.setAdapter(feedbackTopicAdapter);

    }
}
