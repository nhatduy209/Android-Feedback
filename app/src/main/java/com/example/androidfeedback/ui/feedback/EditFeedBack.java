package com.example.androidfeedback.ui.feedback;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;

import java.util.ArrayList;

public class EditFeedBack extends AppCompatActivity {
    private Context context = this ;
    private Button btnReview ;
    private Button btnBack ;
    private TextView txtFBType,txtFBTitle;
    private RecyclerView recyclerTopic;
    private FeedbackTopicAdapter feedbackTopicAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_review_edit_layout);
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

//        btnBack = findViewById(R.id.btnAddFeedbackBack);
//        btnBack.setOnClickListener( new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                finish();
//                navController.navigate(R.id.nav_feedback);
//            }
//        });

//        btnReview = findViewById(R.id.btnReviewFeedback);

        Bundle b = getIntent().getExtras();
        try{
            int[] listQuestionchecked = b.getIntArray("listQuestion");  // get data passing from other activity
        }catch(Exception e){
            return ;
        }
//        recyclerTopic = findViewById(R.id.recyclerFBTopic);
//        listTopic = new int[]{1,2,3};
//        feedbackTopicAdapter = new FeedbackTopicAdapter(getApplicationContext(),listTopic);
//        recyclerTopic.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        recyclerTopic.setAdapter(feedbackTopicAdapter);
    }
}
