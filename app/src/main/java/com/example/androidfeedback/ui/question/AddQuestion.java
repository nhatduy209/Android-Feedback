package com.example.androidfeedback.ui.question;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.androidfeedback.R;

import java.util.Calendar;

public class AddQuestion extends AppCompatActivity{
    private TextView txtQuestionContent, txtTopicName,tvAddQuestion;
//    private Spinner spTopicName;
    private Context context = this ;
    private Button btnBack ;
    private Button btnSave ;
    private int dateAdd = 0 ;    // choose which date pick is press by user
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_add_layout);
        final NavController navController = Navigation.findNavController(this ,R.id.nav_host_fragment);

        btnSave = findViewById(R.id.btnSaveQuestion);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                navController.navigate(R.id.nav_class);
            }
        });

        btnBack = findViewById(R.id.btnBackQuestion);
        btnBack.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
                navController.navigate(R.id.nav_question);
            }
        });

//         get current data if edit
        txtQuestionContent = findViewById(R.id.txtQuestionAddContent);
        txtTopicName = findViewById(R.id.spQuestionAddTopicName);
        tvAddQuestion = findViewById(R.id.tvAddQuestion);
        Bundle b = getIntent().getExtras();
        try{
            String topicName = b.getString("topicName");  // get data passing from other activity
            txtTopicName.setText(topicName);
            String questionContent = b.getString("questionContent");  // get data passing from other activity
            txtQuestionContent.setText(questionContent);
            tvAddQuestion.setText("Edit Question");
        }catch(Exception e){
            return ;
        }
    }
}
