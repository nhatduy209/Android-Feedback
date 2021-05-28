package com.example.androidfeedback.ui.question;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.androidfeedback.R;

import java.util.Calendar;
import java.util.List;

import common.serviceAPI.CallGet;
import common.serviceAPI.CallPost;
import common.serviceAPI.CallPut;
import common.serviceAPI.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddQuestion extends AppCompatActivity{
    private TextView txtQuestionContent, txtTopicName,tvAddQuestion;
    // private Spinner spTopicName;
    private Context context = this ;
    private Button btnBack ;
    private Button btnSave ;
    private int dateAdd = 0 ;    // choose which date pick is press by user
    private boolean isEdit  = false ;
    private String questionContent ;
    private int  questionID ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_add_layout);
        final NavController navController = Navigation.findNavController(this ,R.id.nav_host_fragment);

        btnSave = findViewById(R.id.btnSaveQuestion);
        txtQuestionContent = findViewById(R.id.txtQuestionAddContent);
        txtTopicName = findViewById(R.id.spQuestionAddTopicName);
        tvAddQuestion = findViewById(R.id.tvAddQuestion);

        // user press save => call api add question

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!isEdit)   // check if user edit or add
                    {
                        // get value from user input
                        QuestionViewModel questionModel = new QuestionViewModel(0,
                                txtQuestionContent.getText().toString(), 1,"");

                        Retrofit retrofit = RetrofitInstance.getClient();

                        CallPost callPost = retrofit.create(CallPost.class);

                        Call<QuestionViewModel> addQuestion = callPost.addQuestionAPI(questionModel);

                        // call callback
                        addQuestion.enqueue(new Callback<QuestionViewModel>() {
                            @Override
                            public void onResponse(Call<QuestionViewModel> call, Response<QuestionViewModel> response) {
                                String res = response.message();

                                // load fragment again
                                FragmentManager manager = AddQuestion.this.getSupportFragmentManager();
                                Fragment currentFragment = manager.findFragmentByTag("QuestionFragment");
                                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                                fragmentTransaction.detach(currentFragment);
                                fragmentTransaction.attach(currentFragment);
                                fragmentTransaction.commit();


                                finish();
                                navController.navigate(R.id.nav_question);

                            }

                            @Override
                            public void onFailure(Call<QuestionViewModel> call, Throwable t) {

                            }
                        });
                    }else if (isEdit == true ){

                        Retrofit retrofit = RetrofitInstance.getClient();

                        CallPut callPut = retrofit.create(CallPut.class);

                        Call<QuestionViewModel> updateQuestion = callPut.updateQuestionAPI(questionID,questionContent);

                        // call callback
                        updateQuestion.enqueue(new Callback<QuestionViewModel>() {
                            @Override
                            public void onResponse(Call<QuestionViewModel> call, Response<QuestionViewModel> response) {
                                String res = response.message();

                                // load fragment again
                                FragmentManager manager = AddQuestion.this.getSupportFragmentManager();
                                Fragment currentFragment = manager.findFragmentByTag("QuestionFragment");
                                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                                fragmentTransaction.detach(currentFragment);
                                fragmentTransaction.attach(currentFragment);
                                fragmentTransaction.commit();

                                finish();
                                navController.navigate(R.id.nav_question);

                            }

                            @Override
                            public void onFailure(Call<QuestionViewModel> call, Throwable t) {
                                String aa =t.getMessage() ;
                            }
                        });
                    }

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

         // get current data if edit
        Bundle b = getIntent().getExtras();
        try{
            String topicName = b.getString("topicName");  // get data passing from other activity
            txtTopicName.setText(topicName);
            questionContent = b.getString("questionContent");  // get data passing from other activity
            txtQuestionContent.setText(questionContent);
            tvAddQuestion.setText("Edit Question");
            questionID = b.getInt("questionID");
            isEdit = b.getBoolean("isEditing");
        }catch(Exception e){
            return ;
        }
    }
}
