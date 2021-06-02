package com.example.androidfeedback.ui.question;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;

import java.util.ArrayList;
import java.util.List;

import common.serviceAPI.CallGet;
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
    private boolean allowRefresh = false ;
    private Spinner spinner ;
    private String topicName ;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // get seesion

//        FrameLayout fl = (FrameLayout) getActivity().findViewById(this.getId());
//        fl.removeAllViews();

        SharedPreferences prefs = getActivity().getSharedPreferences("Refresh",Context.MODE_PRIVATE);
        boolean shouldAttach = prefs.getBoolean("shouldAttach", true);
        if(shouldAttach){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("shouldAttach",false);
            editor.putBoolean("shouldReload",false);
            editor.apply();
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(this.getId(),new QuestionFragment()).commit();
        }

        final View root = inflater.inflate(R.layout.fragment_question,null);
        final View smallRoot = inflater.inflate(R.layout.question_recycler_view_item,null );
        questionList = new ArrayList<QuestionViewModel>();
        recyclerQuestionView = root.findViewById(R.id.recyclerQuestionView);
        spinner = root.findViewById(R.id.spQuestionAddTopicName);

        // filter topic name

        // spinner here
        ArrayList<String> list = new ArrayList<String>();
        list.add("all");
        list.add("Training program and content");
        list.add("Trainer Coach" );
        list.add("Course organizations" );
        list.add("Other");

        setSpinner(spinner , list ,root );

        btnAdd = root.findViewById(R.id.btnAddQuestion);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddQuestion.class);
                startActivity(intent);
            }
        });

        return root;
    }
    public void reload(ArrayList<QuestionViewModel> listQuestion, View view){
        questionAdapter = new QuestionAdapter(getActivity(), listQuestion);
        // recyclerCategoryView.setHasFixedSize(true);
        recyclerQuestionView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerQuestionView.setAdapter(questionAdapter);
    }

    private void setSpinner(Spinner spinner, List<String> listData , final View root ){
        ArrayAdapter dataAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,listData);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        // When user select a List-Item.
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                topicName = (String) parent.getSelectedItem();

                // call api to get list question
                Retrofit retrofit = RetrofitInstance.getClient();

                CallGet callGet = retrofit.create(CallGet.class);

                Call<List<QuestionViewModel>> getListQuestions = callGet.getListQuestion(topicName);

                getListQuestions.enqueue(new Callback<List<QuestionViewModel>>() {
                    @Override
                    public void onResponse(Call<List<QuestionViewModel>> call, Response<List<QuestionViewModel>> response) {
                        questionList = (ArrayList<QuestionViewModel>) response.body();
                        reload(questionList, root );
                    }

                    @Override
                    public void onFailure(Call<List<QuestionViewModel>> call, Throwable t) {

                    }
                });
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences prefs = getActivity().getSharedPreferences("Refresh",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        boolean shouldReload = prefs.getBoolean("shouldReload", false);

        if (allowRefresh) {
            // get seesion
            allowRefresh = false;
            editor.putBoolean("shouldAttach",false);
            editor.apply();
        }
        if(shouldReload){
            editor.putBoolean("shouldReload",false);
            editor.apply();
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(this.getId(),new QuestionFragment()).commit();
        }
    }
}
