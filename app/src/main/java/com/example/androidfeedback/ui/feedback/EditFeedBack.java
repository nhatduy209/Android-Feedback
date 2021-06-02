package com.example.androidfeedback.ui.feedback;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.androidfeedback.ui.module.AddModuleModel;
import com.example.androidfeedback.ui.module.FeedbackModel;
import com.example.androidfeedback.ui.question.QuestionTopicModel;
import com.example.androidfeedback.ui.question.QuestionViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import common.serviceAPI.CallGet;
import common.serviceAPI.CallPost;
import common.serviceAPI.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EditFeedBack extends AppCompatActivity {
    private Context context = this ;
    private Button btnSave ;
    private Button btnBack ;
    private TextView txtAdminID,txtFBTitle,txtQuestions,tvTitle;
    private String role,userId;
    int[] listQuestionchecked;
    AddFeedbackModel feedbackModel;
    String act;
//    private int[] listTopic;
    private ArrayList<TopicFeedbackModel> listTopic;

    protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_review_edit_layout);
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        txtFBTitle = findViewById(R.id.txtReviewFeedbackTitle);
        txtAdminID = findViewById(R.id.txtReviewAdminID);
        txtQuestions = findViewById(R.id.txtReviewListQuestion);
        tvTitle = findViewById(R.id.tvReviewEditFeedback);


        SharedPreferences pref = getSharedPreferences("GetSession",Context.MODE_PRIVATE);
        role  = pref.getString("role", "");
        userId = pref.getString("userId", "");

        btnBack = findViewById(R.id.btnReviewFeedbackBack);
        btnBack.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,AddFeedback.class);
                startActivity(intent);
            }
        });


        btnSave = findViewById(R.id.btnReviewFeedbackSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = RetrofitInstance.getClient();
                CallPost callPost = retrofit.create(CallPost.class);
                Call<AddFeedbackModel> addFeedbackAPI = callPost.addFeedbackAPI(feedbackModel);

                addFeedbackAPI.enqueue(new Callback<AddFeedbackModel>() {
                    @Override
                    public void onResponse(Call<AddFeedbackModel> call, Response<AddFeedbackModel> response) {
                        String res = response.message();
                        Toast.makeText(getApplicationContext(),res,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<AddFeedbackModel> call, Throwable t) {

                    }
                });
//                finish();
//                navController.navigate(R.id.nav_feedback);
            }
        });

//        ArrayList<QuestionTopicModel> list = new ArrayList<>();
//        QuestionTopicModel question = new QuestionTopicModel(58,"Question Edit",4);
//        list.add(question);
//        question = new QuestionTopicModel(60,"123",4);
//        list.add(question);
//        question = new QuestionTopicModel(61,"1234",4);
//        list.add(question);
//        question = new QuestionTopicModel(62,"321duy",4);
//        list.add(question);
//        question = new QuestionTopicModel(65,"",4);
//        list.add(question);
//        question = new QuestionTopicModel(67,"aaaa",4);
//        list.add(question);
//        TopicFeedbackModel topic = new TopicFeedbackModel("Training program and content",list);
//        listTopic = new ArrayList<TopicFeedbackModel>();
//        listTopic.add(topic);
//
//
//        list =new ArrayList<QuestionTopicModel>();
//        question = new QuestionTopicModel(63,"Question cho topic 5",5);
//        list.add(question);
//        question = new QuestionTopicModel(68,"Ai cho 10d dep trai nhat\\n",5);
//        list.add(question);
//        topic = new TopicFeedbackModel("Trainer Coach", list);
//        listTopic.add(topic);
//
//        list=new ArrayList<QuestionTopicModel>();
//        question = new QuestionTopicModel(64, "Question Topic 6",6);
//        list.add(question);
//        topic = new TopicFeedbackModel("Course organizations",list);
//        listTopic.add(topic);
//
//        list = new ArrayList<QuestionTopicModel>();
//        question = new QuestionTopicModel(59,"Vui vẻ không",7);
//        list.add(question);
//        topic = new TopicFeedbackModel("Other",list);
//        listTopic.add(topic);

        listQuestionchecked= new int[]{};
        String feedbackTitle = txtFBTitle.getText().toString();
        int feedbackType =0;
        tvTitle.setText("Review New Feedback");
        txtFBTitle.setText("Feedback Title");
        txtAdminID.setText(userId);
        Bundle b = getIntent().getExtras();
        try{
            listQuestionchecked = b.getIntArray("listQuestion");  // get data passing from other activity
            feedbackTitle = b.getString("feedbackTitle");
            act = b.getString("Action");
            feedbackType = b.getInt("feedbackType");
            txtFBTitle.setText(feedbackTitle);
            tvTitle.setText("Review New Feedback");
        }catch(Exception e){
        }
        List<Integer> l = new ArrayList<Integer>();
        int n =0;
        while(listQuestionchecked[n]!=0)
        {
            l.add(listQuestionchecked[n]);
            n++;
        }
        feedbackModel = new AddFeedbackModel(1000,feedbackTitle,userId,feedbackType,l );

        Retrofit retrofit = RetrofitInstance.getClient();
        CallGet callGet = retrofit.create(CallGet.class);
        Call<List<TopicFeedbackModel>> listTopicFeedback = callGet.getListTopicFeedback();
        listTopicFeedback.enqueue(new Callback<List<TopicFeedbackModel>>() {
            @Override
            public void onResponse(Call<List<TopicFeedbackModel>> call, Response<List<TopicFeedbackModel>> response) {
                listTopic = (ArrayList<TopicFeedbackModel>) response.body();
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

            @Override
            public void onFailure(Call<List<TopicFeedbackModel>> call, Throwable t) {

            }
        });



    }
}
