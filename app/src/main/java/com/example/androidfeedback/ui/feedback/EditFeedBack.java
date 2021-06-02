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
    private Button btnSave ;
    private Button btnBack ;
    private TextView txtAdminID,txtFBTitle,txtQuestions,tvTitle;
    private int[] listTopic;


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

        listTopic = new int[]{1,2,3};
        //list question in topic
        String questions = new String();
        for (int i=0;i<listTopic.length;i++){
            questions += "<b>" + listTopic[i] + "</b><br/>";
            for(int j =0; j<2;j++){
                questions += "- Ngày mai nắng ấm heo xinh xinh đi trên con đường <br/>";
            }
        }
        txtQuestions.setText(android.text.Html.fromHtml(questions));
    }
}
