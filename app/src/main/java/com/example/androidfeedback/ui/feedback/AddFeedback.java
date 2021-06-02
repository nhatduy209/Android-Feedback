package com.example.androidfeedback.ui.feedback;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.module.AddModule;
import com.example.androidfeedback.ui.module.AddModuleModel;
import com.example.androidfeedback.ui.module.AddModuleSpinner;
import com.example.androidfeedback.ui.module.AdminModel;
import com.example.androidfeedback.ui.module.FeedbackModel;
import com.example.androidfeedback.ui.question.AddQuestion;
import com.example.androidfeedback.ui.question.QuestionTopicModel;
import com.example.androidfeedback.ui.question.QuestionViewModel;

import java.util.ArrayList;
import java.util.List;

import common.serviceAPI.CallGet;
import common.serviceAPI.CallPost;
import common.serviceAPI.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddFeedback extends AppCompatActivity{
    private Context context = this ;
    private Button btnReview ;
    private Button btnBack ;
    private TextView txtFBTitle;
    private Spinner spFeedbackType;
    private  List<TypeFeedbackModel> listType;
    private RecyclerView recyclerTopic;
    private ArrayList<TopicFeedbackModel> listTopic;
    private TypeFeedbackModel typeFeedbackModel;
    private FeedbackTopicAdapter feedbackTopicAdapter;
    private int typeId;
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
        spFeedbackType = findViewById(R.id.spAddFeedbackType);

        //Get list  type
        listType = new ArrayList<TypeFeedbackModel>();
        TypeFeedbackModel type = new TypeFeedbackModel(1,"Offline",false,null);
        listType.add(type);
        type = new TypeFeedbackModel(2,"Online",false,null);
        listType.add(type);
        setTypeFeedbackSpinner(spFeedbackType,listType);


        ArrayList<QuestionTopicModel> list = new ArrayList<>();
        QuestionTopicModel question = new QuestionTopicModel(58,"Question Edit",4);
        list.add(question);
        question = new QuestionTopicModel(60,"123",4);
        list.add(question);
        question = new QuestionTopicModel(61,"1234",4);
        list.add(question);
        question = new QuestionTopicModel(62,"321duy",4);
        list.add(question);
        question = new QuestionTopicModel(65,"",4);
        list.add(question);
        question = new QuestionTopicModel(67,"aaaa",4);
        list.add(question);
        TopicFeedbackModel topic = new TopicFeedbackModel("Training program and content",list);
        listTopic = new ArrayList<TopicFeedbackModel>();
        listTopic.add(topic);


        list =new ArrayList<QuestionTopicModel>();
        question = new QuestionTopicModel(63,"Question cho topic 5",5);
        list.add(question);
        question = new QuestionTopicModel(68,"Ai cho 10d dep trai nhat\\n",5);
        list.add(question);
        topic = new TopicFeedbackModel("Trainer Coach", list);
        listTopic.add(topic);

        list=new ArrayList<QuestionTopicModel>();
        question = new QuestionTopicModel(64, "Question Topic 6",6);
        list.add(question);
        topic = new TopicFeedbackModel("Course organizations",list);
        listTopic.add(topic);

        list = new ArrayList<QuestionTopicModel>();
        question = new QuestionTopicModel(59,"Vui vẻ không",7);
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
                    ArrayList<QuestionTopicModel> review = feedbackTopicAdapter.getListReview();
                    listID = new int[100];
                    for(int i=0; i < review.size();i++)
                    {
                        listID[i] = (review.get(i).getQuestionID());
                    }
                    Intent intent = new Intent(getApplicationContext(), EditFeedBack.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("listQuestion",listID);
                    intent.putExtra("feedbackTitle",fbTitle);
                    intent.putExtra("action","review");
                    intent.putExtra("feedbackType",typeId);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(),"Title is not null!",Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void setTypeFeedbackSpinner(Spinner spinner, List<TypeFeedbackModel> listData){
        ArrayAdapter dataAdapter = new ArrayAdapter(AddFeedback.this,
                android.R.layout.simple_spinner_dropdown_item,listData);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        // When user select a List-Item.
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                typeFeedbackModel = (TypeFeedbackModel) parent.getSelectedItem();
                typeId = ((TypeFeedbackModel) parent.getSelectedItem()).getTypeID();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}