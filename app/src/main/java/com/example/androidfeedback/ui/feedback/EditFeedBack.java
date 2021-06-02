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
import com.example.androidfeedback.ui.question.QuestionViewModel;

import java.util.ArrayList;

public class EditFeedBack extends AppCompatActivity {
    private Context context = this ;
    private Button btnSave ;
    private Button btnBack ;
    private TextView txtAdminID,txtFBTitle,txtQuestions,tvTitle;
//    private int[] listTopic;
    private ArrayList<TopicFeedbackModel> listTopic;

    protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_review_edit_layout);
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        txtFBTitle = findViewById(R.id.txtReviewFeedbackTitle);
        txtAdminID = findViewById(R.id.txtReviewAdminID);
        txtQuestions = findViewById(R.id.txtReviewListQuestion);
        tvTitle = findViewById(R.id.tvReviewEditFeedback);

        btnBack = findViewById(R.id.btnReviewFeedbackBack);
        btnBack.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
                navController.navigate(R.id.nav_feedback);
            }
        });

        btnSave = findViewById(R.id.btnReviewFeedbackSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                navController.navigate(R.id.nav_feedback);
            }
        });

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



        int[] listQuestionchecked = new int[]{};
        tvTitle.setText("Review New Feedback");
        txtFBTitle.setText("Feedback Title");
        txtAdminID.setText("Admin ID");
        Bundle b = getIntent().getExtras();
        try{
             listQuestionchecked = b.getIntArray("listQuestion");  // get data passing from other activity
            String feedbackTitle = b.getString("feedbackTitle");
            txtFBTitle.setText(feedbackTitle);
            tvTitle.setText("Edit New Feedback");
        }catch(Exception e){
            String AdminId = b.getString("AdminID");
            tvTitle.setText("Edit New Feedback");
        }
//
//        listTopic = new int[]{1,2,3};
        //list question in topic
        String questions = new String();
        for (int i=0;i<listTopic.size();i++){
            questions += "<b>" + listTopic.get(i).getTopicName() + "</b><br/>";
            for(int j =0; j<listTopic.get(i).getQuestions().size();j++){
                for( int k=0; k < listQuestionchecked.length;k++)
                    if(listQuestionchecked[k] == listTopic.get(i).getQuestions().get(j).getQuestionID())
                        questions += "- "+ listTopic.get(i).getQuestions().get(j).getQuestionContent() +" <br/>";
            }
        }
        txtQuestions.setText(android.text.Html.fromHtml(questions));
    }
}
