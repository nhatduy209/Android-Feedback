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
    private ArrayList<TopicFeedbackModel> listTopic;
    private FeedbackTopicAdapter feedbackTopicAdapter;
    private int[] listID;
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

        ArrayList<QuestionViewModel> list = new ArrayList<>();
        QuestionViewModel question = new QuestionViewModel(58,"Question Edit",4);
        list.add(question);
        question = new QuestionViewModel(60,"123",4);
        list.add(question);
        question = new QuestionViewModel(61,"1234",4);
        list.add(question);
        question = new QuestionViewModel(62,"321duy",4);
        list.add(question);
        question = new QuestionViewModel(65,"",4);
        list.add(question);
        question = new QuestionViewModel(67,"aaaa",4);
        list.add(question);
        TopicFeedbackModel topic = new TopicFeedbackModel("Training program and content",list);
        listTopic = new ArrayList<TopicFeedbackModel>();
        listTopic.add(topic);


        list =new ArrayList<QuestionViewModel>();
        question = new QuestionViewModel(63,"Question cho topic 5",5);
        list.add(question);
        question = new QuestionViewModel(68,"Ai cho 10d dep trai nhat\\n",5);
        list.add(question);
        topic = new TopicFeedbackModel("Trainer Coach", list);
        listTopic.add(topic);

        list=new ArrayList<QuestionViewModel>();
        question = new QuestionViewModel(64, "Question Topic 6",6);
        list.add(question);
        topic = new TopicFeedbackModel("Course organizations",list);
        listTopic.add(topic);

        list = new ArrayList<QuestionViewModel>();
        question = new QuestionViewModel(59,"Vui vẻ không",7);
        list.add(question);
        topic = new TopicFeedbackModel("Other",list);
        listTopic.add(topic);




//        Bundle b = getIntent().getExtras();
//        try{
//            String feedbackTitle = b.getString("feedbackTitle");
//            txtFBTitle.setText(feedbackTitle);
//        }catch(Exception e){
//            String AdminId = b.getString("AdminID");
//        }

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
                    listID = new int[100];
                    for(int i=0; i < review.size();i++)
                    {
                        listID[i] = (review.get(i).getQuestionID());
                    }
                    Intent intent = new Intent(getApplicationContext(), EditFeedBack.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("listQuestion",listID);
                    intent.putExtra("feedbackTitle",fbTitle);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(),"Title is not null!",Toast.LENGTH_SHORT).show();

            }
        });

    }

}