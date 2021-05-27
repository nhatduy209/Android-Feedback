package com.example.androidfeedback.ui.feedback;

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
import com.example.androidfeedback.ui.question.AddQuestion;
import com.example.androidfeedback.ui.question.QuestionViewModel;

import java.util.ArrayList;

public class AddFeedback extends AppCompatActivity{
    private Context context = this ;
    private Button btnReview ;
    private Button btnBack ;
    private TextView txtFBType,txtFBTitle;
    private RecyclerView recyclerTopic;
    private int[] listTopic;
    private FeedbackTopicAdapter feedbackTopicAdapter;
    private int[] list;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_add_layout);
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        btnBack = findViewById(R.id.btnAddFeedbackBack);
        btnBack.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
                navController.navigate(R.id.nav_feedback);
            }
        });

        txtFBTitle = findViewById(R.id.txtAddFeedbackTitle);
        recyclerTopic = findViewById(R.id.recyclerFBTopic);
        listTopic = new int[]{1,2,3};
        feedbackTopicAdapter = new FeedbackTopicAdapter(getApplicationContext(),listTopic);
        recyclerTopic.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerTopic.setAdapter(feedbackTopicAdapter);

        btnReview = findViewById(R.id.btnReviewFeedback);
        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fbTitle = txtFBTitle.getText().toString();
                if(!fbTitle.isEmpty()){
                    ArrayList<QuestionViewModel> review = feedbackTopicAdapter.getListReview();
                    list = new int[100];
                    for(int i=0; i < review.size();i++)
                    {
                        list[i] = (review.get(i).getQuestionID());
                    }
                    Intent intent = new Intent(getApplicationContext(), EditFeedBack.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("listQuestion",list);
                    intent.putExtra("feedbackTitle",fbTitle);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(),"Title is not null!",Toast.LENGTH_SHORT).show();

            }
        });

    }

}