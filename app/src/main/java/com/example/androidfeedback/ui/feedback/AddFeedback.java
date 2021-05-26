package com.example.androidfeedback.ui.feedback;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.feedback.feedbacktopic.FeedbackTopicAdapter;

public class AddFeedback extends AppCompatActivity{
    private Context context = this ;
    private Button btnReview ;
    private Button btnBack ;
    private TextView txtFBType,txtFBTitle;
    private RecyclerView recyclerTopic;
    private int[] listTopic;
    private FeedbackTopicAdapter feedbackTopicAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_add_layout);
//        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        recyclerTopic = findViewById(R.id.recyclerFBTopic);
        listTopic = new int[]{1,2,3,4,5,6,7,8};
        feedbackTopicAdapter = new FeedbackTopicAdapter(getApplicationContext(),listTopic);
        recyclerTopic.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerTopic.setAdapter(feedbackTopicAdapter);
    }

}