package com.example.androidfeedback.ui.question;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.MainActivity;
import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.home.HomeViewModel;
import com.example.androidfeedback.ui.login.LoginModel;
import com.example.androidfeedback.ui.module.ModuleAdapter;
import com.example.androidfeedback.ui.module.ModuleViewModel;
import com.example.androidfeedback.ui.uiclass.AddClass;

import java.util.ArrayList;
import java.util.List;

import common.serviceAPI.CallGet;
import common.serviceAPI.CallPost;
import common.serviceAPI.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuestionFragment extends Fragment {
    private RecyclerView recyclerQuestionView;
    QuestionAdapter questionAdapter;
    ArrayList<QuestionViewModel> questionList;
    Button btnAdd;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_question,null);
        final View smallRoot = inflater.inflate(R.layout.question_recycler_view_item,null);
        questionList = new ArrayList<QuestionViewModel>();
        recyclerQuestionView = root.findViewById(R.id.recyclerQuestionView);

        btnAdd = root.findViewById(R.id.btnAddQuestion);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddQuestion.class);
                startActivity(intent);
            }
        });

        // call api to get list quesiton
        Retrofit retrofit = RetrofitInstance.getClient();

        CallGet callGet = retrofit.create(CallGet.class);

        Call<List<QuestionViewModel>> getListQuestions = callGet.getListQuestion();

        getListQuestions.enqueue(new Callback<List<QuestionViewModel>>() {
            @Override
            public void onResponse(Call<List<QuestionViewModel>> call, Response<List<QuestionViewModel>> response) {
                String aa = response.message();
                questionList = (ArrayList<QuestionViewModel>) response.body();
                int a= 11 ;
                reload(questionList, root );
            }

            @Override
            public void onFailure(Call<List<QuestionViewModel>> call, Throwable t) {

            }
        });

        /* QuestionViewModel question = new QuestionViewModel(1,"11",1,"22");
        question.setQuestionID(1);
        question.setQuestionContent("111");
        question.setTopicID(1);
        question.setQuestionContent("222");
        questionList.add(question);
        questionList.add(question);
        questionList.add(question);
        reload(questionList,root);*/



        return root;
    }
    public void reload(ArrayList<QuestionViewModel> listQuestion, View view){
        questionAdapter = new QuestionAdapter(getActivity().getApplicationContext(), listQuestion);
        // recyclerCategoryView.setHasFixedSize(true);
        recyclerQuestionView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerQuestionView.setAdapter(questionAdapter);
    }
}
