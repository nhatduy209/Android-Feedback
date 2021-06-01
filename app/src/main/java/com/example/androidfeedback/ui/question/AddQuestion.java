package com.example.androidfeedback.ui.question;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.MainActivity;
import com.example.androidfeedback.R;

import java.util.ArrayList;
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

public class AddQuestion extends AppCompatActivity {
    private TextView txtQuestionContent, txtTopicName,tvAddQuestion;
    // private Spinner spTopicName;
    private Context context = this ;
    private Button btnBack ;
    private Button btnSave ;
    private boolean isEdit  = false ;
    private String questionContent ;
    private int  questionID ;
    private Spinner spinnerQuestion;
    private TopicModel topicModel ;
    private QuestionAdapter questionAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_add_layout);
        final NavController navController = Navigation.findNavController(this ,R.id.nav_host_fragment);
        btnSave = findViewById(R.id.btnSaveQuestion);
        txtQuestionContent = findViewById(R.id.txtQuestionAddContent);

        tvAddQuestion = findViewById(R.id.tvAddQuestion);

        // spinner here
        spinnerQuestion = findViewById(R.id.spEnListTopicName);

        ArrayList<TopicModel> list = new ArrayList<TopicModel>();
        list.add(new TopicModel("Training program and content" , 4 ));
        list.add(new TopicModel("Trainer Coach" , 5 ));
        list.add(new TopicModel("Course organizations" , 6 ));
        list.add(new TopicModel("Other" , 7));

        setSpinner(spinnerQuestion , list );

        // user press save => call api add question
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!isEdit)   // check if user edit or add
                    {
                        // get value from user input
                        QuestionViewModel questionModel = new QuestionViewModel(0,
                                txtQuestionContent.getText().toString(), topicModel.getTopicID());

                        Retrofit retrofit = RetrofitInstance.getClient();

                        CallPost callPost = retrofit.create(CallPost.class);

                        Call<QuestionViewModel> addQuestion = callPost.addQuestionAPI(questionModel);

                        // call callback
                        addQuestion.enqueue(new Callback<QuestionViewModel>() {
                            @Override
                            public void onResponse(Call<QuestionViewModel> call, Response<QuestionViewModel> response) {
                                String res = response.message();
                                finish();
                                navController.navigate(R.id.nav_question);
                            }

                            @Override
                            public void onFailure(Call<QuestionViewModel> call, Throwable t) {

                            }
                        });
                    }else if (isEdit){

                        Retrofit retrofit = RetrofitInstance.getClient();

                        CallPut callPut = retrofit.create(CallPut.class);

                        Call<QuestionViewModel> updateQuestion = callPut.updateQuestionAPI(questionID,txtQuestionContent.getText().toString());

                        // call callback
                        updateQuestion.enqueue(new Callback<QuestionViewModel>() {
                            @Override
                            public void onResponse(Call<QuestionViewModel> call, Response<QuestionViewModel> response) {
                                String res = response.message();
                                // Reload current fragment
                                // load fragment again


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


    private void setSpinner( Spinner spinner, List<TopicModel> listData){
        ArrayAdapter dataAdapter = new ArrayAdapter(AddQuestion.this,
                android.R.layout.simple_spinner_dropdown_item,listData);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        // When user select a List-Item.
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                topicModel = (TopicModel) parent.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}


